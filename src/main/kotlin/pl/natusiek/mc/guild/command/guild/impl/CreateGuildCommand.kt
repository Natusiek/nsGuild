package pl.natusiek.mc.guild.command.guild.impl

import co.aikar.commands.annotation.CommandAlias
import co.aikar.commands.annotation.Subcommand
import co.aikar.commands.annotation.Syntax
import org.bukkit.entity.Player
import pl.natusiek.mc.GuildPlugin
import pl.natusiek.mc.common.extension.sendFixedMessage
import pl.natusiek.mc.common.helper.ItemHelper
import pl.natusiek.mc.guild.command.guild.GuildCommand
import pl.natusiek.mc.guild.event.default.CreateGuildEvent
import pl.natusiek.mc.guild.structure.guild.Guild
import pl.natusiek.mc.guild.structure.guild.GuildMember
import java.util.*
import kotlin.collections.ArrayList

@CommandAlias("g|guild|gildia|klany|clan")
class CreateGuildCommand(private val plugin: GuildPlugin) : GuildCommand(plugin) {

    @Subcommand("zaloz")
    @Syntax("(tag) (name)")
    fun onCreate(sender: Player, tag: String, name: String) {
        val pattern = "^[0-9a-zA-Z-_]+$".toRegex()
        if (!tag.matches(pattern) || !name.matches(pattern))
            return sender.sendFixedMessage(this.message.unknownCharacters)

        if (tag.length < this.config.create.minLengthInTag)
            return sender.sendFixedMessage(this.message.create.minLengthTag.replace("%lenght%", this.config.create.minLengthInTag.toString()))

        if (tag.length > this.config.create.maxLengthInTag)
            return sender.sendFixedMessage(this.message.create.maxLengthTag.replace("%lenght%", this.config.create.maxLengthInTag.toString()))

        if (name.length > this.config.create.maxLengthInName)
            return sender.sendFixedMessage(this.message.create.maxLengthName.replace("%lenght%", this.config.create.maxLengthInName.toString()))

        if (this.guildRepository.getGuildByMemberId(sender.uniqueId) != null)
            return sender.sendFixedMessage(this.message.uHaveGuild)

        if (this.guildRepository.getGuildByTag(tag) != null)
            return sender.sendFixedMessage(this.message.create.tagIsBusy)

        if (this.guildRepository.getGuildByName(name) != null)
            return sender.sendFixedMessage(this.message.create.nameIsBusy)

        val itemsToCreate = this.config.itemsToCreate.items
        if (!ItemHelper.hasItems(sender.inventory, itemsToCreate.map { it.toItem() }))
            return sender.sendFixedMessage(this.message.create.noHaveItemToCreate)

        var randomGuildId = UUID.randomUUID()
        while (this.guildRepository.getGuildById(randomGuildId) != null)
            randomGuildId = UUID.randomUUID()

        val guild = Guild(randomGuildId, tag, name, GuildMember(sender.uniqueId, sender.name))
        this.plugin.server.pluginManager.callEvent(CreateGuildEvent(sender, guild))

    }

}

