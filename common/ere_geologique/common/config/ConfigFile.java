package ere_geologique.common.config;

import java.io.File;


import net.minecraftforge.common.Configuration;
import net.minecraftforge.common.Property;

public class ConfigFile extends Configuration
{

	public ConfigFile(File file)
	{
		super(file);
	}
	
	@Override
	public void save()
	{
		Property versionProp = get(CATEGORY_GENERAL, "version", Version.VERSION);
		versionProp.set(Version.VERSION);
		super.save();
	}
}