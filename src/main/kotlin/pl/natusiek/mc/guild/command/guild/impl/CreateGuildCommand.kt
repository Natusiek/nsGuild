package pl.natusiek.mc.guild.command.guild.impl

import co.aikar.commands.annotation.CommandAlias
import co.aikar.commands.annotation.Subcommand
import co.aikar.commands.annotation.Syntax
import org.apache.commons.lang.StringUtils
import org.bukkit.entity.Player
import pl.natusiek.mc.guild.GuildModule
import pl.natusiek.mc.common.extension.sendFixedMessage
import pl.natusiek.mc.common.helper.ItemHelper
import pl.natusiek.mc.common.helper.LocationHelper
import pl.natusiek.mc.guild.command.guild.GuildCommand
import pl.natusiek.mc.guild.event.default.CreateGuildEvent
import pl.natusiek.mc.guild.structure.guild.Guild
import pl.natusiek.mc.guild.structure.guild.GuildMember
import java.util.*

@CommandAlias("g|guild|gildia|klany|clan")
class CreateGuildCommand(private val plugin: GuildModule) : GuildCommand(plugin) {

    @Subcommand("zaloz")
    @Syntax("(tag) (name)")
    fun onCommand(sender: Player, tag: String, name: String) {
        if (!this.config.createGuild)
            return sender.sendFixedMessage(this.message.create.guildIsNotEnable)

        val pattern = "^[0-9a-zA-Z-_]+$".toRegex()
        if (!tag.matches(pattern) || !name.matches(pattern))
            return sender.sendFixedMessage(this.message.unknownCharacters)

        if (tag.length < this.config.create.minLengthInTag)
            return sender.sendFixedMessage(StringUtils.replace(this.message.create.minLengthTag, "%lenght%", this.config.create.minLengthInTag.toString()))

        if (tag.length > this.config.create.maxLengthInTag)
            return sender.sendFixedMessage(StringUtils.replace(this.message.create.maxLengthTag, "%lenght%", this.config.create.maxLengthInTag.toString()))

        if (name.length > this.config.create.maxLengthInName)
            return sender.sendFixedMessage(StringUtils.replace(this.message.create.maxLengthName, "%lenght%", this.config.create.maxLengthInName.toString()))

        if (this.guildRepository.getGuildByMemberId(sender.uniqueId) != null)
            return sender.sendFixedMessage(this.message.uHaveGuild)

        if (this.guildRepository.getGuildByTag(tag) != null)
            return sender.sendFixedMessage(this.message.create.tagIsBusy)

        if (this.guildRepository.getGuildByName(name) != null)
            return sender.sendFixedMessage(this.message.create.nameIsBusy)

        val itemsToCreate = this.config.itemsToCreate.items
        if (!ItemHelper.hasItems(sender.inventory, itemsToCreate.map { it.toItem() }))
            return sender.sendFixedMessage(this.message.create.noHaveItemToCreate)

        val senderLocation = sender.location.block.location
        if (LocationHelper.inLocation(this.config.spawnLocation.toBukkitLocation(), senderLocation, this.config.create.distanceFromSpawn))
            return sender.sendFixedMessage(this.message.create.uAreInSpawn)

        if (this.guildRepository.getGuildByLocation(senderLocation) != null)
            return sender.sendFixedMessage(this.message.uAreInGuildRegion)

        if (this.guildRepository.getGuildByDistance(senderLocation, this.config.create.maxRegion + this.config.create.distanceBetweenGuild) != null)
            return sender.sendFixedMessage(this.message.create.tooCloseGuild)

        if (this.config.create.customYLocation)
            senderLocation.y = this.plugin.guildConfig.create.yLocation

        var randomGuildId = UUID.randomUUID()
        while (this.guildRepository.getGuildById(randomGuildId) != null)
            randomGuildId = UUID.randomUUID()

        val guild = Guild(randomGuildId, tag, name, GuildMember(sender.uniqueId, sender.name))
        this.plugin.server.pluginManager.callEvent(
                CreateGuildEvent(sender, guild, senderLocation)
        )
        sender.teleport(senderLocation)
    }

}

