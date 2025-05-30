package pl.natusiek.mc.common.helper

import org.bukkit.inventory.Inventory
import org.bukkit.inventory.ItemStack
import java.util.*

object ItemHelper {

    fun hasItem(inventory: Inventory, item: ItemStack): Boolean = inventory.containsAtLeast(item, item.amount)

    fun hasItem(inventory: Inventory, item: ItemStack, amount: Int): Boolean = inventory.containsAtLeast(item, amount)

    fun hasItems(inventory: Inventory, items: List<ItemStack>): Boolean {
        items.forEach {
            if (!inventory.containsAtLeast(it, it.amount))
                return false
        }
        return true
    }

}