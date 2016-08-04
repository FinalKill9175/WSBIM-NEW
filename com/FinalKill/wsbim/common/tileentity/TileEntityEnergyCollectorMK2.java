package com.FinalKill.wsbim.common.tileentity;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.ForgeDirection;

import com.FinalKill.wsbim.common.block.BlockEnergyCollector;
import com.FinalKill.wsbim.util.ForgeDirectionUtils;
import com.FinalKill.wsbim.util.condenservalues.EnumEnergyCollector;
import com.FinalKill.wsbim.util.condenservalues.ICondenserValuesEmitter;
import com.FinalKill.wsbim.util.condenservalues.ICondenserValuesReciever;
import com.FinalKill.wsbim.util.condenservalues.IEnergyCollector;

public class TileEntityEnergyCollectorMK2 extends TileEntity implements ICondenserValuesEmitter, IEnergyCollector{
	
	private ForgeDirection[] directions = new ForgeDirection[6];
	private ForgeDirection current_supplying_direction = null;
	
	boolean emitting = false;
	
	
	public int cv;
	
	int tick;
	
	public void updateEntity(){
		if(!worldObj.isRemote){
			//System.out.println(cv);
			
			this.updateConnections();
			this.updateCV();
		}
	}
	
	public void updateConnections(){
		List<ForgeDirection> allowed = new ArrayList<ForgeDirection>();
		for(int i = 0; i < this.getOutputDirections().length; i++){
			allowed.add(this.getOutputDirections()[i]);
		}
		
		
		ForgeDirection[] allDirs = ForgeDirectionUtils.allDirections();
		for(int i = 0; i < allDirs.length; i++){
			ForgeDirection current = allDirs[i];
			if(allowed.contains(current)){
				if(testDirection(current)){
					directions[i] = current;
					current_supplying_direction = current;
					//System.out.println("Found: "+current);
				}
			}
		}
	}

	public boolean testDirection(ForgeDirection dir){
		Block block = worldObj.getBlock(xCoord+dir.offsetX, yCoord+dir.offsetY, zCoord+dir.offsetZ);
		TileEntity te = worldObj.getTileEntity(xCoord+dir.offsetX, yCoord+dir.offsetY, zCoord+dir.offsetZ);
		if(block !=null && te !=null){
			if(te instanceof ICondenserValuesReciever){
				ICondenserValuesReciever rec = (ICondenserValuesReciever)te;
				if(rec.canRecieve(dir)){
					return true;
				}
			}
		}
		return false;
	}
	
	public void writeToNBT(NBTTagCompound tagCompound){
		super.writeToNBT(tagCompound);
		tagCompound.setInteger("CV", cv);
	}
	
	public void readFromNBT(NBTTagCompound tagCompound){
		super.readFromNBT(tagCompound);
		cv = tagCompound.getInteger("CV");
	}
	
	public Block getSupplyingBlock(){
		for(int i = 0; i < directions.length; i++){
			ForgeDirection dir = directions[i];
			if(dir !=null){
				return worldObj.getBlock(xCoord+dir.offsetX, yCoord+dir.offsetY, zCoord+dir.offsetZ);
			}
		}
		return null;
	}
	
	
	public TileEntity getSupplyingTileEntity(){
		for(int i = 0; i < directions.length; i++){
			ForgeDirection dir = directions[i];
			if(dir !=null){
				return worldObj.getTileEntity(xCoord+dir.offsetX, yCoord+dir.offsetY, zCoord+dir.offsetZ);
			}
		}
		return null;
	}
	
	public ForgeDirection getSupplyingDirection(){
		for(int i = 0; i < directions.length; i++){
			ForgeDirection dir = directions[i];
			if(dir !=null){
				return dir;
			}
		}
		return null;
	}
	
	public int getTicksPerCV() {
		return getType().getTicksPerEmittion();
	}

	public int getCVPerEmition() {
		return getType().getCVPerEmittion();
	}

	public float getLightIncrementPerCVIncrease() {
		return getType().lightLevelDiffPerCVIncrement;
	}

	public float getMinLightLevel() {
		return getType().minLightlevel;
	}

	public float getMaxLightLevel() {
		return getType().maxLightLevel;
	}

	public int getCVIncrease() {
		return getType().getCVIncrease();
	}
	
	public EnumEnergyCollector getType(){
		if(worldObj.getBlock(xCoord, yCoord, zCoord) !=null && worldObj.getBlock(xCoord, yCoord, zCoord) instanceof BlockEnergyCollector){
			BlockEnergyCollector ec = (BlockEnergyCollector)worldObj.getBlock(xCoord, yCoord, zCoord);
			return ec.getType();
		}
		
		return null;
	}

	public ForgeDirection[] getOutputDirections() {
		  return ForgeDirectionUtils.energyCollectorOutputs();
	}

	public boolean canEmit() {
		return false;
	}

	public void setCV(int cv) {
		this.cv = cv;
		return;
	}

	public int getCV() {
		return cv;
	}

	public ForgeDirection[] getDirections() {
		return directions;
	}

	public void updateCV() {
		TileEntity te = this.getSupplyingTileEntity();
		ForgeDirection dir = this.getSupplyingDirection();
		if(te !=null && dir !=null){
			if(te instanceof ICondenserValuesReciever){
				ICondenserValuesReciever rec = (ICondenserValuesReciever)te;
				if(rec.canRecieve(dir)){
					if(tick > 0){
						--tick;
					}
					if(tick == 0){
						float brightness = worldObj.getLightBrightness(xCoord, yCoord+1, zCoord);
						if(brightness >= this.getMinLightLevel() && brightness <= this.getMaxLightLevel()){
							rec.setCV(rec.getCV()+this.getCVPerEmition()+(cv > 0?cv : 0));
							if(cv > 0){
								cv = 0;
							}
						}
						tick = this.getTicksPerCV();	
					}
					this.emitting = true;
				}
				else{
					if(tick > 0){
						--tick;
					}
					if(tick == 0){
						float brightness = worldObj.getLightBrightness(xCoord, yCoord+1, zCoord);
						if(brightness >= this.getMinLightLevel() && brightness <= this.getMaxLightLevel()){
							cv++;
						}
						tick = this.getTicksPerCV();	
					}
					this.emitting = false;
				}
			}
			else{
				if(tick > 0){
					--tick;
				}
				if(tick == 0){
					float brightness = worldObj.getLightBrightness(xCoord, yCoord+1, zCoord);
					if(brightness >= this.getMinLightLevel() && brightness <= this.getMaxLightLevel()){
						cv++;
					}
					tick = this.getTicksPerCV();	
				}
				this.emitting = false;
			}
		}
		else{
			if(tick > 0){
				--tick;
			}
			if(tick == 0){
				float brightness = worldObj.getLightBrightness(xCoord, yCoord+1, zCoord);
				if(brightness >= this.getMinLightLevel() && brightness <= this.getMaxLightLevel()){
					cv++;
				}
				tick = this.getTicksPerCV();	
			}
			this.emitting = false;
		}
	}

	public boolean isEmitting() {
		return this.emitting;
	}

	public boolean isCollecting() {
		float brightness = worldObj.getLightBrightness(xCoord, yCoord+1, zCoord);
		return brightness >= this.getMinLightLevel() && brightness <= this.getMaxLightLevel();
	}

	public EnumEnergyCollector getCollectorType() {
		// TODO 
		return EnumEnergyCollector.MK2;
	}
	

}
