package pl.natusiek.mc.guild.event.default

import org.bukkit.entity.Player
import pl.natusiek.mc.guild.event.GuildEvent
import pl.natusiek.mc.guild.structure.guild.Guild

class InviteMemberGuildEvent(
        val target: Player,
        val sender: Player,
        val guild: Guild
): GuildEvent()