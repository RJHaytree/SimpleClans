package io.github.rjhaytree.simpleclans.commands;

import io.github.rjhaytree.simpleclans.utility.TextUtils;
import org.spongepowered.api.Sponge;
import org.spongepowered.api.command.CommandException;
import org.spongepowered.api.command.CommandResult;
import org.spongepowered.api.command.CommandSource;
import org.spongepowered.api.command.args.CommandContext;
import org.spongepowered.api.command.spec.CommandExecutor;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.service.pagination.PaginationList;
import org.spongepowered.api.text.Text;
import org.spongepowered.api.text.action.TextActions;
import org.spongepowered.api.text.format.TextColors;
import org.spongepowered.api.text.format.TextStyles;

import java.util.ArrayList;
import java.util.List;

public class ClanRoot implements CommandExecutor {
    @Override
    public CommandResult execute(CommandSource src, CommandContext args) throws CommandException {
        if (src instanceof Player) {
            Player player = (Player) src;

            List<Text> contents = new ArrayList<>();
            contents.add(Text.builder(" /clan create <name>").color(TextColors.GREEN).onClick(TextActions.suggestCommand("/clan create")).onHover(TextActions.showText(Text.of("Create a new clan"))).build());
            contents.add(Text.builder(" /clan disband").color(TextColors.GREEN).onClick(TextActions.suggestCommand("/clan disband")).onHover(TextActions.showText(Text.of("Disband your clan"))).build());
            contents.add(Text.builder(" /clan help").color(TextColors.GREEN).onClick(TextActions.suggestCommand("/clan help")).onHover(TextActions.showText(Text.of("Command help for clans"))).build());
            contents.add(Text.builder(" /clan invite <player>").color(TextColors.GREEN).onClick(TextActions.suggestCommand("/clan invite")).onHover(TextActions.showText(Text.of("Invite a player to your clan"))).build());
            contents.add(Text.builder(" /clan join").color(TextColors.GREEN).onClick(TextActions.suggestCommand("/clan join")).onHover(TextActions.showText(Text.of("Join a clan. Requires an invite"))).build());
            contents.add(Text.builder(" /clan leave").color(TextColors.GREEN).onClick(TextActions.suggestCommand("/clan leave")).onHover(TextActions.showText(Text.of("Leave your current clan"))).build());
            contents.add(Text.builder(" /clan members").color(TextColors.GREEN).onClick(TextActions.suggestCommand("/clan create")).onHover(TextActions.showText(Text.of("View a list of clan members"))).build());
            contents.add(Text.builder(" /clan remove <player>").color(TextColors.GREEN).onClick(TextActions.suggestCommand("/clan remove")).onHover(TextActions.showText(Text.of("Remove a player from your clan"))).build());
            contents.add(Text.builder(" /clan setrole <player> <role>").color(TextColors.GREEN).onClick(TextActions.suggestCommand("/clan setrole")).onHover(TextActions.showText(Text.of("Set the role of a player in your clan"))).build());
            contents.add(Text.builder(" /clan settag <tag>").color(TextColors.GREEN).onClick(TextActions.suggestCommand("/clan settag")).onHover(TextActions.showText(Text.of("Set the tag of your clan"))).build());
            contents.add(Text.builder(" /clan setwaypoint").color(TextColors.GREEN).onClick(TextActions.suggestCommand("/clan setwaypoint")).onHover(TextActions.showText(Text.of("Set a waypoint at your current location"))).build());
            contents.add(Text.builder(" /clan waypoint").color(TextColors.GREEN).onClick(TextActions.suggestCommand("/clan waypoint")).onHover(TextActions.showText(Text.of("Teleport to your clan's waypoint"))).build());

            TextUtils.sendPagination(Text.of("CLANS"), contents, player);
        }
        else {
            src.sendMessage(Text.of(TextColors.GOLD, "This command must be ran by a player"));
        }

        return CommandResult.success();
    }
}
