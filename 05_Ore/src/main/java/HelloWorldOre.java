import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.event.FMLEvent;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

import static cpw.mods.fml.common.Mod.*;

@Mod(modid = HelloWorldOre.MODID, version = HelloWorldOre.VERSION)
public class HelloWorldOre {
    public static final String MODID = "assets/oremod";
    public static final String VERSION = "1.0";

    public WorldGen worldGen;

    @EventHandler
    public void preLoad(FMLPreInitializationEvent event) {
        //TODO Load Items/Blocks
        //TODO Initialize WorldGen
    }

    @EventHandler
    public void init(FMLInitializationEvent event) {
        //TODO Initialisiere Rezepte
        //TODO Registriere WorldGen
    }

    @EventHandler
    public void postInit(FMLPostInitializationEvent event) {
//        Minecraft.getMinecraft().scheduleResourcesRefresh();
    }

    @EventHandler
    public void init(FMLEvent event) {

    }

}