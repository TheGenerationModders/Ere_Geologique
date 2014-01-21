package ere_geologique.common.gui;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import cpw.mods.fml.common.network.IGuiHandler;
import ere_geologique.common.block.container.ContainerAnalyzer;
import ere_geologique.common.block.container.ContainerCultivator;
import ere_geologique.common.block.container.ContainerFeeder;
import ere_geologique.common.block.container.ContainerNotebook;
import ere_geologique.common.block.container.ContainerPedia;
import ere_geologique.common.tileentity.TileEntityAnalyzer;
import ere_geologique.common.tileentity.TileEntityCultivator;
import ere_geologique.common.tileentity.TileEntityFeeder;

public class GuiHandler implements IGuiHandler
{
	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z)
	{
		TileEntity tile_entity = world.func_147438_o(x, y, z);
		switch(ID)
		{
			case 0:return new ContainerFeeder(player.inventory, (TileEntityFeeder) tile_entity);
			case 1:return new ContainerPedia();
			case 2:return new ContainerAnalyzer(player.inventory, (TileEntityAnalyzer) tile_entity);
			case 3:return new ContainerCultivator(player.inventory, (TileEntityCultivator) tile_entity);
			case 4:return new ContainerNotebook();
		}
		return null;
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z)
	{
		TileEntity tile_entity = world.func_147438_o(x, y, z);
		switch(ID)
		{
			case 0:return new GuiFeeder(player.inventory, (TileEntityFeeder) tile_entity);
			case 1:return new GuiPedia();
			case 2:return new GuiAnalyzer(player.inventory, (TileEntityAnalyzer) tile_entity);
			case 3:return new GuiCultivator(player.inventory, (TileEntityCultivator) tile_entity);
			case 4:return new GuiNotebook();
		}
		return null;
	}
}