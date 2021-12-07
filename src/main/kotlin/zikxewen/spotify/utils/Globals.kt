package zikxewen.spotify.utils

import kotlinx.serialization.json.Json
import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Logger

object Globals {
    const val ID = "spotify"
    const val ENDPOINT = "https://spotify-mod-endpoint.vercel.app/api/"
    val logger: Logger = LogManager.getLogger(ID)
    val jsonFormat = Json {ignoreUnknownKeys = true; prettyPrint = true}
}