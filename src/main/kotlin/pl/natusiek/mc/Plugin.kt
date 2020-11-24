package pl.natusiek.mc

import pl.natusiek.mc.common.module.ModuleLoader
import pl.natusiek.mc.common.plugin.Plugin

class Plugin : Plugin() {

    override fun onEnable() {
        ModuleLoader.registerModules(
        )
    }

}