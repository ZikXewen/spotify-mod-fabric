package zikxewen.spotify

import net.fabricmc.api.ClientModInitializer
import zikxewen.spotify.auth.AuthServer
import zikxewen.spotify.config.Config
import zikxewen.spotify.events.TickEvent
import zikxewen.spotify.keybinds.Keybinds

object SpotifyMod: ClientModInitializer {
    override fun onInitializeClient() {
        Config.load()
        AuthServer.load()
        Keybinds.load()
        TickEvent.load()
    }
}