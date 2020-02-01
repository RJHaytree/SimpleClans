package io.github.rjhaytree.simpleclans.clans;

import java.util.List;

public class Clan {
    private String name;
    private List<Member> members;
    private String tag;
    private TeleportPoint waypoint;

    public Clan(String name, List<Member> members) {
        this.name = name;
        this.members = members;
    }

    public String getName() { return name; }
    public List<Member> getMembers() { return members; }
    public String getTag() { return tag; }
    public TeleportPoint getWaypoint() { return waypoint; }

    public void setName(String name) { this.name = name; }
    public void setMembers(List<Member> members) { this.members = members; }
    public void setTag(String tag) { this.tag = tag; }
    public void setWaypoint(TeleportPoint waypoint) { this.waypoint = waypoint; }

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
