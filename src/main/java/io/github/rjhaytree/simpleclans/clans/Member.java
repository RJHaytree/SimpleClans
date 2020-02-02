package io.github.rjhaytree.simpleclans.clans;

import io.github.rjhaytree.simpleclans.utility.RoleEnum;

import java.util.UUID;

public class Member {
    private UUID uuid;
    private RoleEnum role;
    private String clanName;

    public Member(UUID uuid, RoleEnum role, String clanName) {
        this.uuid = uuid;
        this.role = role;
        this.clanName = clanName;
    }

    public UUID getUuid() { return uuid; }
    public RoleEnum getRole() { return role; }
    public String getClanName() { return clanName; }

    public void setUUID(UUID uuid) { this.uuid = uuid; }
    public void setRole(RoleEnum role) { this.role = role; }
    public void setClanName(String clanName) { this.clanName = clanName; }
}
