import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.event.FMLEvent;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.registry.GameRegistry;

@Mod(modid = OreMod.MODID, version = OreMod.VERSION)
public class OreMod {
    public static final String MODID = "oremod";
    public static final String VERSION = "1.0";

    public WorldGen worldGen;

    @EventHandler
    public void preLoad(FMLPreInitializationEvent event) {
        OreCraftium.register();
        ItemCraftiumDust.register();
        worldGen = new WorldGen();
    }

    @EventHandler
    public void init(FMLInitializationEvent event) {
        ItemCraftiumDust.addRecipes();
        GameRegistry.registerWorldGenerator(worldGen, 1);
    }

    @EventHandler
    public void postInit(FMLPostInitializationEvent event) {
//        Minecraft.getMinecraft().scheduleResourcesRefresh();
    }

    @EventHandler
    public void init(FMLEvent event) {

    }

}