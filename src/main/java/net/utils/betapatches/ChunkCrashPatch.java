package net.utils.betapatches;

import org.bukkit.Material;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;

public class ChunkCrashPatch implements Listener {

    public void onPlayerInteract(PlayerInteractEvent event) {
        if (event == null || event.getClickedBlock() == null) return;

        if (event.getItem() != null && event.getItem().getType() == Material.BOAT) {
            event.setCancelled(true); // Prevent boat placing
        }
    }
}