package pl.natusiek.mc.guild.config

import org.bukkit.Material
import pl.natusiek.mc.guild.structure.guild.GuildItem

class GuildConfig {

    val itemsToCreate = ItemsToCreate()
    class ItemsToCreate {
        val items = arrayListOf(
                GuildItem(
                        Material.DIAMOND,
                        "ITEM",
                        1,
                        3,
                        arrayListOf("KOZAK ITEM")
                )
        )
    }

    val create = Create()
    class Create {
        val minLengthInTag = 2
        val maxLengthInTag = 4
        val maxLengthInName = 24
    }

}