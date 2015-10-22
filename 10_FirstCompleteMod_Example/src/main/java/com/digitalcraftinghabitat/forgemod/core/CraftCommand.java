package com.digitalcraftinghabitat.forgemod.core;

import com.digitalcraftinghabitat.forgemod.event.consumer.ValueUpdateEvent;
import com.digitalcraftinghabitat.forgemod.tileentity.RedisValueEntity;
import com.digitalcraftinghabitat.forgemod.util.DCHLog;
import net.minecraft.command.ICommand;
import net.minecraft.command.ICommandSender;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.MinecraftForge;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by christopher on 09/08/15.
 */
public class CraftCommand implements ICommand {


    @Override
    public String getCommandName() {
        return "craft";
    }

    @Override
    public String getCommandUsage(ICommandSender p_71518_1_) {
        return "/craft set_id <X> <Y> <Z> newID (must be numeric)";
    }

    @Override
    public List getCommandAliases() {
        List getEnergy = new ArrayList();
        getEnergy.add("craft");
        return getEnergy;
    }

    @Override
    public void processCommand(ICommandSender sender, String[] commandParams) {
        if ((commandParams != null) && (commandParams.length > 4)) {
            if (commandParams[0].equals("set_id")) {
                DCHLog.warning("set id called");
                DCHLog.warning("coords X: " + commandParams[1] + " Y: " + commandParams[2] + " Z: " + commandParams[3]);
                int x = Integer.parseInt(commandParams[1]);
                int y = Integer.parseInt(commandParams[2]);
                int z = Integer.parseInt(commandParams[3]);
                TileEntity tileEntity = sender.getEntityWorld().getTileEntity(x, y, z);
                if ((tileEntity != null) && (tileEntity instanceof RedisValueEntity)) {
                    try {
                        RedisValueEntity redisValueEntity = (RedisValueEntity) sender.getEntityWorld().getTileEntity(x, y, z);
                        if ((commandParams[4] != null) && (!commandParams[4].isEmpty())) {
                            try {
                                redisValueEntity.setCustomField(commandParams[4]);
                                DCHLog.warning("set new id " + commandParams[4] + " to entity");
                            } catch (NumberFormatException e) {
                                DCHLog.error(e);
                            }
                        }
                    } catch (ClassCastException e) {
                        DCHLog.error(e);
                    }
                } else {
                    if ((tileEntity == null)) {
                        DCHLog.warning("tile at coords is null");
                    }
                }

            }
        }
        else{
            ValueUpdateEvent valueUpdateEvent = new ValueUpdateEvent(sender.getEntityWorld());
            valueUpdateEvent.setValue(42);
            MinecraftForge.EVENT_BUS.post(valueUpdateEvent);
            System.out.println("bkad");
        }
    }

    @Override
    public boolean canCommandSenderUseCommand(ICommandSender p_71519_1_) {
        return true;
    }

    @Override
    public List addTabCompletionOptions(ICommandSender p_71516_1_, String[] p_71516_2_) {
        return null;
    }

    @Override
    public boolean isUsernameIndex(String[] p_82358_1_, int p_82358_2_) {
        return false;
    }

    @Override
    public int compareTo(Object o) {
        return 0;
    }
}
