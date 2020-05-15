package pl.natusiek.mc

import pl.natusiek.mc.common.configuration.ConfigLoader
import pl.natusiek.mc.common.plugin.Plugin
import pl.natusiek.mc.database.DatabaseModule
import pl.natusiek.mc.guild.config.GuildConfig
import pl.natusiek.mc.guild.config.GuildMessage
import pl.natusiek.mc.guild.structure.guild.GuildRepository
import pl.natusiek.mc.guild.structure.user.UserRepository

class GuildPlugin : Plugin() {

    lateinit var databaseModule: DatabaseModule
    lateinit var guildRepository: GuildRepository
    lateinit var userRepository: UserRepository

    lateinit var guildConfig: GuildConfig
    lateinit var guildMessage: GuildMessage

    override fun onEnable() {
        this.databaseModule = DatabaseModule(this)
        this.guildRepository = GuildRepository(this)
        this.userRepository = UserRepository(this)

        this.guildConfig = ConfigLoader.load(this.dataFolder, GuildConfig::class, "config")
        this.guildMessage = ConfigLoader.load(this.dataFolder, GuildMessage::class, "message")
    }

}