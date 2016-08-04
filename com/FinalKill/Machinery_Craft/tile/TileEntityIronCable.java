package com.FinalKill.Machinery_Craft.tile;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.ForgeDirection;

import com.FinalKill.MachineryCraftAPI.power.IConnectableBlock;
import com.FinalKill.MachineryCraftAPI.power.IConnectionInput;
import com.FinalKill.MachineryCraftAPI.power.IPowerConductor;
import com.FinalKill.MachineryCraftAPI.power.IPowerUsage;

public class TileEntityIronCable extends TileEntity implements IConnectableBlock, IConnectionInput {

	
	public ForgeDirection[] connections = new ForgeDirection[6];
	public float power = 0.0F;
	public final float voltage = 1.0F;
	private float max_power = 1000F;
	
	/**
	 * UP, DOWN, NORTH, EAST, SOUTH, WEST
	 */
	
	public void updateEntity(){
		//e++this.power;
		this.updateConnections();
			
		
		if(!this.worldObj.isRemote){
		if(this.power<0)this.power = 0;
		

		//
		}
		
	}
	
	

public void updateConnections(){
		
		if((this.worldObj.getTileEntity(xCoord, yCoord+1, zCoord) instanceof IConnectionInput)) connections [0] = ForgeDirection.UP;
		else connections[0] = null;
		if((this.worldObj.getTileEntity(xCoord, yCoord-1, zCoord) instanceof IConnectionInput) ) connections [1] = ForgeDirection.DOWN;
		else connections[1] = null;
		if((this.worldObj.getTileEntity(xCoord, yCoord, zCoord-1) instanceof IConnectionInput) ) connections [2] = ForgeDirection.NORTH;
		else connections[2] = null;
		if((this.worldObj.getTileEntity(xCoord+1, yCoord, zCoord) instanceof IConnectionInput) ) connections [3] = ForgeDirection.EAST;
		else connections[3] = null;
		if((this.worldObj.getTileEntity(xCoord, yCoord, zCoord+1) instanceof IConnectionInput) ) connections [4] = ForgeDirection.SOUTH;
		else connections[4] = null;
		if((this.worldObj.getTileEntity(xCoord-1, yCoord, zCoord) instanceof IConnectionInput) ) connections [5] = ForgeDirection.WEST;
		else connections[5] = null;
		
	}
	
@Override
public void readFromNBT(NBTTagCompound tagCompound) {
        super.readFromNBT(tagCompound);
     
        
       this.power = tagCompound.getFloat("power");
      }

@Override
public void writeToNBT(NBTTagCompound tagCompound) {
        super.writeToNBT(tagCompound);
                        
        
        tagCompound.setFloat("power", this.power);
    
     }



	public boolean onlyOneOpposite(ForgeDirection[] directions){
		
		ForgeDirection mainDirection = null;
		
		boolean isOpposite = false;
		
		for(int i=0; i<directions.length; i++){
			
			if(mainDirection == null && directions[i] !=null)mainDirection = directions[i];
			
			if(directions[i] !=null && mainDirection !=directions[i]){
				if(!isOpposite(mainDirection, directions[i]))return false;
				else isOpposite = true;
			}
		}
		
		return isOpposite;
		
	}
	
	public boolean isOpposite(ForgeDirection firstDirection, ForgeDirection secondDirection){
		
		if(firstDirection.equals(ForgeDirection.NORTH) && secondDirection.equals(ForgeDirection.SOUTH) || firstDirection.equals(ForgeDirection.SOUTH) && secondDirection.equals(ForgeDirection.NORTH)) return true;
		if(firstDirection.equals(ForgeDirection.WEST) && secondDirection.equals(ForgeDirection.EAST) || firstDirection.equals(ForgeDirection.EAST) && secondDirection.equals(ForgeDirection.WEST)) return true;
		if(firstDirection.equals(ForgeDirection.UP) && secondDirection.equals(ForgeDirection.DOWN) || firstDirection.equals(ForgeDirection.DOWN) && secondDirection.equals(ForgeDirection.UP)) return true;
		
	
		return false;
	}



	public ForgeDirection[] getConnections() {

		return this.connections;
	}



	public float getPower() {
		return this.power;
	}



public void setPower(float power) {
		this.power = power;
	}



	public float getPowerDuductionPerBlock() {
		return 3F;
	}



	public void updatePowerInSurroundingConductors() {
		ForgeDirection[] directions = this.getConnections();
		for(int i = 0; i<directions.length; i++){
			
			if(directions[i] !=null){
				
				if(this.getWorldObj().getTileEntity(xCoord+directions[i].offsetX, yCoord+directions[i].offsetY, zCoord+directions[i].offsetZ) instanceof IPowerConductor){
					
					IPowerConductor conductor = (IPowerConductor) this.getWorldObj().getTileEntity(xCoord+directions[i].offsetX, yCoord+directions[i].offsetY, zCoord+directions[i].offsetZ);
							
					
					//	if(conductor.getPower()<this.getPower()){
						this.setPower(conductor.getPower());
					
		
						//}
						
					
					
					
					
				}
				
			}
			
		}
	}



	public void addPower(float power) {
	this.power+=power;	
	}



	public void removePower(float power) {
	this.power-=power;	
	}




	public float powerToUpdateOnTick() {
		return 0.5F;
	}



public float getMaxLoad() {
	return 20F;
}








public void sendPower() {
	
	
	
	ForgeDirection[] directions = this.getConnections();
	for(int i = 0; i<directions.length; i++){
		
		if(directions[i] !=null){
			
			if(this.getWorldObj().getTileEntity(xCoord+directions[i].offsetX, yCoord+directions[i].offsetY, zCoord+directions[i].offsetZ) instanceof IPowerUsage){
				
				IPowerUsage machine = (IPowerUsage) this.getWorldObj().getTileEntity(xCoord+directions[i].offsetX, yCoord+directions[i].offsetY, zCoord+directions[i].offsetZ);
						
				
				if( machine.getPower()<machine.getMaxPower()){
					machine.addPower(machine.powerToUpdateOnTick());

					this.removePower(this.powerToUpdateOnTick());
								}
				
				
				
				
			}
			
		}
		
	}
	
	
	
	
	
}



	



	




}




