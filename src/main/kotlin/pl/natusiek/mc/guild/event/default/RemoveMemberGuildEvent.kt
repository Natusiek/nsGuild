package pl.natusiek.mc.guild.event.default

import org.bukkit.entity.Player
import org.bukkit.event.HandlerList
import pl.natusiek.mc.guild.event.GuildEvent
import pl.natusiek.mc.guild.structure.guild.Guild
import java.util.*

class RemoveMemberGuildEvent(
        val target: Player?,
        val sender: Player,
        val guild: Guild
): GuildEvent()