import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;

import java.util.Random;

public class BlockHelloWorld extends Block {

    protected BlockHelloWorld(Material material) {
        super(material);
        //TODO define the Block
        //Tutorial: http://bedrockminer.jimdo.com/modding-tutorials/basic-modding-1-7/first-block/
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
