package ere_geologique.common.block;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import ere_geologique.common.EGCreativeTab;
import ere_geologique.common.config.EGProperties;

public class PrehistoriqueBlockCoal extends Block
{

	public PrehistoriqueBlockCoal(int par1)
	{
		super(par1, Material.rock);
		this.setCreativeTab(EGCreativeTab.EGCreativeTab);
	}
	public int idDropped(int par1, Random par2Random, int par3)
	{
		return EGProperties.PrehistoriqueCoalID;
	}
	
	public void dropBlockAsTitemWitchChance(World par1, int par2, int par3, int par4, int par5, float par6, int par7)
	{
		super.dropBlockAsItemWithChance(par1, par2, par3, par4, par5, par6, par7);
		int var8 = 0;
		if (this.blockID == EGProperties.PrehistoriqueCoalID)
		{
			var8 = MathHelper.getRandomIntegerInRange(par1.rand, 0, 5);
		}
		this.dropXpOnBlockBreak(par1, par2, par3, par4, var8);
	}
	
	@Override
    public void registerIcons(IconRegister par1IconRegister)
    {
		blockIcon = par1IconRegister.registerIcon("EreGeologique:Blocks");
    }

}
