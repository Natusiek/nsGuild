package pl.natusiek.mc.guild.command.guild.impl

import co.aikar.commands.annotation.*
import org.bukkit.entity.Player
import pl.natusiek.mc.GuildPlugin
import pl.natusiek.mc.common.extension.sendFixedMessage
import pl.natusiek.mc.guild.command.guild.GuildCommand
import pl.natusiek.mc.guild.event.default.AddMemberGuildEvent
import pl.natusiek.mc.guild.event.default.CreateGuildEvent

@CommandAlias("g|guild|gildia|klany|clan")
class InviteGuildCommand(private val plugin: GuildPlugin) : GuildCommand(plugin) {

    @Subcommand("invite|zazpros")
    @Syntax("(nick)")
    @CommandCompletion("@players")
    fun inviteGuildCommand(sender: Player, @Flags("target") target: Player?) {
        val senderGuild = this.guildRepository.getGuildByMemberId(sender.uniqueId)
                ?: return sender.sendFixedMessage(this.message.uNotHaveGuild)

        if (!senderGuild.isLeaderById(sender.uniqueId))
            return sender.sendFixedMessage(this.message.uNotLeader)

        val other = target
                ?: return sender.sendFixedMessage(this.message.targetIsOffline)

        if(this.guildRepository.getGuildByMemberId(other.uniqueId) != null)
            return sender.sendFixedMessage(this.message.targetHaveGuild)

        this.plugin.server.pluginManager.callEvent(AddMemberGuildEvent(other, senderGuild))
    }

}