package ere_geologique.common.command;

import java.util.List;

import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommand;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.WrongUsageException;
import net.minecraft.util.ChatMessageComponent;
import ere_geologique.client.LocalizationStrings;
import ere_geologique.common.config.Version;

public class CommandDino extends CommandBase
{
	public static boolean dino_Block_Breaking;
	public static boolean heal_Dinos = true;
	public static boolean dinos_Starve = true;
	public static boolean debugMode = true;
	
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
	public int getRequiredPermissionLevel()
	{
		return 2;
	}
	
	@Override
	public List addTabCompletionOptions(ICommandSender sender, String[] arguments)
	{
		return arguments.length == 1 ? getListOfStringsMatchingLastWord(arguments, new String[] {"blockbreack", "heal", "starve", "version"}) : (arguments.length == 2 ? getListOfStringsMatchingLastWord(arguments, new String[] {"true", "false"}) : null);
	}

	@Override
	public void processCommand(ICommandSender sender, String[] arguments)
	{
		if(arguments.length <= 0)
			throw new WrongUsageException(this.getCommandUsage(sender));
		if(arguments[0].matches("blockbreack"))
		{
			commandBlockBreack(sender, arguments);
		}else if(arguments[0].matches("heal"))
		{
			commandHeal(sender, arguments);
		}else if(arguments[0].matches("starve"))
		{
			commandStarve(sender, arguments);
		}else if(arguments[0].matches("debug"))
		{ 
			commandDebug(sender, arguments);
		}else if(arguments[0].matches("version"))
		{ 
			commandVersion(sender, arguments);
		}else if(arguments[0].matches("help"))
		{
			sender.sendChatToPlayer(ChatMessageComponent.createFromTranslationKey("commands.dino.help"));	
		}
		throw new WrongUsageException(this.getCommandUsage(sender));
	}

	private void commandBlockBreack(ICommandSender sender, String[] arguments)
	{
		if(arguments[1].matches("true")){
			dino_Block_Breaking = true;
			sender.getEntityWorld().getWorldInfo().getGameRulesInstance().setOrCreateGameRule("blockbreack", "true");
			sender.sendChatToPlayer(ChatMessageComponent.createFromTranslationKey("commands.dino.blockbreack.true"));
		}else if(arguments[1].matches("false")){
			dino_Block_Breaking = false;
			sender.getEntityWorld().getWorldInfo().getGameRulesInstance().setOrCreateGameRule("blockbreack", "false");
			sender.sendChatToPlayer(ChatMessageComponent.createFromTranslationKey("commands.dino.blockbreack.false"));
		}
	}
	
	private void commandHeal(ICommandSender sender, String[] arguments)
	{
		if(arguments[1].matches("true"))
		{
			heal_Dinos = true;
			sender.getEntityWorld().getWorldInfo().getGameRulesInstance().setOrCreateGameRule("heal", "true");
			sender.sendChatToPlayer(ChatMessageComponent.createFromTranslationKey("commands.dino.heal.true"));
		}else if(arguments[1].matches("false"))
		{
			heal_Dinos = false;
			sender.getEntityWorld().getWorldInfo().getGameRulesInstance().setOrCreateGameRule("heal", "false");
			sender.sendChatToPlayer(ChatMessageComponent.createFromTranslationKey("commands.dino.heal.false"));
		}
	}
	
	private void commandStarve(ICommandSender sender, String[] arguments)
	{
		if(arguments[1].matches("true"))
		{
			dinos_Starve = true;
			sender.getEntityWorld().getWorldInfo().getGameRulesInstance().setOrCreateGameRule("starve", "true");
			sender.sendChatToPlayer(ChatMessageComponent.createFromTranslationKey("commands.dino.starve.true"));
		}else if(arguments[1].matches("false"))
		{
			dinos_Starve = false;
			sender.getEntityWorld().getWorldInfo().getGameRulesInstance().setOrCreateGameRule("starve", "false");
			sender.sendChatToPlayer(ChatMessageComponent.createFromTranslationKey("commands.dino.starve.false"));
		}
	}
	
	private void commandDebug(ICommandSender sender, String[] arguments)
	{
		if(arguments[1].matches("true"))
		{
			debugMode = true;
			sender.getEntityWorld().getWorldInfo().getGameRulesInstance().setOrCreateGameRule("debug", "true");
			sender.sendChatToPlayer(ChatMessageComponent.createFromTranslationKey("commands.dino.debug.true"));
		}else if(arguments[1].matches("false"))
		{
			debugMode = false;
			sender.getEntityWorld().getWorldInfo().getGameRulesInstance().setOrCreateGameRule("debug", "false");
			sender.sendChatToPlayer(ChatMessageComponent.createFromTranslationKey("commands.dino.debug.false"));
		}
	}
	
	private void commandVersion(ICommandSender sender, String[] arguments)
	{
		String colour = Version.isOutdated() ? "\u00A7c" : "\u00A7a";

		sender.sendChatToPlayer(ChatMessageComponent.createFromText(String.format(colour + "EreGeologique %s for Minecraft %s (Latest: %s).", Version.getVersion(), Version.MC_VERSION, Version.getRecommendedVersion())));
		if (Version.isOutdated())
		{
			for (String updateLine : Version.getChangelog())
			{
				sender.sendChatToPlayer(ChatMessageComponent.createFromText("\u00A79" + updateLine));
			}
		}
	}
}