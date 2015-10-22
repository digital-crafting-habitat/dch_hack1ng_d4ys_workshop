package com.digitalcraftinghabitat.forgemod.tileentity;

import com.digitalcraftinghabitat.forgemod.RefStrings;
import com.digitalcraftinghabitat.forgemod.datahub.client.DatahubClientConnector;
import com.digitalcraftinghabitat.forgemod.event.consumer.ValueUpdateEvent;
import com.digitalcraftinghabitat.forgemod.util.DCHConfiguration;
import com.digitalcraftinghabitat.forgemod.util.DCHLog;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.MinecraftForge;

import java.io.IOException;

/**
 * Created by Rauca on 26.08.2015.
 */
public class RedisValueEntity extends TileEntity {
    static DatahubClientConnector datahubClientConnector;

    public String customField;// = GuiWindow.dchId; //TODO GUI
    private boolean active;
    private int count;

    public RedisValueEntity() {
        if (datahubClientConnector == null) {
            datahubClientConnector = new DatahubClientConnector();
        }
    }

    public static void init() {
        GameRegistry.registerTileEntity(RedisValueEntity.class, RefStrings.MODID + "redis_tile_entity");
    }

    @Override
    public void updateEntity() {
        if (worldObj.isRemote) {
            return;
        } else {
            if ((customField == null) || (customField.length() == 0)) {
                DCHLog.info("Custom Field is null");
                customField = DCHConfiguration.getInstance().getJedis_prefix() + count2++;
                MinecraftForge.EVENT_BUS.register(this);
                //customField = GuiWindow.dchId; //TODO GUI
            }
        }
        setTime();
    }


    @SubscribeEvent
    public void onCommand(ValueUpdateEvent tce) throws IOException {
        if (tce.getId().equals(customField)) {
            DCHLog.info("get Event with id: " + tce.getId() + " value:" + tce.getValue());

            boolean oldValue = active;

            if (tce.getValue() == 1) {
                active = true;
            } else {
                active = false;
            }

            if (oldValue != active) {
                DCHLog.info("new Value detected " + active);
                worldObj.notifyBlockChange(xCoord, yCoord, zCoord, blockType);
            }
        }
    }

    private void setTime() {
        int time;
        int lightvalue = datahubClientConnector.getIntValueForKey("light_sensor");

        time = lightvalue * 40;
        worldObj.setWorldTime(time);
    }

    public boolean isActive() {
        return active;
    }

    @Override
    public void writeToNBT(NBTTagCompound par1) {
        par1.setString("customField", customField);
        par1.setBoolean("active", active);
        super.writeToNBT(par1);
    }

    private static int count2 = 12;

    @Override
    public void readFromNBT(NBTTagCompound par1) {
        this.customField = par1.getString("customField");
        if ((customField == null) || (customField.length() == 0)) {
            customField = DCHConfiguration.getInstance().getJedis_prefix() + count2++;
        }
        MinecraftForge.EVENT_BUS.register(this);
        this.active = par1.getBoolean("active");
        super.readFromNBT(par1);
    }

    public String getCustomField() {
        return customField;
    }

    public void setCustomField(String customField) {
        this.customField = customField;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public void onDataPacket(NetworkManager net, S35PacketUpdateTileEntity packet) {
        readFromNBT(packet.func_148857_g());
    }

}
