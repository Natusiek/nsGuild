package pl.natusiek.mc.guild.config

import org.bukkit.Material
import pl.natusiek.mc.common.builder.LocationBuilder
import pl.natusiek.mc.common.configuration.Configuration
import pl.natusiek.mc.common.configuration.ConfigurationAnnotation
import pl.natusiek.mc.guild.structure.guild.GuildItem

@ConfigurationAnnotation("guild", "config")
class GuildConfig : Configuration {

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

    val createGuild = true
    val spawnLocation = LocationBuilder("world", 0.0, 60.0,0.0)

    val create = Create()
    class Create {
        val minRegion = 40
        val maxRegion = 80
        val distanceBetweenGuild = 20
        val distanceFromSpawn = 200
        val minLengthInTag = 2
        val maxLengthInTag = 4
        val maxLengthInName = 24
        val customYLocation = true
        val yLocation = 40.0
    }

    val join = Join()
    class Join {
        val converterByMember = true
        val deleteJoinItem = true
        val itemToJoin =
                GuildItem(
                        Material.DIAMOND,
                        "ITEM",
                        10,
                        0,
                        arrayListOf()
                )


    }

    val enlarge = Enlarge()
    class Enlarge {
        val itemToEnlarge = arrayListOf(
                GuildItem(
                        Material.DIAMOND,
                        "ITEM",
                        10,
                        0,
                        arrayListOf()
                )
        )
        val addRegion = 10
    }

}