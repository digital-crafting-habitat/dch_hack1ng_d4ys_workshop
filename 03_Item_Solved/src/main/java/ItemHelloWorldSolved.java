import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ChatComponentText;
import net.minecraft.world.World;

public class ItemHelloWorldSolved extends Item {

    public ItemHelloWorldSolved() {
        setUnlocalizedName("dustCraftium");
        setCreativeTab(CreativeTabs.tabMaterials);
        setTextureName(HelloWorldItemSolved.MODID + ":craftium_dust");
    }

    @Override
    public ItemStack onItemRightClick(ItemStack itemStack, World world, EntityPlayer entityPlayer) {
        if (entityPlayer.getEntityWorld().isRemote)
            return super.onItemRightClick(itemStack, world, entityPlayer);
        entityPlayer.addChatMessage(new ChatComponentText("Es funktioniert!"));
        return super.onItemRightClick(itemStack, world, entityPlayer);
    }
}
