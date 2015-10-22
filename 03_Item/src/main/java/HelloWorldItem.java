import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.registry.GameRegistry;

@Mod(modid = HelloWorldItem.MODID, version = HelloWorldItem.VERSION)
public class HelloWorldItem {
    public static final String MODID = "helloworlditem";
    public static final String VERSION = "1.0";

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
//        Minecraft.getMinecraft().scheduleResourcesRefresh(); //Refresh the Resources
    }

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        ItemHelloWorld itemHelloWorld = new ItemHelloWorld(); //TODO Implement ItemHelloWorld()
        GameRegistry.registerItem(itemHelloWorld, itemHelloWorld.getUnlocalizedName());
    }


}