package pl.natusiek.mc.common.module

@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.CLASS)
annotation class ModuleAnnotation(val name: String, val version: String)