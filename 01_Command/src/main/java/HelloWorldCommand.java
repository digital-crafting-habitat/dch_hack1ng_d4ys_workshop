import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;
import net.minecraft.client.Minecraft;


@Mod(modid = HelloWorldCommand.MODID, version = HelloWorldCommand.VERSION)
public class HelloWorldCommand {
    public static final String MODID = "helloworldcommand";
    public static final String VERSION = "1.0";

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        Minecraft.getMinecraft().scheduleResourcesRefresh(); //Refresh the Resources
    }

    @Mod.EventHandler
    public void start(FMLServerStartingEvent event) {
        event.registerServerCommand(new CommandHello()); //TODO Implement "CommandHello()"
    }
    // Tutorial: http://www.minecraftforge.net/wiki/Server_Command
}
