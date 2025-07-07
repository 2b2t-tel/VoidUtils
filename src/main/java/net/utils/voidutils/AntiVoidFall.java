package net.utils.voidutils;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerListener;

public class AntiVoidFall extends PlayerListener {

    @Override
    public void onPlayerMove(PlayerMoveEvent event) {
        Player player = event.getPlayer();
        Location loc = player.getLocation();

        if (loc.getY() < -64) {
            World world = loc.getWorld();
            int x = loc.getBlockX();
            int z = loc.getBlockZ();

            // Get highest solid block at that X/Z
            int y = world.getHighestBlockYAt(x, z);
            if (y < 64 || world.getBlockAt(x, y - 1, z).getType() == Material.AIR) {
                y = 72; // fallback if chunk is void or underground
            }

            Location safe = new Location(world, x + 0.5, y + 1, z + 0.5);
            player.teleport(safe);
        }
    }
}