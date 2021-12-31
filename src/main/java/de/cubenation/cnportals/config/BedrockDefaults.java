package de.cubenation.cnportals.config;

import de.cubenation.api.bedrock.BasePlugin;
import net.md_5.bungee.api.ChatColor;

import java.io.File;

@SuppressWarnings("unused")
public class BedrockDefaults extends de.cubenation.api.bedrock.config.BedrockDefaults {

    public BedrockDefaults(BasePlugin plugin) {
        CONFIG_FILE = new File(plugin.getDataFolder(), de.cubenation.api.bedrock.config.BedrockDefaults.getFilename());
        CONFIG_HEADER = getHeader();

        this.setLocalizationLocale("de_DE");

        this.setPermissionPrefix("cnportals");


        this.setColorSchemeName("CUSTOM");

        this.setColorSchemePrimary(ChatColor.DARK_AQUA);
        this.setColorSchemeSecondary(ChatColor.AQUA);
        this.setColorSchemeFlag(ChatColor.GOLD);
        this.setColorSchemeText(ChatColor.WHITE);
    }

}
