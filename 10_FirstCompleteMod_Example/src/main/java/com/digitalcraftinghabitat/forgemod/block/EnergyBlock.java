package com.digitalcraftinghabitat.forgemod.block;

import buildcraft.core.lib.utils.Utils;
import cofh.api.energy.IEnergyConnection;
import cofh.api.energy.IEnergyHandler;
import cofh.api.energy.IEnergyProvider;
import com.digitalcraftinghabitat.forgemod.core.TabDigitalCraftingHabitat;
import com.digitalcraftinghabitat.forgemod.util.DCHLog;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import ic2.api.energy.tile.IEnergyEmitter;
import ic2.api.energy.tile.IEnergySource;
import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

/**
 * Created by christopher on 09/08/15.
 */
public class EnergyBlock extends Block implements ITileEntityProvider{

    EnergyTile energyTile = null;
    public static final IIcon[] TEXTURES = new IIcon[1];

    public EnergyBlock() {
        super(Material.rock);
        setHardness(3.0F);
        setResistance(5.0F);
        setStepSound(soundTypeGlass);
        setCreativeTab(TabDigitalCraftingHabitat.tab);
        setBlockName("energy_block");
    }

    @Override
    @SideOnly(Side.CLIENT)
    public boolean onBlockActivated(World world, int x,
                                    int y, int z, EntityPlayer p_149727_5_,
                                    int p_149727_6_, float p_149727_7_, float p_149727_8_, float p_149727_9_) {
        if (world.isRemote) {
            return false;
        }
        Vec3 position = p_149727_5_.getPosition(1.0F);
        ItemStack itemStack = new ItemStack(Items.diamond);
        EntityItem toolItem = new EntityItem(world, x, y, z, itemStack);
        Utils.addToRandomInjectableAround(world, x, y, z, ForgeDirection.UNKNOWN, itemStack);
        world.notifyBlockOfNeighborChange(x, y, z, this);
        return true;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public int onBlockPlaced(World world, int x, int y, int z, int p_149660_5_, float p_149660_6_, float p_149660_7_, float p_149660_8_, int p_149660_9_) {
        if (world.isRemote)
            return 0;
        world.notifyBlockOfNeighborChange(x, y, z, this);
        return super.onBlockPlaced(world, x, y, z, p_149660_5_, p_149660_6_, p_149660_7_, p_149660_8_, p_149660_9_);

    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister ir) {
        TEXTURES[0] = ir.registerIcon("digitalcraftinghabitat:ore/dch_logo_block");
    }

    @Override
    public IIcon getIcon(int side, int metadata) {
        return TEXTURES[metadata];
    }

    public TileEntity createTileEntity(World world, int metadata) {
        return new EnergyTile();
    }

    public EnergyTile getEnergyTile() {
        return energyTile;
    }

    public void setEnergyTile(EnergyTile energyTile) {
        this.energyTile = energyTile;
    }

    @Override
    public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_) {

        energyTile = new EnergyTile();
        return energyTile;
    }

    @Override
    public void onBlockDestroyedByPlayer(World p_149664_1_, int p_149664_2_, int p_149664_3_, int p_149664_4_, int p_149664_5_) {
        if (p_149664_1_.isRemote){
            return;
        }
        EnergyTile entity = (EnergyTile) p_149664_1_.getTileEntity(p_149664_2_, p_149664_3_, p_149664_4_);
        if (entity != null){
            entity.onDestroyed();
        }else {
            DCHLog.warning("Energy Tile was null at : " +p_149664_2_ + " "+p_149664_3_ +" " +p_149664_4_ +" " );
        }

        super.onBlockDestroyedByPlayer(p_149664_1_, p_149664_2_, p_149664_3_, p_149664_4_, p_149664_5_);
    }
}

