package net.utils.voidutils;

import org.bukkit.entity.Player;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerListener;
import org.bukkit.event.block.BlockListener;
import org.bukkit.inventory.ItemStack;

public class NoNegativeItems {

    public static class PlaceListener extends BlockListener {
        @Override
        public void onBlockPlace(BlockPlaceEvent event) {
            ItemStack inHand = event.getItemInHand();
            if (inHand != null && inHand.getDurability() < 0) {
                event.setCancelled(true);
            }
        }
    }

    public static class JoinListener extends PlayerListener {
        @Override
        public void onPlayerJoin(PlayerJoinEvent event) {
            Player player = event.getPlayer();
            ItemStack[] inv = player.getInventory().getContents();

            for (int i = 0; i < inv.length; i++) {
                ItemStack item = inv[i];
                if (item != null && item.getDurability() < 0) {
                    inv[i] = null;
                }
            }

            player.getInventory().setContents(inv);
        }
    }
}
