package io.github.rjhaytree.simpleclans.commands;

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
            contents.add(Text.builder(" /clans create <name>").color(TextColors.GREEN).onClick(TextActions.suggestCommand("/clans create")).onHover(TextActions.showText(Text.of("Create a new clan"))).build());
            contents.add(Text.of(TextColors.GREEN, TextActions.suggestCommand("/clans disband"), " /clans disband"));
            contents.add(Text.of(TextColors.GREEN, TextActions.suggestCommand("/clans help"), " /clans help"));
            contents.add(Text.of(TextColors.GREEN, TextActions.suggestCommand("/clans invite"), " /clans invite <player>"));
            contents.add(Text.of(TextColors.GREEN, TextActions.suggestCommand("/clans join"), " /clans join"));
            contents.add(Text.of(TextColors.GREEN, TextActions.suggestCommand("/clans leave"), " /clans leave"));
            contents.add(Text.of(TextColors.GREEN, TextActions.suggestCommand("/clan members"), " /clan members"));
            contents.add(Text.of(TextColors.GREEN, TextActions.suggestCommand("/clan remove"), " /clan remove <player>"));
            contents.add(Text.of(TextColors.GREEN, TextActions.suggestCommand("/clan setrole"), " /clan setrole <player> <role>"));
            contents.add(Text.of(TextColors.GREEN, TextActions.suggestCommand("/clan settag"), " /clan settag <tag>"));
            contents.add(Text.of(TextColors.GREEN, TextActions.suggestCommand("/clan setwaypoint"), " /clan setwaypoint"));
            contents.add(Text.of(TextColors.GREEN, TextActions.suggestCommand("/clan waypoint"), " /clan waypoint"));

            PaginationList.builder()
                    .title(Text.of(TextColors.LIGHT_PURPLE, TextStyles.BOLD, " CLANS "))
                    .contents(contents)
                    .padding(Text.of(TextColors.DARK_GRAY, TextStyles.STRIKETHROUGH, "-"))
                    .sendTo(player);
        }
        else {
            src.sendMessage(Text.of(TextColors.GOLD, "This command must be ran by a player"));
        }

        return CommandResult.success();
    }
}
