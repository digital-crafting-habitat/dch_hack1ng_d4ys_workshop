package com.digitalcraftinghabitat.forgemod.item;

import com.digitalcraftinghabitat.forgemod.RefStrings;
import com.digitalcraftinghabitat.forgemod.core.TabDigitalCraftingHabitat;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.item.Item;

/**
 * Created by Rauca on 25.08.2015
 */
public class DustCraftium extends Item {
    public static Item dustCraftium;

    public DustCraftium() {
        setUnlocalizedName("dustCraftium");
        setCreativeTab(TabDigitalCraftingHabitat.tab);
        setTextureName(RefStrings.MODID + ":craftium_dust");
    }

    public static void register() {
        GameRegistry.registerItem(dustCraftium = new DustCraftium(), dustCraftium.getUnlocalizedName());
    }
}
