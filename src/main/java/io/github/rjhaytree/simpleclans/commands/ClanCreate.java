package io.github.rjhaytree.simpleclans.commands;

import io.github.rjhaytree.simpleclans.SimpleClans;
import io.github.rjhaytree.simpleclans.clans.Clan;
import io.github.rjhaytree.simpleclans.clans.Member;
import io.github.rjhaytree.simpleclans.utility.RoleEnum;
import io.github.rjhaytree.simpleclans.utility.TextUtils;
import org.spongepowered.api.command.CommandException;
import org.spongepowered.api.command.CommandResult;
import org.spongepowered.api.command.CommandSource;
import org.spongepowered.api.command.args.CommandContext;
import org.spongepowered.api.command.spec.CommandExecutor;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.text.Text;
import org.spongepowered.api.text.format.TextColors;

public class ClanCreate implements CommandExecutor {
    private SimpleClans instance;

    public ClanCreate() {
        instance = SimpleClans.getInstance();
    }

    @Override
    public CommandResult execute(CommandSource src, CommandContext args) throws CommandException {
        if (!(src instanceof Player)) {
            src.sendMessage(Text.of("Clans can only be created by players!"));
        }
        else {
            Player player = (Player) src;
            String clanName = args.<String>getOne("name").get();

            // Check if claim with that name already exists
            if (!instance.getStorage().checkIfClanDataExists(clanName)) {
                // Check if user is already in a clan
                if (!instance.getStorage().checkIfUserDataExists(player.getUniqueId())) {
                    // If not, create new Member object
                    Member member = new Member(player.getUniqueId(), RoleEnum.LEADER, clanName);
                    // Save new member to storage
                    instance.getStorage().saveMember(member);

                    // Create new Clan object
                    Clan clan = new Clan(clanName, member.getUuid(), member);
                    // Add clan to the loaded clans list
                    instance.getClanManager().loadClan(clan);
                    // Save clan to storage
                    instance.getStorage().saveClan(clan);

                    // Send confirmation message
                    TextUtils.sendMessage(player, Text.builder("Clan created").color(TextColors.GREEN).build());
                }
                else {
                    TextUtils.sendMessage(player, Text.of(TextColors.RED, "You cannot be in more than 1 clan!"));
                }
            }
            else {
                TextUtils.sendMessage(player, Text.builder("Clan with the name ").color(TextColors.RED).append(
                        Text.builder(clanName).color(TextColors.YELLOW).append(Text.builder(" already exists!").color(TextColors.RED).build()).build()).build());
            }
        }

        return CommandResult.success();
    }
}
