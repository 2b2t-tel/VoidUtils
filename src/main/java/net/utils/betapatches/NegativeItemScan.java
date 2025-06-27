package net.utils.betapatches;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockState;
import org.bukkit.block.Chest;
import org.bukkit.block.Furnace;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerListener;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class NegativeItemScan extends PlayerListener {

    @Override
    public void onPlayerInteract(PlayerInteractEvent event) {
        if (event.isCancelled()) return;
        if (!event.hasBlock()) return;

        Block block = event.getClickedBlock();
        if (block == null) return;

        Material type = block.getType();
        if (type != Material.CHEST && type != Material.FURNACE && type != Material.BURNING_FURNACE) return;

        BlockState state = block.getState();
        Inventory inv = null;

        if (state instanceof Chest) {
            inv = ((Chest) state).getInventory();
        } else if (state instanceof Furnace) {
            inv = ((Furnace) state).getInventory();
        }

        if (inv == null) return;

        boolean changed = false;
        ItemStack[] contents = inv.getContents();

        for (int i = 0; i < contents.length; i++) {
            ItemStack item = contents[i];
            if (item != null && item.getDurability() < 0) {
                contents[i] = null;
                changed = true;
            }
        }

        if (changed) {
            inv.setContents(contents);
        }
    }
}