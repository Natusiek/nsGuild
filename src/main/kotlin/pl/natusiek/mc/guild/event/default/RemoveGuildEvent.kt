package pl.natusiek.mc.guild.event.default

import pl.natusiek.mc.guild.event.GuildEvent
import pl.natusiek.mc.guild.structure.guild.Guild

class RemoveGuildEvent(
        val guild: Guild
): GuildEvent()