package pl.natusiek.mc.guild.command.guild.impl

import co.aikar.commands.annotation.*
import com.sun.tools.javac.tree.TreeInfo.args
import org.bukkit.entity.Player
import pl.natusiek.mc.GuildPlugin
import pl.natusiek.mc.common.extension.sendFixedMessage
import pl.natusiek.mc.guild.command.guild.GuildCommand
import pl.natusiek.mc.guild.event.default.RemoveMemberGuildEvent


@CommandAlias("g|guild|gildia|klany|clan")
class KickGuildCommand(private val plugin: GuildPlugin) : GuildCommand(plugin) {

    @Subcommand("wyrzuc|kick")
    @Syntax("(nick)")
    @CommandCompletion("@players")
    fun onCommand(sender: Player, @Flags("target") target: Player?) {
        val senderGuild = this.plugin.guildRepository.getGuildByMemberId(sender.uniqueId)
                ?: return sender.sendFixedMessage(this.message.uNotHaveGuild)

        if (!senderGuild.isLeader(sender.uniqueId))
            return sender.sendFixedMessage(this.message.uNotLeader)

        val other = target ?: return sender.sendFixedMessage(this.message.targetIsOffline)
        if (senderGuild.getMember(other.uniqueId) == null)
            return sender.sendFixedMessage(this.message.kick.targetIsNotUGuild)

        this.plugin.server.pluginManager.callEvent(
                RemoveMemberGuildEvent(other, sender, senderGuild, true)
        )

    }

}