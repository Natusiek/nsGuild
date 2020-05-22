package pl.natusiek.mc

import org.bukkit.Location
import pl.natusiek.mc.common.helper.SchematicHelper
import java.io.File

object GuildAPI {

    lateinit var plugin: GuildPlugin
    lateinit var schematicFile: File


    fun pasteSchematic(location: Location) = SchematicHelper.pasteSchematic(this.schematicFile, location)

}