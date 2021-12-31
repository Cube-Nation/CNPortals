package de.cubenation.cnportals.messages;

import de.cubenation.api.bedrock.BasePlugin;
import de.cubenation.api.bedrock.helper.MessageHelper;
import de.cubenation.api.bedrock.service.pageablelist.PageableListStorable;
import de.cubenation.api.bedrock.translation.JsonMessage;
import de.cubenation.api.bedrock.translation.parts.BedrockJson;
import de.cubenation.api.bedrock.translation.parts.JsonColor;
import de.cubenation.cnportals.CNPortalsPlugin;
import de.cubenation.cnportals.model.Portal;
import de.cubenation.cnportals.model.destination.AbstractDestination;
import de.cubenation.cnportals.model.destination.LocationDestination;
import de.cubenation.cnportals.model.destination.PortalDestination;
import de.cubenation.cnportals.service.PortalPageableListService;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class Messages extends MessageHelper {

    private static CNPortalsPlugin plugin = CNPortalsPlugin.getInstance();

    public static class Success {
        public static void PortalDefine(Player player, String id) {
            new JsonMessage(plugin, "success.portal.define", "id", id).send(player);
        }

        public static void PortalLinkLocation(Player player, String id) {
            new JsonMessage(plugin, "success.portal.link.location", "id", id).send(player);
        }

        public static void PortalLinkPortal(Player player, String fromid, String toid) {
            new JsonMessage(plugin, "success.portal.link.portal", "fromid", fromid, "toid", toid).send(player);
        }

        public static void PortalLinkReset(Player player, String id) {
            new JsonMessage(plugin, "success.portal.link.reset", "id", id).send(player);
        }

        public static void PortalRedefine(Player player, String id) {
            new JsonMessage(plugin, "success.portal.redefine", "id", id).send(player);
        }

        public static void PortalRemove(Player player, String id) {
            new JsonMessage(plugin, "success.portal.remove", "id", id).send(player);
        }

        public static void PortalSelect(Player player, String id) {
            new JsonMessage(plugin, "success.portal.select", "id", id).send(player);
        }
    }

    public static class Failure {
        public static void PortalDefine(Player player, String id) {
            new JsonMessage(plugin, "failure.portal.define", "id", id).send(player);
        }

        public static void PortalLinkLocation(Player player, String id) {
            new JsonMessage(plugin, "failure.portal.link.location", "id", id).send(player);
        }

        public static void PortalLinkPortal(Player player, String fromid, String toid) {
            new JsonMessage(plugin, "failure.portal.link.portal", "fromid", fromid, "toid", toid).send(player);
        }

        public static void PortalLinkReset(Player player, String id) {
            new JsonMessage(plugin, "failure.portal.link.reset", "id", id).send(player);
        }

        public static void PortalRedefine(Player player, String id) {
            new JsonMessage(plugin, "failure.portal.redefine", "id", id).send(player);
        }

        public static void PortalRemove(Player player, String id) {
            new JsonMessage(plugin, "failure.portal.remove", "id", id).send(player);
        }

        public static void PortalSelect(Player player, String id) {
            new JsonMessage(plugin, "failure.portal.select", "id", id).send(player);
        }
    }

    public static class Error {
        public static void ErrorNoWESelection(CommandSender sender) {
            new JsonMessage(plugin, "error.noweselection").send(sender);
        }

        public static void PortalIdAlreadyExistent(Player player, String id) {
            new JsonMessage(plugin, "error.portalidexistent", "id", id).send(player);
        }

        public static void PortalIdNotExistent(Player player, String id) {
            new JsonMessage(plugin, "error.portalidnotexistent", "id", id).send(player);
        }
    }

    public static class Action {
        public static void NoPortalDestination(Player player) {
            new JsonMessage(plugin, "action.noportaldestination").send(player);
        }

        public static void NoPortalUsePermission(Player player) {
            new JsonMessage(plugin, "action.noportalusepermission").send(player);
        }
    }

    public static class Portals {
        public static  void PostCreationHints(CommandSender commandSender, String portalId) {
            new JsonMessage(plugin, "hint.portal.link.location", "command", "/portal link location "+portalId).send(commandSender);
            new JsonMessage(plugin, "hint.portal.link.portal", "command", "/portal link portal ").send(commandSender);
        }

        public static void ListEmpty(CommandSender commandSender) {
            new JsonMessage(plugin, "portal.list.empty").send(commandSender);
        }

        public static void ListNonEmpty(CommandSender commandSender, List<Portal> portals, int page, String pageCommand) {
            PortalPageableListService listService = new PortalPageableListService(plugin);
            for (JsonMessage component : PortalListEntry(plugin, commandSender, portals)) {
                PageableListStorable storeable = new PageableListStorable();
                storeable.set(component);
                listService.store(storeable);
            }
            String header = getPlainText(plugin, "portal.list.header");

            if (page <= 0 || page > listService.getPages())
                page = 1; // page out of bounds => show page 1

            listService.paginate(commandSender, pageCommand, header, page);
        }

        private static ArrayList<JsonMessage> PortalListEntry(BasePlugin plugin, CommandSender sender, List<Portal> portals) {
            ArrayList<JsonMessage> jsonList = new ArrayList<>();

            for (de.cubenation.cnportals.model.Portal portal : portals) {
                BedrockJson portalJson = BedrockJson.JsonWithText("");
                BedrockJson questionMark = BedrockJson.JsonWithText("*").color(JsonColor.PRIMARY);
                BedrockJson questionSpace = BedrockJson.Space();
                BedrockJson commandHead = BedrockJson.JsonWithText(portal.getId()).color(JsonColor.TEXT);
                BedrockJson arrow = BedrockJson.JsonWithText("->").color(JsonColor.DARK_GRAY);
                BedrockJson destination = PrettyDestination(portal.getDestination());

                portalJson.addExtra(questionMark);
                portalJson.addExtra(questionSpace);
                portalJson.addExtra(commandHead);
                portalJson.addExtra(BedrockJson.Space());
                portalJson.addExtra(arrow);
                portalJson.addExtra(BedrockJson.Space());
                portalJson.addExtra(destination);

                jsonList.add(new JsonMessage(plugin, portalJson));
            }
            return jsonList;
        }

        private static BedrockJson PrettyDestination(AbstractDestination destination) {
            if(destination instanceof LocationDestination) {
                LocationDestination locDest = (LocationDestination) destination;
                return BedrockJson.JsonWithText(getPlainText(plugin, "plaintext.portal.destination.location")+": [ "+locDest.getWorld().getName()+" | "+locDest.getLocation(null).getBlockX()+" | "+locDest.getLocation(null).getBlockY()+" | "+locDest.getLocation(null).getBlockZ()+" ]").color(JsonColor.GRAY);
            } else if(destination instanceof PortalDestination) {
                PortalDestination portalDest = (PortalDestination) destination;
                return BedrockJson.JsonWithText(getPlainText(plugin, "plaintext.portal.destination.portal")+": "+portalDest.getPortalId()).color(JsonColor.GRAY);
            }
            return BedrockJson.JsonWithText(getPlainText(plugin, "plaintext.notset")).color(JsonColor.GRAY);
        }
    }
}
