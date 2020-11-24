@file:Suppress("NAME_SHADOWING")

package pl.natusiek.mc.common.module

import org.bukkit.Bukkit
import java.util.*

object ModuleLoader {

    private val modules: MutableSet<ModuleService> = LinkedHashSet()

    fun registerModules(vararg modules: ModuleService) {
        modules.forEach {
            registerModule(it)
        }
    }

    private fun registerModule(module: ModuleService) {
        val moduleClass = module.javaClass
        if (!moduleClass.isAnnotationPresent(ModuleAnnotation::class.java))
            return Bukkit.getLogger().warning(" ${moduleClass.name}: Failed to initialize class because it has no have annotation")

        modules.add(module)
    }

}
