package ere_geologique.common.command;

import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.WrongUsageException;
import net.minecraft.util.ChatMessageComponent;

public class CommandBlockBreak extends CommandBase
{
	public static boolean Dino_Block_Breaking;
	
	@Override
	public String getCommandName()
	{
		return "blockbreak";
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
			Dino_Block_Breaking = true;
			return;
		}else if(arguments[0].matches("false")) {
			Dino_Block_Breaking = false;
			return;
		}else if (arguments[0].matches("help")) {
			sender.sendChatToPlayer(ChatMessageComponent.createFromText("Format: '" + this.getCommandName() + " <command> <arguments>'"));
			sender.sendChatToPlayer(ChatMessageComponent.createFromText("Available commands:"));
			sender.sendChatToPlayer(ChatMessageComponent.createFromText("- version : Version information."));
			return;
		}
	}
}