package pl.natusiek.mc

import pl.natusiek.mc.common.configuration.ConfigLoader
import pl.natusiek.mc.common.plugin.Plugin
import pl.natusiek.mc.database.DatabaseModule
import pl.natusiek.mc.guild.command.guild.impl.*
import pl.natusiek.mc.guild.config.GuildConfig
import pl.natusiek.mc.guild.config.GuildMessage
import pl.natusiek.mc.guild.listener.GuildListener
import pl.natusiek.mc.guild.structure.guild.GuildRepository
import pl.natusiek.mc.guild.structure.user.UserRepository
import java.io.File
import java.io.IOException

class GuildPlugin : Plugin() {

    lateinit var databaseModule: DatabaseModule
    lateinit var guildRepository: GuildRepository
    lateinit var userRepository: UserRepository

    lateinit var guildConfig: GuildConfig
    lateinit var guildMessage: GuildMessage

    private val folder = File(this.dataFolder, "schematics")
    private val schematicFile = File(this.folder, "cuboid.schematic")

    override fun onEnable() {
        this.databaseModule = DatabaseModule(this)
        this.guildRepository = GuildRepository(this)
        this.userRepository = UserRepository(this)

        this.guildConfig = ConfigLoader.load(this.dataFolder, GuildConfig::class, "config")
        this.guildMessage = ConfigLoader.load(this.dataFolder, GuildMessage::class, "message")

        this.loadSchematic()
        this.registerCommands(
                CreateGuildCommand(this),
                EnlargeGuildCommand(this),
                InviteGuildCommand(this),
                JoinGuildCommand(this),
                KickGuildCommand(this),
                LeaveGuildCommand(this)
        )

        this.registerListeners(
                GuildListener(this)
        )

        GuildAPI.plugin = this
        GuildAPI.schematicFile = this.schematicFile
    }

    private fun loadSchematic() {
        if (!this.folder.exists()) this.folder.mkdirs()
        if (!this.schematicFile.exists()) {
            try {
                this.schematicFile.createNewFile()
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }
    }

}