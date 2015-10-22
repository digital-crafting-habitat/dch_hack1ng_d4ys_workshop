import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

/**
 * Created by Rauca on 25.08.2015
 */
public class ItemCraftiumDust extends Item {
    public static Item dustCraftium;

    public ItemCraftiumDust() {
        setUnlocalizedName("dustCraftium");
        setCreativeTab(CreativeTabs.tabMaterials);
        setTextureName(OreMod.MODID + ":craftium_dust");
    }

    public static void register() {
        GameRegistry.registerItem(dustCraftium = new ItemCraftiumDust(), dustCraftium.getUnlocalizedName());
    }

    public static void addRecipes() {
        addCraftingRecipes();
        addOvenRecipes();
    }

    private static void addCraftingRecipes() {
        GameRegistry.addRecipe(new ItemStack(dustCraftium), new Object[]{
                " D ",
                "DCD",
                " D ",
                'C', Items.redstone, 'D', Items.coal
        });
    }

    public static void addOvenRecipes() {
        GameRegistry.addSmelting(new ItemStack(OreCraftium.oreCraftium), new ItemStack(dustCraftium, 2), 2.0F);
    }
}
