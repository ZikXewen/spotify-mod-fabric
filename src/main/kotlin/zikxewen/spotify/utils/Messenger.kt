package zikxewen.spotify.utils


import net.minecraft.client.MinecraftClient
import net.minecraft.text.TranslatableText

object Messenger {
    private val player = MinecraftClient.getInstance().player
    fun send(message: String) {
        player!!.sendMessage(TranslatableText("message." + Globals.ID + "." + message), true)
    }
}