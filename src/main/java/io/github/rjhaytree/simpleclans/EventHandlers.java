package io.github.rjhaytree.simpleclans;

import io.github.rjhaytree.simpleclans.clans.ClanManager;
import io.github.rjhaytree.simpleclans.clans.Member;
import io.github.rjhaytree.simpleclans.utility.RoleEnum;
import org.spongepowered.api.Sponge;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.event.Listener;
import org.spongepowered.api.event.network.ClientConnectionEvent;

public class EventHandlers {
    private SimpleClans instance;

    public EventHandlers(SimpleClans instance) {
        this.instance = instance;
    }

    @Listener
    public void onPlayerJoin(ClientConnectionEvent.Join e) {
        Player player = e.getTargetEntity();
        Member member;

        // Check if user exists
        if (!instance.getStorage().checkIfUserDataExists(player.getUniqueId())) {
            return;
        }

        // If user exists, load file
        member = instance.getStorage().loadUserData(player.getUniqueId());

        // Check if clan is loaded already, and if not, load file
        if (!instance.getClanManager().checkIfClaimLoaded(member.getClanName())) {
            instance.getClanManager().loadClan(instance.getStorage().loadClaimData(member.getClanName()));
        }
    }

    @Listener
    public void onPlayerLeave(ClientConnectionEvent.Disconnect e) {
        Player player = e.getTargetEntity();

        // If player has no clan data loaded, do nothing
        if (!instance.getStorage().checkIfUserDataExists(player.getUniqueId())) {
            instance.getLogger().info("Player has no data present.");
            return;
        }

        // Check if the clan has any remaining players online.
        if (instance.getClanManager().checkIfClanHasMembersOnline(player.getUniqueId())) {
            instance.getLogger().info("Clan has players online.");
            return;
        }

        // Get member object from the clan in question.
        Member member = instance.getClanManager().getPlayersClan(player.getUniqueId()).getMember(player.getUniqueId());

        // Save member to storage.
        instance.getStorage().saveMember(member);

        // Save clan to storage.
        instance.getStorage().saveClan(instance.getClanManager().getPlayersClan(player.getUniqueId()));

        // Finally, unload this clan.
        instance.getClanManager().unloadClan(instance.getClanManager().getPlayersClan(player.getUniqueId()));
    }
}
