package pl.natusiek.mc.guild.command.guild.impl

import co.aikar.commands.annotation.*
import org.bukkit.entity.Player
import pl.natusiek.mc.GuildPlugin
import pl.natusiek.mc.common.extension.sendFixedMessage
import pl.natusiek.mc.common.helper.ItemHelper
import pl.natusiek.mc.guild.command.guild.GuildCommand
import pl.natusiek.mc.guild.event.default.AddMemberGuildEvent

@CommandAlias("g|guild|gildia|klany|clan")
class JoinGuildCommand(private val plugin: GuildPlugin) : GuildCommand(plugin) {

    @Subcommand("join|dolacz")
    @Syntax("(tag)")
    fun onCommand(sender: Player, tag: String) {
        val senderGuild = this.guildRepository.getGuildByMemberId(sender.uniqueId)

        if (senderGuild != null)
            return sender.sendFixedMessage(this.message.uHaveGuild)

        val targetGuild = this.guildRepository.getGuildByTag(tag)
                ?: return sender.sendFixedMessage(this.message.noSuchGuild)

        if (sender.uniqueId !in targetGuild.memberInvites)
            return sender.sendFixedMessage(this.message.join.uNoHaveInvitation)

        val inventory = sender.inventory

        val itemToJoin = this.config.join.itemToJoin.toItem()
        var amount = itemToJoin.amount
        if (this.config.join.converterByMember) {
            val member = targetGuild.members.size
            amount *= member
        }

        if (!ItemHelper.hasItem(inventory, itemToJoin, amount))
            return sender.sendFixedMessage(this.message.join.noHaveItem)

        this.plugin.server.pluginManager.callEvent(
                AddMemberGuildEvent(sender, targetGuild)
        )
    }

}