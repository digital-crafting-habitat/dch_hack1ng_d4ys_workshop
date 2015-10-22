import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;
import net.minecraft.client.Minecraft;

@Mod(modid = HelloWorldCommandSolved.MODID, version = HelloWorldCommandSolved.VERSION)
public class HelloWorldCommandSolved {
    public static final String MODID = "helloworldcommandSolved";
    public static final String VERSION = "1.0";

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        Minecraft.getMinecraft().scheduleResourcesRefresh();
    }

    @Mod.EventHandler
    public void starting(FMLServerStartingEvent event) {
        event.registerServerCommand(new CommandHelloSolved());
    }


}