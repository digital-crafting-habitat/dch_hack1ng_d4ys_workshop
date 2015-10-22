package com.digitalcraftinghabitat.forgemod.GUI;

import cpw.mods.fml.common.FMLCommonHandler;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;

/**
 * Created by Rauca on 17.09.2015.
 */
public class GuiWindow extends GuiContainer {
    public static int dchId = 1; //TODO static -> non-static for every Block
    int i = 0;
    int j = 0;
    int k = 0;
    EntityPlayer entity = null;


    public GuiWindow(World world, int i, int j, int k, EntityPlayer entity) {
        super(new GuiContainerMod((EntityPlayer) entity));
        this.i = i;
        this.j = j;
        this.k = k;
        this.entity = entity;
    }

    private static final ResourceLocation texture = new ResourceLocation("GUIBackground.png");

    protected void drawGuiContainerBackgroundLayer(float par1, int par2, int par3) {
        int posX = (this.width) / 2;
        int posY = (this.height) / 2;
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);

        this.mc.renderEngine.bindTexture(texture);
        this.xSize = 230;
        this.ySize = 122;
        int k = (this.width - this.xSize) / 2;
        int l = (this.height - this.ySize) / 2;
        this.drawTexturedModalRect(k, l, 0, 0, this.xSize, this.ySize);


        zLevel = 100.0F;


    }

    protected void mouseClicked(int par1, int par2, int par3) {
        super.mouseClicked(par1, par2, par3);

    }

    public void updateScreen() {
        int posX = (this.width) / 2;
        int posY = (this.height) / 2;

    }

    protected void keyTyped(char par1, int par2) {


        if (par2 != 28 && par2 != 156) {
            if (par2 == 1) {
                this.mc.displayGuiScreen((GuiScreen) null);
            }
        }

    }

    protected void drawGuiContainerForegroundLayer(int par1, int par2) {
        int posX = (this.width) / 2;
        int posY = (this.height) / 2;
        this.fontRendererObj.drawString("SetID", posX + (-29), posY + (-50), 0x0000ff);
        this.fontRendererObj.drawString("" + dchId + "", posX + (1), posY + (-10), 0xffffff);

    }

    public void onGuiClosed() {
        Keyboard.enableRepeatEvents(false);
    }

    public void initGui() {
        Keyboard.enableRepeatEvents(true);
        this.buttonList.clear();
        int posX = (this.width) / 2;
        int posY = (this.height) / 2;
        this.buttonList.add(new GuiButton(0, posX + (30), posY + (-10), 20, 20, "+ 1"));
        this.buttonList.add(new GuiButton(1, posX + (60), posY + (-10), 40, 20, "+ 10"));
        this.buttonList.add(new GuiButton(2, posX + (-30), posY + (-10), 20, 20, "- 1"));
        this.buttonList.add(new GuiButton(3, posX + (-79), posY + (-10), 40, 20, "- 10"));

    }

    protected void actionPerformed(GuiButton button) {
        MinecraftServer server = FMLCommonHandler.instance().getMinecraftServerInstance();
        World world = server.worldServers[0];

        switch (button.id) {
            case 0:
                dchId += 1;
                break;
            case 1:
                dchId += 10;
                break;
            case 2:
                dchId -= 1;
                break;
            case 3:
                dchId -= 10;
                break;
        }
    }

    public boolean doesGuiPauseGame() {
        return false;
    }

}
