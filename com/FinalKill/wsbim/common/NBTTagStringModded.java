package com.FinalKill.wsbim.common;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTSizeTracker;
import net.minecraft.nbt.NBTTagString;

public class NBTTagStringModded extends NBTTagString
{
    /** The string value for the tag (cannot be empty). */
    private String data;
    private static final String __OBFID = "CL_00001228";

    public NBTTagStringModded()
    {
        this.data = "";
    }

    public NBTTagStringModded(String p_i1389_1_)
    {
        this.data = p_i1389_1_;

        if (p_i1389_1_ == null)
        {
            throw new IllegalArgumentException("Empty string not allowed");
        }
    }

    public String toString()
    {
        return this.data;
    }


}