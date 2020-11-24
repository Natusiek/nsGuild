package pl.natusiek.mc.common.configuration

@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.CLASS)
annotation class ConfigurationAnnotation(val folderName: String, val fileName: String)