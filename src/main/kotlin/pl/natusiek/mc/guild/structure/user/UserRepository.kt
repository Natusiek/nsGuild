package pl.natusiek.mc.guild.structure.user

import pl.natusiek.mc.guild.GuildModule
import pl.natusiek.mc.database.DatabaseAPI
import java.util.*
import java.util.concurrent.ConcurrentHashMap

class UserRepository(private val plugin: GuildModule) {

    private val profiles: MutableMap<UUID, UserProfile> = ConcurrentHashMap()

    init {
        DatabaseAPI.loadAll<UserProfile>("users") { profiles ->
            profiles.forEach {
                this.profiles[it.userId] = it
            }
        }
    }

    private fun getProfileBy(guild: (UserProfile) -> Boolean) = this.profiles.values.find(guild)


    fun getProfileByName(userName: String) = this.getProfileBy { it.userName == userName }

    fun getProfileById(id: UUID) = this.getProfileBy { it.userId == id }

}