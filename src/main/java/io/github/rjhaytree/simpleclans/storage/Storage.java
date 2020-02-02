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
import java.nio.file.Path;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

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

    public Boolean checkIfUserDataExists(UUID uuid) {
        return userdir.toPath().resolve(uuid + ".json").toFile().exists();
    }

    public Boolean checkIfClaimDataExists(String name) {
        return clansdir.toPath().resolve(clansdir + ".json").toFile().exists();
    }

    public void saveMember(Member member) {
        try (BufferedWriter writer = Files.newBufferedWriter(userdir.toPath().resolve(member.getUuid() + ".json"), StandardCharsets.UTF_8)) {
            gson.toJson(member, writer);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void saveClan(Clan clan) {
        try (BufferedWriter writer = Files.newBufferedWriter(clansdir.toPath().resolve(clan.getName() + ".json"), StandardCharsets.UTF_8)) {
            gson.toJson(clan, writer);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Member loadUserDate(UUID uuid) {
        try (BufferedReader reader = Files.newBufferedReader(userdir.toPath().resolve(uuid + ".json"), StandardCharsets.UTF_8)) {
            Member member = gson.fromJson(reader, Member.class);
            return member;
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

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
}
