package com.digitalcraftinghabitat.forgemod.block;

import com.digitalcraftinghabitat.forgemod.core.TabDigitalCraftingHabitat;
import com.digitalcraftinghabitat.forgemod.item.ItemCraftiumCoal;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.Item;
import net.minecraft.util.IIcon;

import java.util.Random;

/**
 * Created by christopher on 16/08/15.
 */
public class BlockCraftium extends Block {

    public static final IIcon[] TEXTURES = new IIcon[1];


    public BlockCraftium() {
        super(Material.rock);
        setBlockName("craftium_block");
        setCreativeTab(TabDigitalCraftingHabitat.tab);
        this.setResistance(3.0F);
        this.setHardness(5.0F);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister ir) {
        TEXTURES[0] = ir.registerIcon("digitalcraftinghabitat:ore/Ore_Craftium");
    }

    @Override
    public IIcon getIcon(int side, int metadata) {
        return TEXTURES[metadata];
    }

    @Override
    public int quantityDropped(Random p_149745_1_) {
        return 2;
    }

    @Override
    public Item getItemDropped(int metadata, Random random, int fortune) {
        return ItemCraftiumCoal.craftiumCoal;
    }
}
