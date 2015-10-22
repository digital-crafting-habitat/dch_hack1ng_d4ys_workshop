package com.digitalcraftinghabitat.forgemod.item;

import com.digitalcraftinghabitat.forgemod.RefStrings;
import com.digitalcraftinghabitat.forgemod.core.TabDigitalCraftingHabitat;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

/**
 * Created by christopher on 12/08/15.
 */
public class ItemIngotCraftium extends Item {

public static Item itemCraftiumIngot;
    public ItemIngotCraftium() {
        super();
        setCreativeTab(TabDigitalCraftingHabitat.tab);
        setTextureName(RefStrings.MODID + ":craftiumIngot");
        setUnlocalizedName("craftium_ingot");
    }

    @Override
    public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player) {

        if (!player.capabilities.isCreativeMode) {
            stack.stackSize--;
        }
        return stack;
    }


    public static void addRecipes() {
        GameRegistry.addRecipe(new ItemStack(itemCraftiumIngot), new Object[]{
                " D ",
                "DCD",
                " D ",
                'D', new ItemStack(DustCraftium.dustCraftium), 'C', Items.iron_ingot
        });
    }

    public static void register() {
        GameRegistry.registerItem(itemCraftiumIngot = new ItemIngotCraftium(), itemCraftiumIngot.getUnlocalizedName());
    }
}
