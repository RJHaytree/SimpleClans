package io.github.rjhaytree.simpleclans.clans;

import io.github.rjhaytree.simpleclans.SimpleClans;
import org.spongepowered.api.Sponge;
import org.spongepowered.api.entity.living.player.Player;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

public class ClanManager {
    private SimpleClans instance;
    private List<Clan> loadedClans;

    public ClanManager(SimpleClans instance) {
        this.instance = instance;
        loadedClans = new ArrayList<>();
    }

    /**
     * Check if a clan is already loaded.
     * @param name Name of the clan.
     * @return Whether the claim is loaded.
     */
    public Boolean checkIfClaimLoaded(String name) {
        for (int i = 0; i < loadedClans.size(); i++) {
            if (name.equals(loadedClans.get(i).getName())) {
                return true;
            }
        }

        return false;
    }

    /**
     * Check if a clan has any members online.
     * @param uuid Player UUID.
     * @return Whether a clan has members online.
     */
    public Boolean checkIfClanHasMembersOnline(UUID uuid) {
        // Get online players
        Collection<Player> onlinePlayers = Sponge.getServer().getOnlinePlayers();

        // Get this specific player's clan
        Clan clan = getPlayersClan(uuid);

        // Check if clan members are online
        for (Player p: onlinePlayers) {
            for (Member m: clan.getMembers()) {
                if (m.getUuid().equals(p.getUniqueId()) && !m.getUuid().equals(uuid)) {
                    return true;
                }
            }
        }

        return false;
    }

    /**
     * Load a clan by adding it to the 'loadedClans' ArrayList.
     * @param clan The clan to load.
     */
    public void loadClan(Clan clan) {
        loadedClans.add(clan);
    }

    /**
     * Remove clan from the 'loadedClans' ArrayList.
     * @param name Name of the clan to unload.
     */
    public void unloadClan(String name) {
        for (int i = 0; i < loadedClans.size(); i++) {
            if (name.equals(loadedClans.get(i).getName())) {
                loadedClans.remove(loadedClans.get(i));
            }
        }
    }

    /**
     * Unload clan using the clan instance.
     * @param clan The clan to unload.
     */
    public void unloadClan(Clan clan) {
        loadedClans.remove(clan);
    }

    /**
     * Get list of loaded clans.
     * @return loadedClans ArrayList.
     */
    public List<Clan> getLoadedClans() {
        return loadedClans;
    }

    /**
     * Get the member's clan from their UUID.
     * @param uuid UUID of the player in question.
     * @return The player's clan.
     */
    public Clan getPlayersClan(UUID uuid) {
        for (Clan c: loadedClans) {
            for (Member m: c.getMembers()) {
                if (m.getUuid().equals(uuid)) {
                    return c;
                }
            }
        }

        return null;
    }
}
