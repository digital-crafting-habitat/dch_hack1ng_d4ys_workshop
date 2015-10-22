import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.client.Minecraft;

@Mod(modid = HelloWorldItemSolved.MODID, version = HelloWorldItemSolved.VERSION)
public class HelloWorldItemSolved {
    public static final String MODID = "helloworlditemsolved";
    public static final String VERSION = "1.0";

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        Minecraft.getMinecraft().scheduleResourcesRefresh(); //Refresh the Resources
    }

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        ItemHelloWorldSolved itemHelloWorldSolved = new ItemHelloWorldSolved();
        GameRegistry.registerItem(itemHelloWorldSolved, itemHelloWorldSolved.getUnlocalizedName());
    }


}