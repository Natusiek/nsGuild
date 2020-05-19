package pl.natusiek.mc.guild.event.default

import org.bukkit.entity.Player
import org.bukkit.event.HandlerList
import pl.natusiek.mc.guild.event.GuildEvent
import pl.natusiek.mc.guild.structure.guild.Guild
import java.util.*

class AddMemberGuildEvent(
        val target: Player,
        val sender: Player,
        val guild: Guild
): GuildEvent() {

    override fun getHandlers() = getHandlerList()

    companion object {
        private val handlers = HandlerList()
        @JvmStatic
        fun getHandlerList(): HandlerList {
            return handlers
        }
    }

}