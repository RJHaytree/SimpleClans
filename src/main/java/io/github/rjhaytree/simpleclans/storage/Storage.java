package io.github.rjhaytree.simpleclans.storage;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import io.github.rjhaytree.simpleclans.SimpleClans;
import io.github.rjhaytree.simpleclans.clans.Clan;
import io.github.rjhaytree.simpleclans.clans.Member;
import org.spongepowered.api.Sponge;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.List;
import java.util.UUID;

public class Storage {

    private SimpleClans instance;
    private File clansdir;
    private File userdir;
    private Gson gson;

    public Storage(SimpleClans instance) {
        this.instance = instance;
        gson = new GsonBuilder()
                .setPrettyPrinting()
                .create();

        try {
            clansdir = Sponge.getGame().getSavesDirectory().resolve("simpleclans/clans").toFile();
            if (!clansdir.exists()) {
                clansdir.mkdirs();
            }

            userdir = Sponge.getGame().getSavesDirectory().resolve("simpleclans/users").toFile();
            if (!userdir.exists()) {
                userdir.mkdirs();
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Check if member data already exists.
     * @param uuid UUID of player to check.
     * @return Whether the file is present.
     */
    public Boolean checkIfUserDataExists(UUID uuid) {
        return userdir.toPath().resolve(uuid + ".json").toFile().exists();
    }

    /**
     * Check if clan dat already exists.
     * @param name Name of the clan to check.
     * @return Whether the file is present.
     */
    public Boolean checkIfClanDataExists(String name) {
        return clansdir.toPath().resolve(name + ".json").toFile().exists();
    }

    /**
     * Save a member to their respective file.
     * @param member Member to be saved.
     */
    public void saveMember(Member member) {
        try (BufferedWriter writer = Files.newBufferedWriter(userdir.toPath().resolve(member.getUuid() + ".json"), StandardCharsets.UTF_8)) {
            gson.toJson(member, writer);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Save a clan to it's respective file.
     * @param clan Clan to be saved.
     */
    public void saveClan(Clan clan) {
        try (BufferedWriter writer = Files.newBufferedWriter(clansdir.toPath().resolve(clan.getName() + ".json"), StandardCharsets.UTF_8)) {
            gson.toJson(clan, writer);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Load a member's data.
     * @param uuid UUID of the member to load.
     * @return The loaded member object.
     */
    public Member loadUserData(UUID uuid) {
        try (BufferedReader reader = Files.newBufferedReader(userdir.toPath().resolve(uuid + ".json"), StandardCharsets.UTF_8)) {
            Member member = gson.fromJson(reader, Member.class);
            return member;
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * Load a clan's data.
     * @param name The name of the clan to load.
     * @return The loaded clan object.
     */
    public Clan loadClaimData(String name) {
        try (BufferedReader reader = Files.newBufferedReader(clansdir.toPath().resolve(name + ".json"), StandardCharsets.UTF_8)) {
            Clan clan = gson.fromJson(reader, Clan.class);
            return clan;
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * Delete clan and all members.
     * @param clan The clan to delete.
     */
    public void deleteClan(Clan clan) {
        List<Member> members = clan.getMembers();

        try {
            for (Member m: members) {
                Files.deleteIfExists(userdir.toPath().resolve(m.getUuid() + ".json"));
            }

            Files.deleteIfExists(clansdir.toPath().resolve(clan.getName() + ".json"));
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
