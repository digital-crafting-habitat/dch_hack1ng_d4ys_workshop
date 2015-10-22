package com.digitalcraftinghabitat.forgemod.block;

import com.digitalcraftinghabitat.forgemod.core.TabDigitalCraftingHabitat;
import com.digitalcraftinghabitat.forgemod.datahub.client.DatahubClientConnector;
import com.digitalcraftinghabitat.forgemod.util.DCHLog;
import net.minecraft.block.BlockLever;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

/**
 * Created by christopher on 18/08/15.
 */
public class CraftingLever extends BlockLever {

    static DatahubClientConnector datahubClientConnector;

    public CraftingLever() {
        super();
        if (datahubClientConnector == null) {
            datahubClientConnector = new DatahubClientConnector();
        }
        setBlockName("crafting_lever_block");
        setCreativeTab(TabDigitalCraftingHabitat.tab);
        this.setResistance(3.0F);
        this.setHardness(5.0F);
    }

    @Override
    public int onBlockPlaced(World p_149660_1_, int p_149660_2_, int p_149660_3_, int p_149660_4_, int p_149660_5_, float p_149660_6_, float p_149660_7_, float p_149660_8_, int p_149660_9_) {
        int superReturnValue = super.onBlockPlaced(p_149660_1_, p_149660_2_, p_149660_3_, p_149660_4_, p_149660_5_, p_149660_6_, p_149660_7_, p_149660_8_, p_149660_9_);
        DCHLog.info("CraftingLever: it works");
        return superReturnValue;
    }

    @Override
    public boolean onBlockActivated(World p_149727_1_, int p_149727_2_, int p_149727_3_, int p_149727_4_, EntityPlayer p_149727_5_, int p_149727_6_, float p_149727_7_, float p_149727_8_, float p_149727_9_) {
        if (p_149727_1_.isRemote) {
            return true;
        }

        int blockMetadata = p_149727_1_.getBlockMetadata(p_149727_2_, p_149727_3_, p_149727_4_);
        boolean isActivated = blockMetadata >= 8;
        DCHLog.info("Value?= " + p_149727_6_);
        boolean bol = super.onBlockActivated(p_149727_1_, p_149727_2_, p_149727_3_, p_149727_4_, p_149727_5_, p_149727_6_, p_149727_7_, p_149727_8_, p_149727_9_);
        int test = datahubClientConnector.getIntValueForKey("lever_value");

        if (test == 1 && isActivated) {
            datahubClientConnector.setValueForKey("lever_value", "0");
        } else if (!isActivated) {
            datahubClientConnector.setValueForKey("lever_value", "1");
        }
        return bol;
    }
}
