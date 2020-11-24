package pl.natusiek.mc.common.builder

data class LoggerBuilder(
        private val module: String
) {

    fun info(text: String) = println(" INFO - ${this.module}: $text")

    fun warn(text: String) = println(" WARN - ${this.module}: $text")

    fun error(text: String) = println(" ERROR - ${this.module}: $text")

}