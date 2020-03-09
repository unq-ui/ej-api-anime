package anime.api

import io.javalin.Javalin
import io.javalin.apibuilder.ApiBuilder.*
import io.javalin.core.util.RouteOverviewPlugin

fun main() { AnimeApi(7000).init() }


class AnimeApi(private val port: Int) {
    fun init(): Javalin {
        val app = Javalin.create {
            it.defaultContentType = "application/json"
            it.registerPlugin(RouteOverviewPlugin("/routes"))
            it.enableCorsForAllOrigins()
        }
        app.start(port)

        val animeController = AnimeController(BootstrapSystem.create())

        app.routes {
            path("animes") {
                get(animeController::getAllPaginated)
                put(animeController::create)
                post(animeController::update)
                delete(animeController::delete)
                path("search/:text") {
                    get(animeController::getByText)
                }
                path(":id") {
                    get(animeController::getById)
                }
            }
        }

        return app
    }
}