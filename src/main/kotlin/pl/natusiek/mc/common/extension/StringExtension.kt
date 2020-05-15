package pl.natusiek.mc.common.extension

import org.bukkit.ChatColor

fun String.fixColor(): String = ChatColor.translateAlternateColorCodes('&', this)
