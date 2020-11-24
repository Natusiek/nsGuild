package pl.natusiek.mc.database.config

import pl.natusiek.mc.common.configuration.Configuration
import pl.natusiek.mc.common.configuration.ConfigurationAnnotation

@ConfigurationAnnotation("database", "config")
class DatabaseConfig : Configuration {

    val url = "mongodb://localhost:3306/guild"
    val database = "guild"

}