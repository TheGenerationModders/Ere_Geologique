package ere_geologique.common.command;

import java.util.List;

import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommand;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.WrongUsageException;
import net.minecraft.util.ChatMessageComponent;
import ere_geologique.client.LocalizationStrings;

public class CommandDino extends CommandBase
{
	public static boolean Dino_Block_Breaking;
	public static boolean Heal_Dinos;
	public static boolean Dinos_Starve;
	
	@Override
	public String getCommandName()
	{
		return "dino";
	}

	@Override
	public String getCommandUsage(ICommandSender sender)
	{
		return "commands.dino.usage";
	}
	
	@Override
	public List addTabCompletionOptions(ICommandSender par1ICommandSender, String[] par2ArrayOfStr)
	{
		return null;
	}

	@Override
	public void processCommand(ICommandSender sender, String[] arguments)
	{
		if(arguments.length <= 0)
			throw new WrongUsageException("Type '" + this.getCommandUsage(sender) + "' for help.");
		if(arguments[0].matches("blockbreack"))
		{
			commandBlockBreack(sender, arguments);
			return;
		}else if(arguments[0].matches("heal"))
		{
			commandHeal(sender, arguments);
			return;
		}else if(arguments[0].matches("starve"))
		{
			commandStarve(sender, arguments);
			return;
		}else if (arguments[0].matches("help")) {
			sender.sendChatToPlayer(ChatMessageComponent.createFromText("Format: '" + this.getCommandName() + " <command> <arguments>'"));
			sender.sendChatToPlayer(ChatMessageComponent.createFromText("Available commands:"));
			sender.sendChatToPlayer(ChatMessageComponent.createFromText("- blockbreack : " + LocalizationStrings.COMMAND_Descr_B + " ."));
			sender.sendChatToPlayer(ChatMessageComponent.createFromText("- heal : " + LocalizationStrings.COMMAND_Descr_H + " ."));
			sender.sendChatToPlayer(ChatMessageComponent.createFromText("- starve : " + LocalizationStrings.COMMAND_Descr_S + " ."));
			return;
		}
		
		throw new WrongUsageException(this.getCommandUsage(sender));
	}
	
	private void commandBlockBreack(ICommandSender sender, String[] arguments)
	{
		if(arguments[1].matches("true")){
			Dino_Block_Breaking = true;
			sender.sendChatToPlayer(ChatMessageComponent.createFromText("Command Done"));
		}else if(arguments[1].matches("false")){
			Dino_Block_Breaking = false;
			sender.sendChatToPlayer(ChatMessageComponent.createFromText("Command Done"));
		}
	}
	
	private void commandHeal(ICommandSender sender, String[] arguments)
	{
		if(arguments[1].matches("true"))
		{
			Heal_Dinos = true;
			return;
		}else if(arguments[1].matches("false")) {
			Heal_Dinos = false;
			return;
		}
	}
	
	private void commandStarve(ICommandSender sender, String[] arguments)
	{
		if(arguments[1].matches("true"))
		{
			Dinos_Starve = true;
			return;
		}else if(arguments[1].matches("false")) {
			Dinos_Starve = false;
			return;
		}
	}
}