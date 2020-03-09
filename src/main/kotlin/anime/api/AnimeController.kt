package anime.api

import io.javalin.http.*

class AnimeController(val system: AnimeSystem) {
    // extension JSONUtils = new JSONUtils

    /**
     * Permite obtener 30 animes de la pagina {page}
     */
    fun getAllPaginated(ctx: Context) {
        val page = ctx.queryParam("page") ?: "1"
        ctx.status(200).json(AnimeList(system, page.toInt()))
    }

    /**
     * Permite agregar un nuevo anime
     */
    fun create(ctx: Context) {
        val anime = ctx.body<Anime>()
        system.addAnime(anime)
        ctx.status(200).json(anime)
    }

    /**
     * Permite modificar un anime
     */
    fun update(ctx: Context) {
        val anime = ctx.bodyValidator<Anime>()
            .check({ it.id !== null })
            .check({ system.getAnime(it.id!!) !== null })
            .get()
        system.editAnime(anime.id!!, anime)
        ctx.status(200).json(system.getAnime(anime.id!!)!!)
    }

    /**
     * Permite eliminar un anime por su id.
     */
    fun delete(ctx: Context) {
        val id = ctx.pathParam("id").toInt()
        val ok = system.removeAnime(id)
        if (!ok) {
            throw NotFoundResponse("No existe el anime con identificador ${id}")
        }
        ctx.status(204)

    }

    /**
     * Permite obtener un anime por su id.
     */
    fun getById(ctx: Context) {
        val id = ctx.pathParam("id").toInt()
        val anime = system.getAnime(id) ?: throw NotFoundResponse("No existe el anime con identificador ${id}")
        ctx.status(200).json(anime)
    }

    fun getByText(ctx: Context) {
        val text = ctx.pathParam("text")
        ctx.status(200).json(system.search(text))
    }
}
