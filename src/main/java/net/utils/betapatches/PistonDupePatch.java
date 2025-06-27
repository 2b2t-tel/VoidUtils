package net.utils.betapatches;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;

public class PistonDupePatch implements Listener {

    public void onBlockPlace(BlockPlaceEvent event) {
        Block block = event.getBlockPlaced();
        if (block.getType() == Material.PISTON_BASE || block.getType() == Material.PISTON_STICKY_BASE) {
            event.setCancelled(true); // Disable piston placement entirely (dupe fix)
        }
    }
}
