package net.utils.voidutils;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.block.BlockListener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerListener;
import org.bukkit.inventory.ItemStack;

import java.util.EnumSet;

public class NoIllegals {

    public static class PlaceListener extends BlockListener {
        private static final EnumSet<Material> illegalBlocks = NoIllegals.illegalBlocks;

        @Override
        public void onBlockPlace(BlockPlaceEvent event) {
            Material type = event.getBlockPlaced().getType();
            Player player = event.getPlayer();

            if (illegalBlocks.contains(type)) {
                if (type == Material.FIRE) {

                    ItemStack hand = player.getItemInHand();
                    if (hand != null && hand.getType() == Material.FLINT_AND_STEEL) {
                        return;
                    } else {
                        event.setCancelled(true);
                    }
                } else if (type == Material.PORTAL) {

                    event.setCancelled(true);
                } else if (illegalBlocks.contains(type)) {
                    event.setCancelled(true);
                }
            }
        }
    }

    public static class JoinListener extends PlayerListener {
        private static final EnumSet<Material> illegalBlocks = NoIllegals.illegalBlocks;

        @Override
        public void onPlayerJoin(PlayerJoinEvent event) {
            Player player = event.getPlayer();
            ItemStack[] contents = player.getInventory().getContents();
            for (int i = 0; i < contents.length; i++) {
                if (contents[i] != null && illegalBlocks.contains(contents[i].getType())) {
                    contents[i] = null;
                }
            }
            player.getInventory().setContents(contents);
        }
    }

    private static final EnumSet<Material> illegalBlocks = EnumSet.of(
            Material.FIRE,
            Material.BEDROCK,
            Material.LAVA,
            Material.STATIONARY_LAVA,
            Material.WATER,
            Material.STATIONARY_WATER,
            Material.MOB_SPAWNER,
            Material.PISTON_BASE,
            Material.PISTON_STICKY_BASE
    );
}
