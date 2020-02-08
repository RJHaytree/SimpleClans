package io.github.rjhaytree.simpleclans.commands;

import io.github.rjhaytree.simpleclans.SimpleClans;
import io.github.rjhaytree.simpleclans.clans.Clan;
import io.github.rjhaytree.simpleclans.utility.TextUtils;
import org.spongepowered.api.command.CommandException;
import org.spongepowered.api.command.CommandResult;
import org.spongepowered.api.command.CommandSource;
import org.spongepowered.api.command.args.CommandContext;
import org.spongepowered.api.command.spec.CommandExecutor;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.text.Text;
import org.spongepowered.api.text.format.TextColors;

public class ClanDisband implements CommandExecutor {
    private SimpleClans instance;

    public ClanDisband() {
        instance = SimpleClans.getInstance();
    }

    @Override
    public CommandResult execute(CommandSource src, CommandContext args) throws CommandException {
        if (!(src instanceof Player)) {
            src.sendMessage(Text.of("Clans can only be deleted by players!"));
        }
        else {
            Player player = (Player) src;
            if (instance.getStorage().checkIfUserDataExists(player.getUniqueId())) {
                Clan clan = instance.getClanManager().getPlayersClan(player.getUniqueId());
                if (clan.getLeader().equals(player.getUniqueId())) {
                    instance.getStorage().deleteClan(clan);
                    TextUtils.sendMessage(player, Text.of(TextColors.GREEN, "Your clan has been deleted"));
                }
                else {
                    TextUtils.sendMessage(player, Text.of(TextColors.RED, "The clan can only be disbanded by the leader!"));
                }
            }
        }

        return CommandResult.success();
    }
}
