package com.digitalcraftinghabitat.forgemod.util;


import org.apache.logging.log4j.Level;

import cpw.mods.fml.relauncher.FMLRelaunchLog;

/**
 * Created by christopher on 02/08/15.
 */
public class DCHLog {

    public static final FMLRelaunchLog INSTANCE = FMLRelaunchLog.log;

    private DCHLog(){

    }

    public static void warning( String format, Object... data )
    {
        log( Level.WARN, format, data );
    }

    private static void log( Level level, String format, Object... data )
    {
        FMLRelaunchLog.log( "DCH:", level, format, data );
    }

    public static void error( Throwable e )
    {
        severe( "Error: " + e.getClass().getName() + " : " + e.getMessage() );
        e.printStackTrace();
    }

    public static void severe( String format, Object... data )
    {
        log( Level.ERROR, format, data );
    }

    public static void blockUpdate( int xCoord, int yCoord, int zCoord, String title)
    {
        info( title + " @ " + xCoord + ", " + yCoord + ", " + zCoord );
    }

    public static void info( String format, Object... data )
    {
        log( Level.INFO, format, data );
    }

    public static void crafting( String format, Object... data )
    {
        log( Level.INFO, format, data );
    }
}
