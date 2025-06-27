package net.utils.betapatches;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;

public class NoBedrock implements Listener {

    public void onBlockPlace(BlockPlaceEvent event) {
        Block block = event.getBlockPlaced();
        if (block.getType() == Material.BEDROCK) {
            event.setCancelled(true); // Prevent placing bedrock blocks
        }
    }
}