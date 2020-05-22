package pl.natusiek.mc.guild.command.guild.impl

import co.aikar.commands.annotation.Subcommand
import org.bukkit.entity.Player
import pl.natusiek.mc.GuildPlugin
import pl.natusiek.mc.common.extension.sendFixedMessage
import pl.natusiek.mc.common.helper.ItemHelper
import pl.natusiek.mc.guild.command.guild.GuildCommand
import pl.natusiek.mc.guild.event.default.EnlargeGuildEvent

class EnlargeGuildCommand(private val plugin: GuildPlugin) : GuildCommand(plugin) {

    @Subcommand("enlarge|powieksz")
    fun onCommand(sender: Player) {
        val senderGuild = this.guildRepository.getGuildByMemberId(sender.uniqueId)
                ?: return sender.sendFixedMessage(this.message.uNotHaveGuild)

        if (!senderGuild.isLeader(sender.uniqueId))
            return sender.sendFixedMessage(this.message.uNotLeader)

        if (senderGuild.region.regionSize == this.config.create.maxRegion)
            return sender.sendFixedMessage(this.message.enlarge.maxRegionReached)

        val itemsToCreate = this.config.enlarge.itemToEnlarge
        if (!ItemHelper.hasItems(sender.inventory, itemsToCreate.map { it.toItem() }))
            return sender.sendFixedMessage(this.message.enlarge.noHaveItem)

        this.plugin.server.pluginManager.callEvent(
                EnlargeGuildEvent(sender, senderGuild)
        )
    }

}