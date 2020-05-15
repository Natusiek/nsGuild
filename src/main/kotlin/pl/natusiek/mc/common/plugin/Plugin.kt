package pl.natusiek.mc.common.plugin

import co.aikar.commands.BaseCommand
import co.aikar.commands.ExceptionHandler
import co.aikar.commands.PaperCommandManager
import org.bukkit.Bukkit
import org.bukkit.event.Listener
import org.bukkit.plugin.java.JavaPlugin
import pl.natusiek.mc.common.extension.fixColor
import java.util.*

abstract class Plugin : JavaPlugin() {

    private val paperCommandManager = PaperCommandManager(this)

    init {
        this.paperCommandManager.defaultExceptionHandler = ExceptionHandler { command, _, sender, _, _ ->
            sender.sendMessage("&4Ups! &cWystapil blad z komenda: ${command.name}, zglos to jak najszybciej do admina".fixColor())
            true
        }
        this.paperCommandManager.addSupportedLanguage(Locale("pl"))
        this.paperCommandManager.locales.defaultLocale = Locale("pl")
    }

    private fun <T> registerCommand(command: T) where T : BaseCommand {
        this.paperCommandManager.registerCommand(command)
    }

    fun <T> registerCommands(vararg commands: T) where T : BaseCommand {
        commands.forEach { registerCommand(it) }
    }

    fun registerListeners(vararg listeners: Listener) {
        listeners.forEach { Bukkit.getPluginManager().registerEvents(it, this) }
    }

}