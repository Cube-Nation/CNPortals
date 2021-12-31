package de.cubenation.cnportals.config.locale;

import de.cubenation.api.bedrock.BasePlugin;
import de.cubenation.api.bedrock.service.config.CustomConfigurationFile;
import net.cubespace.Yamler.Config.Comment;
import net.cubespace.Yamler.Config.Path;

import java.io.File;
import java.util.HashMap;

public class de_DE extends CustomConfigurationFile {

    public static String getFilename() {
        return "locale" + File.separator + "de_DE.yml";
    }

    public de_DE(BasePlugin plugin) {
        CONFIG_FILE = new File(plugin.getDataFolder(), getFilename());
    }

    /**
     * Success
     */

    @Comment("Args: %id%")
    @Path("success.portal.define")
    private String success_portal_define = "{\"text\":\"\",\"extra\":[{\"text\":\"%plugin_prefix%\"},{\"text\":\" \",\"color\":\"white\"},{\"text\":\"Das Portal mit der ID\",\"color\":\"&TEXT&\"},{\"text\":\" \",\"color\":\"white\"},{\"text\":\"%id%\",\"color\":\"&PRIMARY&\"},{\"text\":\" \",\"color\":\"white\"},{\"text\":\"wurde erfolgreich erstellt.\",\"color\":\"&TEXT&\"}]}";

    @Comment("Args: %id%")
    @Path("success.portal.link.location")
    private String success_portal_link_location = "{\"text\":\"\",\"extra\":[{\"text\":\"%plugin_prefix%\"},{\"text\":\" \",\"color\":\"white\"},{\"text\":\"Das Ziel wurde erfolgreich zu deiner aktuellen Position geändert.\",\"color\":\"&TEXT&\"}]}";

    @Comment("Args: %fromid%, %toid%")
    @Path("success.portal.link.portal")
    private String success_portal_link_portal = "{\"text\":\"\",\"extra\":[{\"text\":\"%plugin_prefix%\"},{\"text\":\" \",\"color\":\"white\"},{\"text\":\"Das Portal\",\"color\":\"&TEXT&\"},{\"text\":\" \",\"color\":\"white\"},{\"text\":\"%toid%\",\"color\":\"&PRIMARY&\"},{\"text\":\" \",\"color\":\"white\"},{\"text\":\"wurde erfolgreich als Ziel verbunden.\",\"color\":\"&TEXT&\"}]}";

    @Comment("Args: %id%")
    @Path("success.portal.link.reset")
    private String success_portal_link_reset = "{\"text\":\"\",\"extra\":[{\"text\":\"%plugin_prefix%\"},{\"text\":\" \",\"color\":\"white\"},{\"text\":\"Das Ziel wurde erfolgreich zurückgesetzt.\",\"color\":\"&TEXT&\"}]}";

    @Comment("Args: %id%")
    @Path("success.portal.redefine")
    private String success_portal_redefine = "{\"text\":\"\",\"extra\":[{\"text\":\"%plugin_prefix%\"},{\"text\":\" \",\"color\":\"white\"},{\"text\":\"Das Portal mit der ID\",\"color\":\"&TEXT&\"},{\"text\":\" \",\"color\":\"white\"},{\"text\":\"%id%\",\"color\":\"&PRIMARY&\"},{\"text\":\" \",\"color\":\"white\"},{\"text\":\"wurde erfolgreich abgeändert.\",\"color\":\"&TEXT&\"}]}";

    @Comment("Args: %id%")
    @Path("success.portal.remove")
    private String success_portal_remove = "{\"text\":\"\",\"extra\":[{\"text\":\"%plugin_prefix%\"},{\"text\":\" \",\"color\":\"white\"},{\"text\":\"Das Portal mit der ID\",\"color\":\"&TEXT&\"},{\"text\":\" \",\"color\":\"white\"},{\"text\":\"%id%\",\"color\":\"&PRIMARY&\"},{\"text\":\" \",\"color\":\"white\"},{\"text\":\"wurde erfolgreich gelöscht.\",\"color\":\"&TEXT&\"}]}";

    @Comment("Args: %id%")
    @Path("success.portal.select")
    private String success_select_remove = "{\"text\":\"\",\"extra\":[{\"text\":\"%plugin_prefix%\"},{\"text\":\" \",\"color\":\"white\"},{\"text\":\"Das Portal mit der ID\",\"color\":\"&TEXT&\"},{\"text\":\" \",\"color\":\"white\"},{\"text\":\"%id%\",\"color\":\"&PRIMARY&\"},{\"text\":\" \",\"color\":\"white\"},{\"text\":\"wurde erfolgreich ausgewählt.\",\"color\":\"&TEXT&\"}]}";


    /**
     * Failure
     */

    @Comment("Args: %id%")
    @Path("failure.portal.define")
    private String failure_portal_define = "{\"text\":\"\",\"extra\":[{\"text\":\"%plugin_prefix%\"},{\"text\":\" \",\"color\":\"white\"},{\"text\":\"Das Portal mit der ID\",\"color\":\"red\"},{\"text\":\" \",\"color\":\"white\"},{\"text\":\"%id%\",\"color\":\"dark_red\"},{\"text\":\" \",\"color\":\"white\"},{\"text\":\"konnte nicht erstellt werden.\",\"color\":\"red\"}]}";

    @Comment("Args: %id%")
    @Path("failure.portal.link.location")
    private String failure_portal_link_location = "{\"text\":\"\",\"extra\":[{\"text\":\"%plugin_prefix%\"},{\"text\":\" \",\"color\":\"white\"},{\"text\":\"Das Ziel konnte nicht gesetzt werden.\",\"color\":\"red\"}]}";

    @Comment("Args: %fromid%, %toid%")
    @Path("failure.portal.link.portal")
    private String failure_portal_link_portal = "{\"text\":\"\",\"extra\":[{\"text\":\"%plugin_prefix%\"},{\"text\":\" \",\"color\":\"white\"},{\"text\":\"Das Portal\",\"color\":\"red\"},{\"text\":\" \",\"color\":\"white\"},{\"text\":\"%toid%\",\"color\":\"dark_red\"},{\"text\":\" \",\"color\":\"white\"},{\"text\":\"konnte nicht als Ziel verbunden werden.\",\"color\":\"red\"}]}";

    @Comment("Args: %id%")
    @Path("failure.portal.link.reset")
    private String failure_portal_link_reset = "{\"text\":\"\",\"extra\":[{\"text\":\"%plugin_prefix%\"},{\"text\":\" \",\"color\":\"white\"},{\"text\":\"Das Ziel konnte nicht zurückgesetzt werden.\",\"color\":\"red\"}]}";

    @Comment("Args: %id%")
    @Path("failure.portal.redefine")
    private String failure_portal_redefine = "{\"text\":\"\",\"extra\":[{\"text\":\"%plugin_prefix%\"},{\"text\":\" \",\"color\":\"white\"},{\"text\":\"Das Portal mit der ID\",\"color\":\"red\"},{\"text\":\" \",\"color\":\"white\"},{\"text\":\"%id%\",\"color\":\"dark_red\"},{\"text\":\" \",\"color\":\"white\"},{\"text\":\"konnte nicht abgeändert werden.\",\"color\":\"red\"}]}";

    @Comment("Args: %id%")
    @Path("failure.portal.remove")
    private String failure_portal_remove = "{\"text\":\"\",\"extra\":[{\"text\":\"%plugin_prefix%\"},{\"text\":\" \",\"color\":\"white\"},{\"text\":\"Das Portal mit der ID\",\"color\":\"red\"},{\"text\":\" \",\"color\":\"white\"},{\"text\":\"%id%\",\"color\":\"dark_red\"},{\"text\":\" \",\"color\":\"white\"},{\"text\":\"konnte nicht gelöscht werden.\",\"color\":\"red\"}]}";

    @Comment("Args: %id%")
    @Path("failure.portal.select")
    private String failure_portal_select = "{\"text\":\"\",\"extra\":[{\"text\":\"%plugin_prefix%\"},{\"text\":\" \",\"color\":\"white\"},{\"text\":\"Das Portal mit der ID\",\"color\":\"red\"},{\"text\":\" \",\"color\":\"white\"},{\"text\":\"%id%\",\"color\":\"dark_red\"},{\"text\":\" \",\"color\":\"white\"},{\"text\":\"konnte nicht ausgewählt werden.\",\"color\":\"red\"}]}";

    /**
     * Commands
     */

    // Define

    @Path("command.portal.define.desc")
    private String command_portal_define_desc = "Setzt ein Portal.";

    @Path("command.portal.define.args.portalid")
    private HashMap<String, String> command_portal_define_args_portalid = new HashMap<String, String>() {{
        put("desc", "Eindeutiger Portal Name");
        put("ph", "Portal");
    }};

    // Link Location

    @Path("command.portal.link.location.desc")
    private String command_portal_link_location_desc = "Verlinkt ein Portal mit deiner aktuellen Position.";

    @Path("command.portal.link.location.args.portalid")
    private HashMap<String, String> command_portal_link_location_args_portalid = new HashMap<String, String>() {{
        put("desc", "Eindeutiger Portal Name");
        put("ph", "Portal");
    }};

    // Link Portal

    @Path("command.portal.link.portal.desc")
    private String command_portal_link_portal_desc = "Verlinkt ein Portal mit einem anderen Portal.";

    @Path("command.portal.link.portal.args.portalidfrom")
    private HashMap<String, String> command_portal_link_portal_args_portalidfrom = new HashMap<String, String>() {{
        put("desc", "Eindeutiger Portal Name");
        put("ph", "StartPortal");
    }};

    @Path("command.portal.link.portal.args.portalidto")
    private HashMap<String, String> command_portal_link_portal_args_portalidto = new HashMap<String, String>() {{
        put("desc", "Eindeutiger Portal Name");
        put("ph", "ZielPortal");
    }};

    // Link Reset

    @Path("command.portal.link.reset.desc")
    private String command_portal_link_reset_desc = "Setzt die Verlinkung eines Portales zurück.";

    @Path("command.portal.link.reset.args.portalid")
    private HashMap<String, String> command_portal_link_reset_args_portalid = new HashMap<String, String>() {{
        put("desc", "Eindeutiger Portal Name");
        put("ph", "Portal");
    }};

    // List

    @Path("command.portal.list.desc")
    private String command_portal_list_desc = "Löscht ein Portal.";

    @Path("command.portal.list.args.page")
    private HashMap<String, String> command_portal_list_args_page = new HashMap<String, String>() {{
        put("desc", "Seitenzahl");
        put("ph", "Seite");
    }};

    // Define

    @Path("command.portal.redefine.desc")
    private String command_portal_redefine_desc = "Setzt ein Portal.";

    @Path("command.portal.redefine.args.portalid")
    private HashMap<String, String> command_portal_redefine_args_portalid = new HashMap<String, String>() {{
        put("desc", "Eindeutiger Portal Name");
        put("ph", "Portal");
    }};

    // Remove

    @Path("command.portal.remove.desc")
    private String command_portal_remove_desc = "Löscht ein Portal.";

    @Path("command.portal.remove.args.portalid")
    private HashMap<String, String> command_portal_remove_args_portalid = new HashMap<String, String>() {{
        put("desc", "Eindeutiger Portal Name");
        put("ph", "Portal");
    }};

    // Select

    @Path("command.portal.select.desc")
    private String command_portal_select_desc = "Selektiert ein Portal mit WorldEdit.";

    @Path("command.portal.select.args.portalid")
    private HashMap<String, String> command_portal_select_args_portalid = new HashMap<String, String>() {{
        put("desc", "Eindeutiger Portal Name");
        put("ph", "Portal");
    }};

    /**
     * Error
     */

    @Path("error.noweselection")
    private String error_noweselection = "{\"text\":\"%plugin_prefix%\",\"extra\":[{\"text\":\" Du musst zunächst eine viereckige WorldEdit Selektion machen.\",\"color\":\"red\"}]}";

    @Comment("Args: %id%")
    @Path("error.portalidexistent")
    private String failure_portalidexistent = "{\"text\":\"\",\"extra\":[{\"text\":\"%plugin_prefix%\"},{\"text\":\" \",\"color\":\"white\"},{\"text\":\"Es existiert bereits ein Portal mit der ID\",\"color\":\"red\"},{\"text\":\" \",\"color\":\"white\"},{\"text\":\"%id%\",\"color\":\"dark_red\"},{\"text\":\".\",\"color\":\"red\"}]}";

    @Comment("Args: %id%")
    @Path("error.portalidnotexistent")
    private String failure_portalidnotexistent = "{\"text\":\"\",\"extra\":[{\"text\":\"%plugin_prefix%\"},{\"text\":\" \",\"color\":\"white\"},{\"text\":\"Es existiert kein Portal mit der ID\",\"color\":\"red\"},{\"text\":\" \",\"color\":\"white\"},{\"text\":\"%id%\",\"color\":\"dark_red\"},{\"text\":\".\",\"color\":\"red\"}]}";

    /**
     * Action
     */

    @Path("action.noportaldestination")
    private String error_noportaldestination = "{\"text\":\"Dieses Portal scheint ins Nichts zu führen...\",\"color\":\"dark_gray\"}";

    @Path("action.noportalusepermission")
    private String error_noportalusepermission = "{\"text\":\"Du bist noch nicht mächtig genug dieses Portal zu betreten...\",\"color\":\"red\"}";

    /**
     * Portal
     */

    @Path("portal.list.empty")
    private String portal_list_empty = "{\"text\":\"\",\"extra\":[{\"text\":\"%plugin_prefix%\"},{\"text\":\" \",\"color\":\"white\"},{\"text\":\"Es sind keine Portale gesetzt.\",\"color\":\"&TEXT&\"}]}";

    @Path("portal.list.header")
    private String portal_list_header = "Portale";

    @Comment("Args: %command%")
    @Path("hint.portal.link.location")
    private String hint_portal_link_location = "{\"text\":\"\",\"extra\":[{\"text\":\"<?>\",\"color\":\"dark_gray\"},{\"text\":\" \",\"color\":\"white\"},{\"text\":\"Klicke\",\"color\":\"gray\"},{\"text\":\" \",\"color\":\"white\"},{\"text\":\"hier\",\"color\":\"&SECONDARY&\"},{\"text\":\" \",\"color\":\"white\"},{\"text\":\"um das Portal mit deiner aktuellen\",\"color\":\"gray\"},{\"text\":\" \",\"color\":\"white\"},{\"text\":\"Position\",\"color\":\"&SECONDARY&\"},{\"text\":\" \",\"color\":\"white\"},{\"text\":\"zu verbinden.\",\"color\":\"gray\"}],\"clickEvent\":{\"action\":\"run_command\",\"value\":\"%command%\"},\"hoverEvent\":{\"action\":\"show_text\",\"value\":\"Mit Position verbinden\"}}";

    @Comment("Args: %command%")
    @Path("hint.portal.link.portal")
    private String hint_portal_link_portal = "{\"text\":\"\",\"extra\":[{\"text\":\"<?>\",\"color\":\"dark_gray\"},{\"text\":\" \",\"color\":\"white\"},{\"text\":\"Klicke\",\"color\":\"gray\"},{\"text\":\" \",\"color\":\"white\"},{\"text\":\"hier\",\"color\":\"&SECONDARY&\"},{\"text\":\" \",\"color\":\"white\"},{\"text\":\"um das Portal mit einem anderen\",\"color\":\"gray\"},{\"text\":\" \",\"color\":\"white\"},{\"text\":\"Portal\",\"color\":\"&SECONDARY&\"},{\"text\":\" \",\"color\":\"white\"},{\"text\":\"zu verbinden.\",\"color\":\"gray\"}],\"clickEvent\":{\"action\":\"suggest_command\",\"value\":\"%command%\"},\"hoverEvent\":{\"action\":\"show_text\",\"value\":\"Mit Portal verbinden\"}}";

    /**
     * Plaintext
     */

    @Path("plaintext.portal.destination.location")
    private String plaintext_portal_destination_location = "Position";

    @Path("plaintext.portal.destination.portal")
    private String plaintext_portal_destination_portal = "Portal";

    @Path("plaintext.notset")
    private String plaintext_notset = "nicht gesetzt";
}

