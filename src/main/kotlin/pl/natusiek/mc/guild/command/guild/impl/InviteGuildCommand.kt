package pl.natusiek.mc.guild.command.guild.impl

import co.aikar.commands.annotation.*
import org.bukkit.entity.Player
import pl.natusiek.mc.guild.GuildModule
import pl.natusiek.mc.common.extension.sendFixedMessage
import pl.natusiek.mc.guild.command.guild.GuildCommand
import pl.natusiek.mc.guild.event.default.AddMemberGuildEvent

@CommandAlias("g|guild|gildia|klany|clan")
class InviteGuildCommand(private val plugin: GuildModule) : GuildCommand(plugin) {

    @Subcommand("invite|zapros")
    @Syntax("(nick)")
    @CommandCompletion("@players")
    fun onCommand(sender: Player, @Flags("target") target: Player?) {
        val senderGuild = this.guildRepository.getGuildByMemberId(sender.uniqueId)
                ?: return sender.sendFixedMessage(this.message.uNotHaveGuild)

        if (!senderGuild.isLeader(sender.uniqueId))
            return sender.sendFixedMessage(this.message.uNotLeader)

        val other = target
                ?: return sender.sendFixedMessage(this.message.targetIsOffline)

        if(this.guildRepository.getGuildByMemberId(other.uniqueId) != null)
            return sender.sendFixedMessage(this.message.targetHaveGuild)

        this.plugin.server.pluginManager.callEvent(
                AddMemberGuildEvent(other, senderGuild)
        )
    }

}