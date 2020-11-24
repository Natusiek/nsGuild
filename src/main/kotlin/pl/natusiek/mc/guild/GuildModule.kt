package pl.natusiek.mc.guild

import pl.natusiek.mc.common.configuration.ConfigurationService
import pl.natusiek.mc.common.module.Module
import pl.natusiek.mc.common.module.ModuleAnnotation
import pl.natusiek.mc.database.config.DatabaseConfig
import pl.natusiek.mc.guild.command.guild.impl.*
import pl.natusiek.mc.guild.config.GuildConfig
import pl.natusiek.mc.guild.config.GuildMessage
import pl.natusiek.mc.guild.listener.GuildListener
import pl.natusiek.mc.guild.structure.guild.GuildRepository
import pl.natusiek.mc.guild.structure.user.UserRepository

import java.io.File
import java.io.IOException
import javax.naming.ConfigurationException

@ModuleAnnotation("ns-Guild", "1.0")
class GuildModule() : Module<GuildConfig>() {

    lateinit var guildRepository: GuildRepository
    lateinit var userRepository: UserRepository

    override lateinit var configuration: GuildConfig

    private val folder = this.plugin.dataFolder
    private val folderSchematic = File(this.folder, "schematics")
    val schematicFile = File(this.folderSchematic, "cuboid.schematic")

    override fun onStart() {
        this.guildRepository = GuildRepository(this)
        this.userRepository = UserRepository(this)

        ConfigurationService.load(this.configuration::class)

        this.loadSchematic()
        this.plugin.registerCommands(
                CreateGuildCommand(this),
                EnlargeGuildCommand(this),
                InviteGuildCommand(this),
                JoinGuildCommand(this),
                KickGuildCommand(this),
                LeaveGuildCommand(this)
        )

        this.plugin.registerListeners(
                GuildListener(this)
        )

        GuildAPI.plugin = this
    }

    override fun onStop() {

    }

    private fun loadSchematic() {
        if (!this.folderSchematic.exists()) this.folderSchematic.mkdirs()
        if (!this.schematicFile.exists()) {
            try {
                this.schematicFile.createNewFile()
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }
    }

}