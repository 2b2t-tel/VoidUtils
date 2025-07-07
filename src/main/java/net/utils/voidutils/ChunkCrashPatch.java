package net.utils.voidutils;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerListener;
import org.bukkit.inventory.ItemStack;

public class ChunkCrashPatch extends PlayerListener {

    @Override
    public void onPlayerInteract(PlayerInteractEvent event) {
        if (event.getAction() != Action.RIGHT_CLICK_BLOCK) return;
        if (event.getItem() == null) return;

        ItemStack item = event.getItem();


        if (item.getTypeId() == 333) {
            Block block = event.getClickedBlock();
            if (block == null) return;

            Material type = block.getType();

            // Allow boat placement only if clicked block is water or stationary water
            if (type != Material.WATER && type != Material.STATIONARY_WATER) {
                event.setCancelled(true);
                // player.sendMessage("You can only place boats on water!");
            }
        }
    }
}