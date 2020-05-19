package pl.natusiek.mc.guild.listener

import org.apache.commons.lang.StringUtils
import org.bukkit.Bukkit
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import pl.natusiek.mc.GuildPlugin
import pl.natusiek.mc.common.extension.sendFixedMessage
import pl.natusiek.mc.guild.event.default.AddMemberGuildEvent
import pl.natusiek.mc.guild.event.default.CreateGuildEvent
import pl.natusiek.mc.guild.event.default.RemoveGuildEvent
import pl.natusiek.mc.guild.event.default.RemoveMemberGuildEvent

class GuildListener(private val plugin: GuildPlugin) : Listener {

    @EventHandler
    fun onCreateGuild(event: CreateGuildEvent) {
        val guild = event.guild
        val sender = event.sender


        this.plugin.guildMessage.create.completeCreateGuild.map {
            it.also {
                StringUtils.replace(it, "%guild%", guild.tag)
                StringUtils.replace(it, "%sender%", sender.name)
            }
        }.also { message ->
            this.plugin.server.onlinePlayers.forEach {
                it.sendFixedMessage(*message.toTypedArray())
            }
        }

    }

    @EventHandler
    fun onAddMemberToGuild(event: AddMemberGuildEvent) {
        val guild = event.guild
        val target = event.target
        val sender = event.sender

        this.plugin.guildMessage.invite.targetMessage.map {
            it.also {
                StringUtils.replace(it, "%guild%", guild.tag)
                StringUtils.replace(it, "%sender%", sender.name)
            }
        }.also {
            target.sendFixedMessage(*it.toTypedArray())
        }

        this.plugin.guildMessage.invite.senderMessage.map {
            StringUtils.replace(it, "%guild%", guild.tag)
        }.also {
            sender.sendFixedMessage(*it.toTypedArray())
        }
        guild.alliesInvites.add(target.uniqueId)
    }

    @EventHandler
    fun onDeleteGuild(event: RemoveGuildEvent) {

    }

    @EventHandler
    fun onRemoveMemberOfGuild(event: RemoveMemberGuildEvent) {

    }


}