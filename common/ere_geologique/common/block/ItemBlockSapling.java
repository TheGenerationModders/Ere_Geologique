package ere_geologique.common.block;

import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

public class ItemBlockSapling extends ItemBlock
{
	public ItemBlockSapling(Block block)
	{
		super(block);
		this.setHasSubtypes(true);
	}
	
	public int getMetadata(int metadata)
	{
		return metadata;
	}
	
	public String getUnlocalizedName(ItemStack stack)
	{
		int metadata = stack.getItemDamage();
		if(metadata > Sapling.saplingtype.length || metadata < 0)
		{
			metadata = 0;
		}
		return super.getUnlocalizedName() + "." + Sapling.saplingtype[metadata];
	}
}