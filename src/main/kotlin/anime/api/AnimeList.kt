package anime.api

class AnimeList(system: AnimeSystem, val currentPage: Int) {

    val data: List<Anime>
    val amountOfPages: Int

    init {
        this.data = system.getPage(currentPage)
        this.amountOfPages = system.amountOfPages()
    }


}