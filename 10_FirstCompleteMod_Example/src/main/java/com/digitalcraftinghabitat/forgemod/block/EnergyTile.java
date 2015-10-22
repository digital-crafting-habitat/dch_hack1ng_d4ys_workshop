package com.digitalcraftinghabitat.forgemod.block;

import cofh.api.energy.IEnergyConnection;
import cofh.api.energy.IEnergyHandler;
import cofh.api.energy.IEnergyProvider;
import com.digitalcraftinghabitat.forgemod.util.DCHLog;
import cpw.mods.fml.common.eventhandler.Event;
import ic2.api.energy.event.EnergyTileLoadEvent;
import ic2.api.energy.event.EnergyTileUnloadEvent;
import ic2.api.energy.tile.IEnergyEmitter;
import ic2.api.energy.tile.IEnergySource;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.util.ForgeDirection;

/**
 * Created by christopher on 11/08/15.
 */
public class EnergyTile extends TileEntity implements IInventory , IEnergyHandler, IEnergyConnection, IEnergyProvider, IEnergyEmitter, IEnergySource {

    public int customField;

    public EnergyTile() {
        super();
    }

    @Override
    public int getSizeInventory() {
        return 64;
    }

    @Override
    public ItemStack getStackInSlot(int p_70301_1_) {
        return new ItemStack(Items.brewing_stand);
    }

    @Override
    public ItemStack decrStackSize(int p_70298_1_, int p_70298_2_) {
        return new ItemStack(Items.chainmail_boots);
    }

    @Override
    public ItemStack getStackInSlotOnClosing(int p_70304_1_) {
        return new ItemStack(Items.diamond_boots);
    }

    @Override
    public void setInventorySlotContents(int p_70299_1_, ItemStack p_70299_2_) {
        DCHLog.info("setInventorySlotContents");
    }


    @Override
    public String getInventoryName() {
        return "Crafting Energy";
    }

    @Override
    public boolean hasCustomInventoryName() {
        return true;
    }

    @Override
    public int getInventoryStackLimit() {
        return 64;
    }

    @Override
    public boolean isUseableByPlayer(EntityPlayer p_70300_1_) {
        return true;
    }

    @Override
    public void openInventory() {
        DCHLog.info("openInventory");
    }

    @Override
    public void closeInventory() {
        DCHLog.info("closeInventory");
    }

    @Override
    public boolean isItemValidForSlot(int p_94041_1_, ItemStack p_94041_2_) {
        return true;
    }

    @Override
    public void setWorldObj(World p_145834_1_) {
        super.setWorldObj(p_145834_1_);
        MinecraftForge.EVENT_BUS.post((Event) new EnergyTileLoadEvent(this));
    }



    @Override
    public void writeToNBT(NBTTagCompound par1) {
        super.writeToNBT(par1);
        par1.setInteger("customField", customField);
    }


    @Override
    public void readFromNBT(NBTTagCompound par1) {
        super.readFromNBT(par1);
        this.customField = par1.getInteger("customField");
    }
    @Override
    public int receiveEnergy(ForgeDirection forgeDirection, int i, boolean b) {
        System.out.println(" ; " + forgeDirection + " , " + i + " , " + b);
        return i;
    }

    @Override
    public int extractEnergy(ForgeDirection forgeDirection, int i, boolean b) {
        System.out.println(" ; " + forgeDirection + " , " + i + " , " + b);
        return i;
    }

    @Override
    public int getEnergyStored(ForgeDirection forgeDirection) {
        System.out.println(" ; " + forgeDirection);
        return 0;
    }

    @Override
    public int getMaxEnergyStored(ForgeDirection forgeDirection) {
        System.out.println(" ; " + forgeDirection);
        return 0;
    }

    @Override
    public boolean canConnectEnergy(ForgeDirection forgeDirection) {
        System.out.println(" ; " + forgeDirection);
        return false;
    }


    @Override
    public boolean emitsEnergyTo(TileEntity tileEntity, ForgeDirection forgeDirection) {
        return true;
    }

    @Override
    public double getOfferedEnergy() {
        return 5;
    }

    @Override
    public void drawEnergy(double v) {
        System.out.println(v);
    }

    @Override
    public int getSourceTier() {
        return 1;
    }

    public void onDestroyed() {
        MinecraftForge.EVENT_BUS.post(new EnergyTileUnloadEvent(this));
    }
}
