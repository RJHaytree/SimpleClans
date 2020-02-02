package io.github.rjhaytree.simpleclans.clans;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Clan {
    private String name;
    private List<Member> members;
    private String tag;
    private TeleportPoint waypoint;
    private UUID leader;

    public Clan(String name, UUID leader) {
        this.name = name;
        this.leader = leader;
        this.members = new ArrayList<>();
        this.tag = null;
        this.waypoint = null;
    }

    public String getName() { return name; }
    public List<Member> getMembers() { return members; }
    public String getTag() { return tag; }
    public TeleportPoint getWaypoint() { return waypoint; }
    public UUID getLeader() { return leader; }

    public void setName(String name) { this.name = name; }
    public void setMembers(List<Member> members) { this.members = members; }
    public void setTag(String tag) { this.tag = tag; }
    public void setWaypoint(TeleportPoint waypoint) { this.waypoint = waypoint; }
    public void setLeader(UUID leader) { this.leader = leader; }

    public void addMember(Member member) {
        members.add(member);
    }

    public void removeMember(Member member) {
        members.remove(member);
    }

    public Member getMember(Member member) {
        if (checkIfMemberExists(member)) {
            for (Member m : members) {
                if (m.equals(member)) {
                    return member;
                }
            }
        }

        return null;
    }

    public Boolean checkIfMemberExists(Member member) {
        return members.contains(member);
    }
}
