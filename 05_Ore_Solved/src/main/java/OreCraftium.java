import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

import java.util.Random;

/**
 * Created by Rauca on 25.08.2015.
 */
public class OreCraftium extends Block {
    public static Block oreCraftium;

    protected OreCraftium(Material material) {
        super(material);
        setBlockName("oreCraftium");
        setCreativeTab(CreativeTabs.tabBlock);
        setBlockTextureName(OreMod.MODID + ":ore/Ore_Craftium");
        setResistance(3.0F);
        setHardness(5.0F);
        setLightLevel(0.5F);
        setHarvestLevel("pickaxe", 2);
        setStepSound(soundTypeStone);
    }

    public static void register() {
        GameRegistry.registerBlock(oreCraftium = new OreCraftium(Material.rock), oreCraftium.getUnlocalizedName());
    }

    @Override
    public int quantityDropped(Random random) {
        return 2 + random.nextInt(3);
    }

    @Override
    public Item getItemDropped(int meta, Random random, int fortune) {
        return ItemCraftiumDust.dustCraftium;
    }
}
