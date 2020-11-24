package pl.natusiek.mc.database

import com.mongodb.MongoClientURI
import org.bukkit.Bukkit
import pl.natusiek.mc.common.configuration.ConfigurationService
import pl.natusiek.mc.common.module.Module
import pl.natusiek.mc.common.module.ModuleAnnotation
import pl.natusiek.mc.common.builder.LoggerBuilder
import pl.natusiek.mc.database.system.Database
import pl.natusiek.mc.database.config.DatabaseConfig
import java.lang.Exception

@ModuleAnnotation("ns-Database", "1.0")
class DatabaseModule : Module<DatabaseConfig>() {

    lateinit var database: Database

    override lateinit var configuration: DatabaseConfig
    override lateinit var logger: LoggerBuilder

    @ExperimentalStdlibApi
    override fun onStart() {
        this.configuration = ConfigurationService.load(this.plugin.dataFolder, this.configuration::class)!!
        this.logger = LoggerBuilder("ns-Database")

        try {
            this.database = Database(MongoClientURI(this.configuration.url), this.configuration.database)
        } catch (e: Exception) {
            this.logger.error("Can't connect to database...")
            Bukkit.shutdown()
        }

        DatabaseAPI.database = this.database
    }

    override fun onStop() {

    }

}