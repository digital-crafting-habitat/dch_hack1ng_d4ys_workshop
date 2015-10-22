package com.digitalcraftinghabitat.forgemod.util;

import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;

/**
 * Created by christopher on 17/08/15.
 */
public class DCHUtils {


    public static ChatComponentText getChatMessageFromText(String message) {
        return new ChatComponentText(EnumChatFormatting.DARK_PURPLE + message);
    }

}
