package net.utils.voidutils;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.block.BlockListener;

public class PistonDupePatch extends BlockListener {

    @Override
    public void onBlockPlace(BlockPlaceEvent event) {
        Block block = event.getBlockPlaced();
        if (block.getType() == Material.PISTON_BASE || block.getType() == Material.PISTON_STICKY_BASE) {
            event.setCancelled(true);
        }
    }
}