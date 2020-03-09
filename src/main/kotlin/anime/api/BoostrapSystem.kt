package anime.api

import java.io.File
import com.fasterxml.jackson.module.kotlin.readValue
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper

object BootstrapSystem {
    fun create(): AnimeSystem {
        var animes = mutableListOf<Anime>()
        try {
            val json = File("src/main/resources/animeList.json")
            animes = jacksonObjectMapper().readValue(json)
        } catch(e: Exception) {
            println(e)
        }
        return AnimeSystem(animes)
    }
}
