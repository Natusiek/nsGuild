package pl.natusiek.mc.guild

import org.bukkit.Location
import pl.natusiek.mc.common.helper.SchematicHelper
import java.io.File

object GuildAPI {

    lateinit var plugin: GuildModule


    fun pasteSchematic(location: Location) = SchematicHelper.pasteSchematic(this.plugin.schematicFile, location)

}