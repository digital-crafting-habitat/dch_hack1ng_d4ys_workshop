import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;


@Mod(modid = HelloWorldBlockSolved.MODID, version = HelloWorldBlockSolved.VERSION)
public class HelloWorldBlockSolved {
    public static final String MODID = "helloworldblocksolved";
    public static final String VERSION = "1.0";

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        Minecraft.getMinecraft().scheduleResourcesRefresh(); //Refresh the Resources
    }

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        BlockHelloWorldSolved blockHelloWorldSolved = new BlockHelloWorldSolved(Material.rock); //TODO Create the "BlockHelloWorldSolved"-Block with own Texture
        GameRegistry.registerBlock(blockHelloWorldSolved, blockHelloWorldSolved.getUnlocalizedName());
    }

}
