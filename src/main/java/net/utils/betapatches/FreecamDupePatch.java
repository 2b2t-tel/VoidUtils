package net.utils.betapatches;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.player.PlayerInteractEvent;

import java.util.HashMap;

public class FreecamDupePatch implements Listener {

    private final HashMap<String, Long> lastChestUse = new HashMap<String, Long>();

    public void onPlayerInteract(PlayerInteractEvent event) {
        if (event == null || event.getClickedBlock() == null) return;

        Block block = event.getClickedBlock();
        Player player = event.getPlayer();

        if (block.getType() == Material.CHEST) {
            double distance = player.getLocation().distance(block.getLocation());
            if (distance > 6.0) {
                event.setCancelled(true);
                return;
            }
            lastChestUse.put(player.getName(), System.currentTimeMillis());
        }
    }

    public void onBlockBreak(BlockBreakEvent event) {
        Block block = event.getBlock();
        Player player = event.getPlayer();

        if (block.getType() == Material.CHEST) {
            Long lastUse = lastChestUse.get(player.getName());
            if (lastUse != null) {
                long diff = System.currentTimeMillis() - lastUse;
                if (diff < 500) {
                    event.setCancelled(true);
                }
            }
        }
    }
}