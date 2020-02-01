package io.github.rjhaytree.simpleclans.clans;

public class TeleportPoint {
    private double x;
    private double y;
    private double z;
    private String world;

    public TeleportPoint(double x, double y, double z, String world) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.world = world;
    }

    public double getX() { return x; }
    public double getY() { return y; }
    public double getZ() { return z; }
    public String getWorld() { return world; }

    public void setX(double x) { this.x = x; }
    public void setY(double y) { this.y = y; }
    public void setZ(double z) { this.z = z; }
    public void setWorld(String world) { this.world = world; }

    public void overrideTeleportPoint(double x, double y, double z, String world) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.world = world;
    }
}
