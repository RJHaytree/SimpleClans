package io.github.rjhaytree.simpleclans.clans;

import io.github.rjhaytree.simpleclans.utility.RoleEnum;

import java.util.UUID;

public class Member {
    private UUID uuid;
    private RoleEnum role;

    public Member(UUID uuid, RoleEnum role) {
        this.uuid = uuid;
        this.role = role;
    }

    public UUID getUuid() { return uuid; }
    public RoleEnum getRole() { return role; }

    public void setUUID(UUID uuid) { this.uuid = uuid; }
    public void setRole(RoleEnum role) { this.role = role; }
}
