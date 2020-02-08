package io.github.rjhaytree.simpleclans.commands;

import io.github.rjhaytree.simpleclans.SimpleClans;
import io.github.rjhaytree.simpleclans.utility.Permissions;
import org.spongepowered.api.Sponge;
import org.spongepowered.api.command.args.GenericArguments;
import org.spongepowered.api.command.spec.CommandSpec;
import org.spongepowered.api.text.Text;

public class CommandRegistrar {
    private SimpleClans instance;
    public CommandRegistrar(SimpleClans instance) {
        this.instance = instance;
    }

    public void registerCommands() {
        CommandSpec create = CommandSpec.builder()
                .permission(Permissions.CLAN_USER_CREATE)
                .description(Text.of("Creates a new clan"))
                .arguments(GenericArguments.onlyOne(GenericArguments.string(Text.of("name"))))
                .executor(new ClanCreate())
                .build();

        CommandSpec disband = CommandSpec.builder()
                .permission(Permissions.CLAN_USER_DISBAND)
                .description(Text.of("Disbands your current clan"))
                .executor(new ClanDisband())
                .build();

        CommandSpec help = CommandSpec.builder()
                .permission(Permissions.CLAN_USER_HELP)
                .description(Text.of("Help command"))
                .executor(new ClanHelp())
                .build();

        CommandSpec invite = CommandSpec.builder()
                .permission(Permissions.CLAN_USER_INVITE)
                .description(Text.of("Invite a player to the clan"))
                .arguments(GenericArguments.onlyOne(GenericArguments.string(Text.of("name"))))
                .executor(new ClanInvite())
                .build();

        CommandSpec join = CommandSpec.builder()
                .permission(Permissions.CLAN_USER_JOIN)
                .description(Text.of("Join the clan following an invite"))
                .executor(new ClanJoin())
                .build();

        CommandSpec leave = CommandSpec.builder()
                .permission(Permissions.CLAN_USER_LEAVE)
                .description(Text.of("Leave your current clan"))
                .executor(new ClanLeave())
                .build();

        CommandSpec members = CommandSpec.builder()
                .permission(Permissions.CLAN_USER_MEMBERS)
                .description(Text.of("View your clan's current members"))
                .executor(new ClanMembers())
                .build();

        CommandSpec remove = CommandSpec.builder()
                .permission(Permissions.CLAN_USER_REMOVE)
                .description(Text.of("Remove a member of your clan"))
                .arguments(GenericArguments.onlyOne(GenericArguments.string(Text.of("name"))))
                .executor(new ClanRemove())
                .build();

        CommandSpec setRole = CommandSpec.builder()
                .permission(Permissions.CLAN_USER_SET_ROLE)
                .description(Text.of("Set the role of a member in your clan"))
                .arguments(GenericArguments.onlyOne((GenericArguments.string(Text.of("name")))))
                .executor(new ClanSetRole())
                .build();

        CommandSpec setTag = CommandSpec.builder()
                .permission(Permissions.CLAN_USER_SET_TAG)
                .description(Text.of("Set the tag of your clan"))
                .arguments(GenericArguments.onlyOne(GenericArguments.string(Text.of("tag"))))
                .executor(new ClanSetTag())
                .build();

        CommandSpec setWaypoint = CommandSpec.builder()
                .permission(Permissions.CLAN_USER_SET_WAYPOINT)
                .description(Text.of("Set the waypoint of your clan at your current location"))
                .executor(new ClanSetWaypoint())
                .build();

        CommandSpec waypoint = CommandSpec.builder()
                .permission(Permissions.CLAN_USER_WAYPOINT)
                .description(Text.of("Teleport to your clan's waypoint"))
                .executor(new ClanWaypoint())
                .build();

        CommandSpec adminWaypoint = CommandSpec.builder()
                .permission(Permissions.CLAN_ADMIN_WAYPOINT)
                .description(Text.of("Teleport to the waypoint of a clan"))
                .arguments(GenericArguments.onlyOne(GenericArguments.string(Text.of("clan name"))))
                .executor(new ClanAdminWaypoint())
                .build();

        CommandSpec clansRoot = CommandSpec.builder()
                .permission(Permissions.CLAN_USER)
                .description(Text.of("Clan Commands"))
                .child(create, "create")
                .child(disband, "disband")
                .child(invite, "invite")
                .child(members, "members")
                .child(join, "join")
                .child(leave, "leave")
                .child(members, "members", "list")
                .child(remove, "remove")
                .child(setRole, "setrole")
                .child(setTag, "settag")
                .child(setWaypoint, "setwaypoint", "sethome")
                .child(waypoint, "waypoint", "home")
                .child(help, "help")
                .child(adminWaypoint, "admintp")
                .executor(new ClanRoot())
                .build();

        Sponge.getCommandManager().register(instance, clansRoot, "clan", "clans");
    }
}
