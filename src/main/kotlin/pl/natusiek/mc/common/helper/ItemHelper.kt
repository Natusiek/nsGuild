package pl.natusiek.mc.common.helper

import org.bukkit.inventory.Inventory
import org.bukkit.inventory.ItemStack

object ItemHelper {

    fun hasItem(inventory: Inventory, item: ItemStack): Boolean = inventory.contents.singleOrNull { item.isSimilar(it) } != null

    fun hasItems(inventory: Inventory, items: List<ItemStack>): Boolean {
        items.forEach { item ->
            if (!inventory.containsAtLeast(item, item.amount))
                return false
        }
        return true
    }

}