package pl.natusiek.mc.guild.command.guild.impl

import co.aikar.commands.annotation.CommandCompletion
import co.aikar.commands.annotation.Flags
import co.aikar.commands.annotation.Subcommand
import co.aikar.commands.annotation.Syntax
import org.bukkit.entity.Player
import pl.natusiek.mc.GuildPlugin
import pl.natusiek.mc.common.extension.sendFixedMessage
import pl.natusiek.mc.guild.command.guild.GuildCommand
import pl.natusiek.mc.guild.event.default.RemoveMemberGuildEvent

class LeaveGuildCommand(private val plugin: GuildPlugin) : GuildCommand(plugin) {

    @Subcommand("leave|opusc")
    fun onCommand(sender: Player) {
        val senderGuild = this.plugin.guildRepository.getGuildByMemberId(sender.uniqueId)
                ?: return sender.sendFixedMessage(this.message.uNotHaveGuild)

        if (senderGuild.isLeader(sender.uniqueId))
            return sender.sendFixedMessage(this.message.uLeader)

        this.plugin.server.pluginManager.callEvent(
                RemoveMemberGuildEvent(null, sender, senderGuild, false)
        )

    }

}