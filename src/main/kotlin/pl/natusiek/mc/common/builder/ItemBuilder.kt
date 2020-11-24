package pl.natusiek.mc.common.builder

import org.bukkit.Material
import org.bukkit.enchantments.Enchantment
import org.bukkit.inventory.ItemStack
import pl.natusiek.mc.common.extension.fixColor
import java.io.Serializable

open class ItemBuilder(
    val material: Material,
    val name: String,
    val amount: Int,
    val data: Short,
    val lore: ArrayList<String>,
    val enchant: HashMap<Int, Int>
) : Serializable {

    constructor(material: Material, name: String, amount: Int) : this(material, name, amount, 0, arrayListOf(), hashMapOf())
    constructor(material: Material, name: String, amount: Int, data: Short) : this(material, name, amount, data, arrayListOf(), hashMapOf())
    constructor(material: Material, name: String, amount: Int, data: Short, lore: ArrayList<String>) : this(material, name, amount, data, lore, hashMapOf())

    fun build(): ItemStack {
        var fixedAmount = this.amount
        if (fixedAmount > this.material.maxStackSize) {
            fixedAmount = material.maxStackSize
        } else if (fixedAmount <= 0) {
            fixedAmount = 1
        }
        val item = ItemStack(this.material, fixedAmount, this.data)
        this.enchant.forEach {
            val enchant = Enchantment.getById(it.key)
            if (it.value > enchant.maxLevel) {
                item.addUnsafeEnchantment(enchant, it.value)
            } else {
                item.addEnchantment(enchant, it.value)
            }
        }
        val itemMeta = item.itemMeta ?: return item
        if (this.name.isNotEmpty()) {
            itemMeta.displayName = this.name.fixColor()
        }
        val fixLore = arrayListOf<String>()
        this.lore.forEach {
            fixLore.add(it.fixColor())
        }
        itemMeta.lore = fixLore
        item.itemMeta = itemMeta
        return item
    }

}