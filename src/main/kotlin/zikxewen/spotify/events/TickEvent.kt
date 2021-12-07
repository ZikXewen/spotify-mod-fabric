package zikxewen.spotify.events

import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents
import net.minecraft.client.MinecraftClient
import zikxewen.spotify.keybinds.Keybinds
import zikxewen.spotify.utils.SpotifyControls

object TickEvent {
    private val mc: MinecraftClient = MinecraftClient.getInstance()
    fun load(){
        ClientTickEvents.END_CLIENT_TICK.register {
            if(SpotifyControls.cooldown > 0) {
                --SpotifyControls.cooldown
                return@register
            }
            if(mc.world == null) return@register
            if(SpotifyControls.accessToken.isEmpty()){
                if(Keybinds.loginKey.isPressed) SpotifyControls.login()
            } else {
                if(Keybinds.playPauseKey.isPressed) SpotifyControls.playPause()
                if(Keybinds.nextSongKey.isPressed) SpotifyControls.skip(true)
                if(Keybinds.prevSongKey.isPressed) SpotifyControls.skip(false)
            }
        }
    }
}