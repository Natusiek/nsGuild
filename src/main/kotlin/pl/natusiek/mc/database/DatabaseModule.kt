package pl.natusiek.mc.database

import com.mongodb.MongoClientURI
import org.bukkit.Bukkit
import pl.natusiek.mc.GuildPlugin
import pl.natusiek.mc.common.configuration.ConfigLoader
import pl.natusiek.mc.common.plugin.Module
import pl.natusiek.mc.database.system.Database
import pl.natusiek.mc.database.config.DatabaseConfig
import java.lang.Exception

class DatabaseModule(private val plugin: GuildPlugin) : Module {

    lateinit var databaseConfig: DatabaseConfig
    lateinit var database: Database

    init {
        start()
    }

    override fun start() {
        this.databaseConfig = ConfigLoader.load(this.plugin.dataFolder, DatabaseConfig::class, "database")

        try {
            this.database = Database(MongoClientURI(this.databaseConfig.url), this.databaseConfig.database)
        } catch (e: Exception) {
            this.plugin.logger.warning("Can't connect to database...")
            Bukkit.shutdown()
        }

        DatabaseAPI.database = this.database
    }

    override fun stop() {
    }

}