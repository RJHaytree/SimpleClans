package io.github.rjhaytree.simpleclans.clans;

import io.github.rjhaytree.simpleclans.SimpleClans;

import java.util.ArrayList;
import java.util.List;

public class ClanManager {
    private SimpleClans instance;
    private List<Clan> loadedClans;

    public ClanManager(SimpleClans instance) {
        this.instance = instance;
        loadedClans = new ArrayList<>();
    }

    public Boolean checkIfClaimLoaded(String name) {
        for (int i = 0; i < loadedClans.size(); i++) {
            if (name.equals(loadedClans.get(i).getName())) {
                return true;
            }
        }

        return false;
    }

    public void loadClan(Clan clan) {
        loadedClans.add(clan);
    }

    public void unloadClan(String name) {
        for (int i = 0; i < loadedClans.size(); i++) {
            if (name.equals(loadedClans.get(i).getName())) {
                loadedClans.remove(loadedClans.get(i));
            }
        }
    }
}
