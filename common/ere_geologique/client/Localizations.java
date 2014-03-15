package ere_geologique.client;

import cpw.mods.fml.common.registry.LanguageRegistry;
import ere_geologique.common.EreGeologique;

public class Localizations
{
    private static final String LANG_RESOURCE_LOCATION = "/assets/ere_geologique/lang/";

    public static String[] localeFiles =
    { 
        LANG_RESOURCE_LOCATION + "en_US.xml", 
        LANG_RESOURCE_LOCATION + "fr_FR.xml", 
    };
    
    public static void loadLanguages()
    {

        // For every file specified in the Localization library class, load them into the Language Registry
        for (String localizationFile : Localizations.localeFiles) {
            LanguageRegistry.instance().loadLocalization(localizationFile, getLocaleFromFileName(localizationFile), isXMLLanguageFile(localizationFile));
            //EreGeologique.egLog.info("Fichiers des langs chargés");
        }
    }

    public static boolean isXMLLanguageFile(String fileName) {

        return fileName.endsWith(".xml");
    }

    public static String getLocaleFromFileName(String fileName) {

        return fileName.substring(fileName.lastIndexOf('/') + 1, fileName.lastIndexOf('.'));
    }

    public static String getLocalizedString(String key) {

        return LanguageRegistry.instance().getStringLocalization(key);
    }
}