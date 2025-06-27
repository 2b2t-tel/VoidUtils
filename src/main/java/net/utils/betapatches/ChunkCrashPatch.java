package net.utils.betapatches;

import org.bukkit.Material;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerListener;

public class ChunkCrashPatch extends PlayerListener {

    @Override
    public void onPlayerInteract(PlayerInteractEvent event) {
        if (event == null || event.getItem() == null) return;

        if (event.getItem().getType() == Material.BOAT) {
            event.setCancelled(true); // Prevent boat placing (crash fix)
        }
    }
}