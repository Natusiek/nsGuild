package pl.natusiek.mc.guild.listener

import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import pl.natusiek.mc.GuildPlugin
import pl.natusiek.mc.guild.event.default.AddMemberGuildEvent
import pl.natusiek.mc.guild.event.default.CreateGuildEvent
import pl.natusiek.mc.guild.event.default.RemoveGuildEvent
import pl.natusiek.mc.guild.event.default.RemoveMemberGuildEvent

class GuildListener(private val plugin: GuildPlugin) : Listener {

    @EventHandler
    fun onCreateGuild(event: CreateGuildEvent) {

    }

    @EventHandler
    fun onCreateGuild(event: RemoveGuildEvent) {

    }

    @EventHandler
    fun onAddMemberToGuild(event: AddMemberGuildEvent) {

    }

    @EventHandler
    fun onRemoveMemberOfGuild(event: RemoveMemberGuildEvent) {

    }


}