package zikxewen.spotify.keybinds

import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper
import net.minecraft.client.option.KeyBinding
import net.minecraft.client.util.InputUtil
import zikxewen.spotify.utils.Globals

object Keybinds {
    val loginKey = keyBuilder("login", InputUtil.GLFW_KEY_G)
    val nextSongKey = keyBuilder("next", InputUtil.GLFW_KEY_APOSTROPHE)
    val prevSongKey = keyBuilder("prev", InputUtil.GLFW_KEY_SEMICOLON)
    val playPauseKey = keyBuilder("play", InputUtil.GLFW_KEY_P)
    fun load(){
        KeyBindingHelper.registerKeyBinding(loginKey)
        KeyBindingHelper.registerKeyBinding(nextSongKey)
        KeyBindingHelper.registerKeyBinding(prevSongKey)
        KeyBindingHelper.registerKeyBinding(playPauseKey)
    }
    private fun keyBuilder(name: String, key: Int) = KeyBinding("key." + Globals.ID + "." + name, key, "key.category." + Globals.ID)
}