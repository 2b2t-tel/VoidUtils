package net.utils.betapatches;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerListener;
import org.bukkit.event.block.Action;

public class ChunkCrashPatch extends PlayerListener {

    @Override
    public void onPlayerInteract(PlayerInteractEvent event) {
        if (event == null || event.getItem() == null || event.getClickedBlock() == null) return;

        // Only care about right-clicking with a boat
        if (event.getItem().getType() != Material.BOAT) return;
        if (event.getAction() != Action.RIGHT_CLICK_BLOCK) return;

        Block clicked = event.getClickedBlock();
        Block blockAbove = clicked.getRelative(0, 1, 0);

        // Cancel if not placing on top of water
        if (clicked.getType() != Material.WATER && clicked.getType() != Material.STATIONARY_WATER) {
            event.setCancelled(true);
        } else if (blockAbove.getType() != Material.AIR) {
            event.setCancelled(true); // Don’t allow placing inside blocks
        }
    }
}