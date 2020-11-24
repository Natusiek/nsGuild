package pl.natusiek.mc.common.module

import pl.natusiek.mc.Plugin
import pl.natusiek.mc.common.builder.LoggerBuilder
import pl.natusiek.mc.common.configuration.Configuration

abstract class Module<T : Configuration> : ModuleService {

    val plugin get() = Plugin()

    abstract var configuration: T

    abstract var logger: LoggerBuilder

}
