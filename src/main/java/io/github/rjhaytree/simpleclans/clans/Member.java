package io.github.rjhaytree.simpleclans.clans;

import io.github.rjhaytree.simpleclans.utility.RoleEnum;
import org.spongepowered.api.entity.living.player.Player;

public class Member {
    private Player player;
    private RoleEnum role;

    public Member(Player player, RoleEnum role) {
        this.player = player;
        this.role = role;
    }

    public Player getPlayer() { return player; }
    public RoleEnum getRole() { return role; }

    public void setPlayer(Player player) { this.player = player; }
    public void setRole(RoleEnum role) { this.role = role; }
}
