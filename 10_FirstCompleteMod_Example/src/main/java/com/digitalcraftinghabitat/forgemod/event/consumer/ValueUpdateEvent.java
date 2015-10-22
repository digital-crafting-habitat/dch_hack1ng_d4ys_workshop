package com.digitalcraftinghabitat.forgemod.event.consumer;

import net.minecraft.world.World;
import net.minecraftforge.event.world.WorldEvent;

/**
 * Created by christopher on 23/09/15.
 */
public class ValueUpdateEvent extends WorldEvent{
    private int value;
    private String id;

    public ValueUpdateEvent(World world) {
        super(world);
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

}
