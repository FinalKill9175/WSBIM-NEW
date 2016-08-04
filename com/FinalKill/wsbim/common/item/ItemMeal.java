package com.FinalKill.wsbim.common.item;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import com.FinalKill.wsbim.WSBIM;

import cpw.mods.fml.common.registry.GameRegistry;

/**Used for meals, can auto register a crafting recipe.*/
public class ItemMeal extends ItemFood{

	private Item[] mealContents;
	
	public ItemMeal(String str, int p_i45339_1_, float p_i45339_2_, Item[] mealContents) {
		super(p_i45339_1_, p_i45339_2_, false);
		this.setUnlocalizedName(str);
		this.setTextureName(WSBIM.modid+":"+str);
		this.mealContents = mealContents;
		this.setMaxStackSize(1);
		this.setCreativeTab(WSBIM.tabFood);
		ItemMeal.registerMeal(this);
	}

	public ItemMeal(String str, int p_i45339_1_, float p_i45339_2_, Item[] mealContents, int potionID, int duration, int amp, float chance) {
		super(p_i45339_1_, p_i45339_2_, false);
		this.setUnlocalizedName(str);
		this.mealContents = mealContents;
		this.setTextureName(WSBIM.modid+":"+str);	
		this.setPotionEffect(potionID, duration, amp, chance);
		this.setMaxStackSize(1);
		this.setCreativeTab(WSBIM.tabFood);
		ItemMeal.registerMeal(this);
	}
	
	public Item[] getMealContents(){
		return mealContents;
	}
	
	public int getMealSize(){
		return this.getMealContents().length;
	}
	
	public static void registerMeal(ItemMeal meal){
		GameRegistry.registerItem(meal, meal.getUnlocalizedName());
		Object[] items = new Object[meal.getMealSize()+1];
		items[0] = WSBIM.main.itemPlate;
		for(int i = 0; i < meal.getMealSize(); i++){
			Item item = meal.getMealContents()[i];
			if(item !=null){
				items[i+1] = item;
			}
		}
		GameRegistry.addShapelessRecipe(new ItemStack(meal), items);
	}
	
	public static Item[] calcMealContents(Item... items){
		return items;
	}
	
    public ItemStack onEaten(ItemStack p_77654_1_, World p_77654_2_, EntityPlayer p_77654_3_)
    {
    	p_77654_1_ = new ItemStack(WSBIM.main.itemPlate);
        p_77654_3_.getFoodStats().func_151686_a(this, p_77654_1_);
        p_77654_2_.playSoundAtEntity(p_77654_3_, "random.burp", 0.5F, p_77654_2_.rand.nextFloat() * 0.1F + 0.9F);
        this.onFoodEaten(p_77654_1_, p_77654_2_, p_77654_3_);
        return p_77654_1_;
    }
}
