package net.utils.voidutils;

import com.cypherx.xauth.xAuth;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.player.*;
import org.bukkit.event.player.PlayerListener;

import java.lang.reflect.Field;
import java.util.HashMap;

public class AntiCoordLogger extends PlayerListener {

    private final xAuth xauth;
    private HashMap<String, ?> sessions = null;
    private final HashMap<String, Location> savedLocations = new HashMap<String, Location>();

    public AntiCoordLogger(xAuth xauth) {
        this.xauth = xauth;

        if (xauth != null) {
            try {
                Field sessionsField = xAuth.class.getDeclaredField("sessions");
                sessionsField.setAccessible(true);
                this.sessions = (HashMap<String, ?>) sessionsField.get(xauth);
                System.out.println("[VoidUtils] Hooked into xAuth sessions.");
            } catch (Exception e) {
                System.out.println("[VoidUtils] Failed to access xAuth sessions.");
                e.printStackTrace();
            }
        }
    }

    @Override
    public void onPlayerQuit(PlayerQuitEvent event) {
        handlePreLogout(event.getPlayer());
    }

    @Override
    public void onPlayerKick(PlayerKickEvent event) {
        handlePreLogout(event.getPlayer());
    }

    @Override
    public void onPlayerJoin(PlayerJoinEvent event) {
        final Player player = event.getPlayer();
        final String name = player.getName().toLowerCase();

        // Delay restore to wait for xAuth login
        player.getServer().getScheduler().scheduleSyncDelayedTask(
                player.getServer().getPluginManager().getPlugin("VoidUtils"), new Runnable() {
                    public void run() {
                        if (isAuthenticated(player)) {
                            Location loc = savedLocations.remove(name);
                            if (loc != null) {
                                player.teleport(loc);
                                System.out.println("[VoidUtils] Restored location for " + player.getName());
                            }
                        }
                    }
                }, 40L // ~2 seconds delay
        );
    }

    private void handlePreLogout(Player player) {
        if (!isAuthenticated(player)) {
            savedLocations.put(player.getName().toLowerCase(), player.getLocation());
            wipeCoords(player);
        }
    }

    private boolean isAuthenticated(Player player) {
        if (sessions == null) return false;
        return sessions.containsKey(player.getName().toLowerCase());
    }

    private void wipeCoords(Player player) {
        World world = player.getWorld();
        Location fake = new Location(world, 0, -64, 0);
        player.teleport(fake);
    }
}