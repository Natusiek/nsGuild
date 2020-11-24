package pl.natusiek.mc.common.configuration

import com.google.gson.GsonBuilder
import org.bukkit.inventory.ItemStack
import pl.natusiek.mc.common.builder.LoggerBuilder
import java.io.File
import java.io.IOException
import java.nio.file.Files
import kotlin.reflect.KClass
import kotlin.reflect.full.findAnnotation
import kotlin.reflect.full.hasAnnotation

object ConfigurationService {

    private val gson
        get() = GsonBuilder()
                .setPrettyPrinting()
                .disableHtmlEscaping()
                .create()

    @ExperimentalStdlibApi
    fun <T: Configuration> load(dataFolder: File, clazz: KClass<T>): T? {

        if (!clazz.hasAnnotation<ConfigurationAnnotation>()) {
            LoggerBuilder(clazz.simpleName!!).error("Failed to load config class because it has no have annotation")
            return null
        }
        val annotation = clazz.findAnnotation<ConfigurationAnnotation>()!!

        val folder = File(dataFolder, annotation.folderName)
        val file = File(folder, "${annotation.fileName}.json")

        if (!file.exists())
            this.save(dataFolder, clazz)

        val stringConfig = file.readText(Charsets.UTF_8)


        return this.gson.fromJson(stringConfig, clazz.java)
    }

    fun <T: Configuration> save(dataFolder: File, clazz: KClass<T>) {
        val annotation = clazz.findAnnotation<ConfigurationAnnotation>()!!

        val folder = File(dataFolder, annotation.folderName)
        val file = File(folder, "${annotation.fileName}.json")
        this.checkFile(file)

        val json = this.gson.toJson(clazz.java.newInstance())

        Files.write(file.toPath(), json.toByteArray(Charsets.UTF_8))
    }

    private fun checkFile(file: File) {
        if (!file.exists()) {
            file.parentFile.mkdirs()
            try {
                file.createNewFile()
            } catch (ex: IOException) {
                ex.printStackTrace()
            }
        }
    }

}