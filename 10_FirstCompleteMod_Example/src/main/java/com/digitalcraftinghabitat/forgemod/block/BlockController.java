package com.digitalcraftinghabitat.forgemod.block;

import com.digitalcraftinghabitat.forgemod.RefStrings;
import com.digitalcraftinghabitat.forgemod.core.TabDigitalCraftingHabitat;
import com.digitalcraftinghabitat.forgemod.util.DCHUtils;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

/**
 * Created by Rauca on 25.08.2015.
 */
public class BlockController extends Block {
    public static Block blockController;
    private World world;
    boolean active = false;

    protected BlockController(Material material) {
        super(material);
        setBlockName("blockController");
        setCreativeTab(TabDigitalCraftingHabitat.tab);
        setBlockTextureName(RefStrings.MODID + ":ore/dch_logo_block");
        setBlockUnbreakable();
    }

    public static void register() {
        GameRegistry.registerBlock(blockController = new BlockController(Material.circuits), blockController.getUnlocalizedName());
    }

    @Override
    public boolean onBlockActivated(World p_149727_1_, int p_149727_2_, int p_149727_3_, int p_149727_4_, EntityPlayer p_149727_5_, int p_149727_6_, float p_149727_7_, float p_149727_8_, float p_149727_9_) {
        if (p_149727_1_.isRemote){
            return false;
        }

        this.world = p_149727_1_;

        if (active){
            active = false;
            ControllerRefreshThread.kill(world);
            p_149727_5_.addChatComponentMessage(DCHUtils.getChatMessageFromText("Updates deactivated"));
        }else {
            active = true;
            ControllerRefreshThread.init(world);
            p_149727_5_.addChatComponentMessage(DCHUtils.getChatMessageFromText("Updates activated"));
        }
        return super.onBlockActivated(p_149727_1_, p_149727_2_, p_149727_3_, p_149727_4_, p_149727_5_, p_149727_6_, p_149727_7_, p_149727_8_, p_149727_9_);
    }

    @Override
    public boolean canPlaceBlockOnSide(World p_149707_1_, int p_149707_2_, int p_149707_3_, int p_149707_4_, int p_149707_5_) {
        return super.canPlaceBlockOnSide(p_149707_1_, p_149707_2_, p_149707_3_, p_149707_4_, p_149707_5_);
    }
}


