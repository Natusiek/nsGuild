package pl.natusiek.mc.guild.structure.guild

import org.bukkit.Location
import pl.natusiek.mc.GuildPlugin
import pl.natusiek.mc.common.helper.LocationHelper
import pl.natusiek.mc.database.DatabaseAPI
import java.util.*
import java.util.concurrent.ConcurrentHashMap

class GuildRepository(private val plugin: GuildPlugin) {

    private val guilds: MutableMap<UUID, Guild> = ConcurrentHashMap()

    init {
        DatabaseAPI.loadAll<Guild>("guilds") { guilds ->
            guilds.forEach {
                this.guilds[it.guildId] = it
            }
        }
    }

    private fun getGuildBy(guild: (Guild) -> Boolean) = this.guilds.values.find(guild)


    fun getGuildByTag(tag: String) = this.getGuildBy { it.tag == tag }

    fun getGuildByName(name: String) = this.getGuildBy { it.name == name }

    fun getGuildById(id: UUID) = this.getGuildBy { it.guildId == id }

    fun getGuildByMemberId(memberId: UUID) = this.getGuildBy { guild -> guild.members.singleOrNull { it.memberId == memberId } != null }

    fun getGuildByMemberName(memberName: String) = this.getGuildBy { guild -> guild.members.singleOrNull { it.memberName == memberName } != null }

    fun getGuildByLocation(location: Location) = this.getGuildBy { getGuildByDistance(location, it.region.regionSize) != null }

    fun getGuildByDistance(location: Location, distance: Int) = this.getGuildBy { LocationHelper.inLocation(it.region.guildLocation!!, location, distance) }

    fun getGuilds() = this.guilds.values.asSequence()

}