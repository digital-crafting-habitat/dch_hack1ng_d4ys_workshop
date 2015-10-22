package com.digitalcraftinghabitat.forgemod.core;

import com.digitalcraftinghabitat.forgemod.block.OreCraftium;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

/**
 * Created by Rauca on 16.09.2015.
 */
public class TabDigitalCraftingHabitat {
    public static CreativeTabs tab = new CreativeTabs("tabdigitalCraftingHabitat") {
        @SideOnly(Side.CLIENT)
        public Item getTabIconItem() {
            return Item.getItemFromBlock(OreCraftium.oreCraftium);
        }
    };
}