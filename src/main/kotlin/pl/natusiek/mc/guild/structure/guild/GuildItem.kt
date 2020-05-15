package pl.natusiek.mc.guild.structure.guild

import org.bukkit.Material
import org.bukkit.inventory.Inventory
import pl.natusiek.mc.common.builder.ItemBuilder
import pl.natusiek.mc.common.helper.ItemHelper

class GuildItem(
        val material: Material,
        val name: String,
        val amount: Int,
        val data: Short,
        val lore: ArrayList<String>
) {

    fun hasItem(inventory: Inventory) = ItemHelper.hasItem(inventory, toItem())

    fun toItem() = ItemBuilder(this.material, if(this.name == "ITEM") this.material.name else this.name, this.amount, this.data, lore).build()

}