import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

import java.util.Random;

public class BlockHelloWorldSolved extends Block {

    protected BlockHelloWorldSolved(Material material) {
        super(material);
        setBlockName("oreCraftium");
        setCreativeTab(CreativeTabs.tabBlock);
        setBlockTextureName(HelloWorldBlockSolved.MODID + ":Ore_Craftium");
        setResistance(3.0F);
        setHardness(5.0F);
        setLightLevel(0.5F);
        setHarvestLevel("pickaxe", 2);
        setStepSound(soundTypeStone);
    }

    @Override
    public int quantityDropped(Random random) {
        return super.quantityDropped(random);
    }

    @Override
    public Item getItemDropped(int meta, Random random, int fortune) {
        return super.getItemDropped(meta, random, fortune);
    }
}
