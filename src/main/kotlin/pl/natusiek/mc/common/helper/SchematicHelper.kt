package pl.natusiek.mc.common.helper

import com.sk89q.worldedit.MaxChangedBlocksException
import com.sk89q.worldedit.Vector
import com.sk89q.worldedit.WorldEdit
import com.sk89q.worldedit.bukkit.BukkitWorld
import com.sk89q.worldedit.extent.clipboard.io.ClipboardFormat
import com.sk89q.worldedit.function.operation.Operations
import com.sk89q.worldedit.session.ClipboardHolder

import org.bukkit.Location
import java.io.File
import java.io.FileInputStream

object SchematicHelper {

    fun pasteSchematic(schematicFile: File, location: Location) {
        try {
            val pasteLocation = Vector(location.x, location.y, location.z)
            val pasteWorld = BukkitWorld(location.world)
            val pasteWorldData = pasteWorld.worldData

            val clipboard = ClipboardFormat.SCHEMATIC.getReader(FileInputStream(schematicFile)).read(pasteWorldData)
            val clipboardHolder = ClipboardHolder(clipboard, pasteWorldData)

            val editSession = WorldEdit.getInstance().editSessionFactory.getEditSession(pasteWorld, -1)
            val operation = clipboardHolder
                .createPaste(editSession, pasteWorldData)
                .to(pasteLocation)
                .ignoreAirBlocks(false)
                .build()

            Operations.completeLegacy(operation)
        } catch (ex: MaxChangedBlocksException) {
            ex.printStackTrace()
        }
    }

}