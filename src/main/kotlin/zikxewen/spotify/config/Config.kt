package zikxewen.spotify.config

import de.maxhenkel.configbuilder.ConfigBuilder
import de.maxhenkel.configbuilder.ConfigEntry
import net.fabricmc.loader.api.FabricLoader
import kotlin.io.path.Path
import kotlin.io.path.pathString

object Config {
    lateinit var refreshToken: ConfigEntry<String>
    fun load() {
        ConfigBuilder.build(Path(FabricLoader.getInstance().configDir.pathString + "/spotify-client.properties")) {
            refreshToken = it.stringEntry("refresh_token", "")
        }
    }
}