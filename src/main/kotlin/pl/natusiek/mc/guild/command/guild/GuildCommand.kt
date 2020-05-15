package pl.natusiek.mc.guild.command.guild

import co.aikar.commands.BaseCommand
import co.aikar.commands.annotation.CommandAlias
import co.aikar.commands.annotation.CommandPermission
import pl.natusiek.mc.GuildPlugin

@CommandAlias("g|guild|gildia|klany|clan")
abstract class GuildCommand(private val plugin: GuildPlugin) : BaseCommand() {

    val guildRepository = this.plugin.guildRepository
    val config = this.plugin.guildConfig
    val message = this.plugin.guildMessage

}