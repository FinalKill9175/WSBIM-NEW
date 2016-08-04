package com.FinalKill.Machinery_Craft;


import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class CondenserRecipies
{
public CondenserRecipies()
{
}
public static ItemStack getSmeltingResult(Item i)
{
         return getOutput(i);
}
private static ItemStack getOutput(Item i)
{
         if (i == Items.coal)
         {
                 return new ItemStack(Items.gold_ingot);
         }
        
return null;
}
}
