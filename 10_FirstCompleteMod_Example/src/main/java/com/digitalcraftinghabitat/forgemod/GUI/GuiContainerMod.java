package com.digitalcraftinghabitat.forgemod.GUI;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.item.ItemStack;

/**
 * Created by Rauca on 17.09.2015.
 */
public class GuiContainerMod extends Container {

    public GuiContainerMod(EntityPlayer player) {
    }

    @Override
    public boolean canInteractWith(EntityPlayer player) {
        return true;
    }


    protected void bindPlayerInventory(InventoryPlayer inventoryPlayer) {
    }

    @Override
    public ItemStack transferStackInSlot(EntityPlayer player, int slot) {
        return null;
    }
}
