package net.utils.voidutils;

import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerListener;
import org.bukkit.inventory.ItemStack;

public class NegativeItemScan extends PlayerListener {

    @Override
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        ItemStack[] contents = player.getInventory().getContents();

        boolean changed = false;

        for (int i = 0; i < contents.length; i++) {
            ItemStack item = contents[i];
            if (item != null) {
                if (item.getAmount() <= 0 || item.getTypeId() <= 0 || item.getDurability() < 0) {
                    contents[i] = null;
                    changed = true;
                }
            }
        }

        if (changed) {
            player.getInventory().setContents(contents);
        }
    }
}