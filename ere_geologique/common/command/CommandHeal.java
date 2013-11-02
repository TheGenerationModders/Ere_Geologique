package ere_geologique.common.command;

import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.WrongUsageException;
import net.minecraft.util.ChatMessageComponent;

public class CommandHeal extends CommandBase
{
	public static boolean Heal_Dinos;
	
	@Override
	public String getCommandName()
	{
		return "healdinos";
	}

	@Override
	public String getCommandUsage(ICommandSender sender)
	{
		return "/" + this.getCommandName() + "help";
	}

	@Override
	public void processCommand(ICommandSender sender, String[] arguments)
	{
		if(arguments.length <= 0)
			throw new WrongUsageException("Type '" + this.getCommandUsage(sender) + "' for help.");
		if(arguments[0].matches("true"))
		{
			Heal_Dinos = true;
			return;
		}else if(arguments[0].matches("false")) {
			Heal_Dinos = false;
			return;
		}else if (arguments[0].matches("help")) {
			sender.sendChatToPlayer(ChatMessageComponent.createFromText("Format: '" + this.getCommandName() + " <command> <arguments>'"));
			sender.sendChatToPlayer(ChatMessageComponent.createFromText("Available commands:"));
			sender.sendChatToPlayer(ChatMessageComponent.createFromText("- version : Version information."));
			return;
		}
	}
}