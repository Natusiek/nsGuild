package pl.natusiek.mc.guild.event.default

import org.bukkit.event.HandlerList

import pl.natusiek.mc.guild.event.GuildEvent
import pl.natusiek.mc.guild.structure.guild.Guild

class RemoveGuildEvent(
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