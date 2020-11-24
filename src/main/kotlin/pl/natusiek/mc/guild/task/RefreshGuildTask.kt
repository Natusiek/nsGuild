package pl.natusiek.mc.guild.task

import org.bukkit.scheduler.BukkitRunnable
import pl.natusiek.mc.guild.GuildModule

class RefreshGuildTask(private val plugin: GuildModule) : BukkitRunnable() {

    init {
        runTaskLaterAsynchronously(this.plugin, 540)
    }

    override fun run() {
        this.plugin.guildRepository.getGuilds().forEach {
                it.memberInvites.clear()
                it.alliesInvites.clear()
        }
    }


}