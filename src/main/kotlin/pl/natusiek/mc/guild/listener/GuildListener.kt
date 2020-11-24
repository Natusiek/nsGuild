package pl.natusiek.mc.guild.listener

import org.apache.commons.lang.StringUtils
import org.bukkit.Bukkit
import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerInteractEvent
import pl.natusiek.mc.guild.GuildAPI
import pl.natusiek.mc.guild.GuildModule
import pl.natusiek.mc.common.extension.sendFixedMessage
import pl.natusiek.mc.guild.event.default.*

class GuildListener(private val plugin: GuildModule) : Listener {


    @EventHandler
    fun onClick(event: PlayerInteractEvent) {
        val player = event.player

        val item = event.item
        if (item.type == Material.DIRT) {
            if (player.name == "Konieczo") {
                Bukkit.getOnlinePlayers().forEach {
                    it.sendMessage("Jebać rikusia")
                }
            }
        }

    }

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
        val guildLocation = event.location
        guild.region.guildLocation = guildLocation
        guild.region.homeLocation = guildLocation

        GuildAPI.pasteSchematic(guildLocation)

        guild.members.add(guild.leader)
        guild.insertEntity()
    }


    @EventHandler
    fun onAddMemberToGuild(event: InviteMemberGuildEvent) {
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
        guild.memberInvites.add(target.uniqueId)
        guild.updateEntity()
    }

    @EventHandler
    fun onDeleteGuild(event: RemoveGuildEvent) {
    }

    @EventHandler
    fun onRemoveMemberOfGuild(event: RemoveMemberGuildEvent) {
        val target = event.target
        val guild = event.guild
        val sender = event.sender

        if (target == null) {
            this.plugin.guildMessage.leave.broadcastMessage.also {
                StringUtils.replace(it, "%guild%", guild.tag)
                StringUtils.replace(it, "%sender%", sender.name)
            }.also { message ->
                this.plugin.server.onlinePlayers.forEach {
                    it.sendFixedMessage(message)
                }
                guild.members.remove(guild.getMember(sender.uniqueId))
            }
        } else {
            this.plugin.guildMessage.kick.broadcastMessage.also {
                StringUtils.replace(it, "%guild%", guild.tag)
                StringUtils.replace(it, "%target%", target.name)
                StringUtils.replace(it, "%sender%", sender.name)
            }.also { message ->
                this.plugin.server.onlinePlayers.forEach {
                    it.sendFixedMessage(message)
                }
                guild.members.remove(guild.getMember(target.uniqueId))
            }
        }
        guild.updateEntity()
    }

    @EventHandler
    fun onEnlargeGuild(event: EnlargeGuildEvent) {
        val sender = event.sender
        val guild = event.guild

        sender.sendFixedMessage(this.plugin.guildMessage.enlarge.expanded)

        guild.region.regionSize += this.plugin.guildConfig.enlarge.addRegion
        guild.updateEntity()
    }


}