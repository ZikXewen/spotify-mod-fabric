package zikxewen.spotify.utils

import net.minecraft.util.Util
import org.apache.http.client.utils.URIBuilder
import org.apache.logging.log4j.Level

object SpotifyControls {
    var accessToken = ""
    var cooldown = 0
    fun login() = Async.async {
        cooldown += 100
        val clientId = Request.get(Globals.ENDPOINT + "clientId", String::class)
        Globals.logger.log(Level.INFO, "Got Client ID")
        if (clientId.isNullOrEmpty()) {
            Globals.logger.log(Level.WARN, "Failed to get Client ID")
            return@async
        }
        try {
            Util.getOperatingSystem().open(
                URIBuilder("https://accounts.spotify.com/authorize")
                .addParameter("client_id", clientId)
                .addParameter("response_type", "code")
                .addParameter("redirect_uri", "http://localhost:5000/callback")
                .addParameter("scope", "user-read-playback-state user-read-currently-playing user-modify-playback-state")
                .build())
        } catch (err: Exception) {
            Globals.logger.log(Level.WARN, "Failed to open browser.")
            Globals.logger.log(Level.WARN, err.message)
        }
    }
    private fun getPlaybackStatus(): String {
        val playbackStatus = Request.get("https://api.spotify.com/v1/me/player/currently-playing", PlaybackStatusClass::class, listOf(Pair("Authorization", "Bearer $accessToken")))
            ?: return "error"
        return if (playbackStatus.is_playing) "pause" else "play"
    }
    fun playPause() = Async.async {
        cooldown += 100
        val playbackStatus = getPlaybackStatus()
        if(playbackStatus == "error") return@async Messenger.send("error")
        val statusCode = Request.req("put", "https://api.spotify.com/v1/me/player/$playbackStatus", listOf(Pair("Authorization", "Bearer $accessToken")))
        if(statusCode == 204) Messenger.send(playbackStatus)
        else Messenger.send("error")
    }
    fun skip(isNext: Boolean) = Async.async {
        cooldown += 100
        val isNextString = if(isNext) "next" else "previous"
        val statusCode = Request.req("post","https://api.spotify.com/v1/me/player/$isNextString", listOf(Pair("Authorization", "Bearer $accessToken")))
        if(statusCode == 204) Messenger.send(isNextString)
        else Messenger.send("error")
    }
}