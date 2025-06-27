package net.utils.betapatches;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.block.BlockListener;

public class NoBedrock extends BlockListener {

    @Override
    public void onBlockPlace(BlockPlaceEvent event) {
        Block block = event.getBlockPlaced();
        if (block.getType() == Material.BEDROCK) {
            event.setCancelled(true);
        }
    }
}