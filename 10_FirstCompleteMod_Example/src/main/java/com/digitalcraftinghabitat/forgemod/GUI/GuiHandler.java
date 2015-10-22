package com.digitalcraftinghabitat.forgemod.GUI;

import cpw.mods.fml.common.network.IGuiHandler;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

/**
 * Created by Rauca on 17.09.2015.
 */
public class GuiHandler implements IGuiHandler {
    @Override
    public Object getServerGuiElement(int id, EntityPlayer player, World world, int x, int y, int z) {
        if (id == GUIID.GUIID) return new GuiContainerMod(player);
        return null;
    }

    @Override
    public Object getClientGuiElement(int id, EntityPlayer player, World world, int x, int y, int z) {
        if (id == GUIID.GUIID) return new GuiWindow(world, x, y, z, player);
        return null;
    }
}
