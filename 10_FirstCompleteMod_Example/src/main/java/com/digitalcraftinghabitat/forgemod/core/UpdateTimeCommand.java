package com.digitalcraftinghabitat.forgemod.core;

import com.digitalcraftinghabitat.forgemod.block.ControllerRefreshThread;
import net.minecraft.command.ICommand;
import net.minecraft.command.ICommandSender;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by marius.drodten on 18.10.16.
 */
public class UpdateTimeCommand implements ICommand {
    @Override
    public String getCommandName() {
        return "updateTime";
    }

    @Override
    public String getCommandUsage(ICommandSender p_71518_1_) {
        return "/updateTime";
    }

    @Override
    public List getCommandAliases() {
        List updateTime = new ArrayList();
        updateTime.add("updateTime");
        return updateTime;
    }

    @Override
    public void processCommand(ICommandSender p_71515_1_, String[] p_71515_2_) {
        ControllerRefreshThread refreshThread = new ControllerRefreshThread(p_71515_1_.getEntityWorld());
        refreshThread.updateTimeRange();
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
