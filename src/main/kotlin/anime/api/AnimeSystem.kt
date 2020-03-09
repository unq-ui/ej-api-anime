package anime.api


class AnimeSystem(private val animes: MutableList<Anime>) {
    private var defaultAmountForPage = 30

    fun getPage(numberPage: Int): MutableList<Anime> {
        val start = (numberPage -1) * defaultAmountForPage
        val end = start + defaultAmountForPage
        return animes.subList(start, end)
    }

    fun amountOfPages() = animes.size / defaultAmountForPage

    fun addAnime(anime: Anime) {
        val lastAnime = animes.lastOrNull()
        if(lastAnime === null) {
            anime.id = 1
        } else {
            anime.id = (lastAnime.id ?: 0) + 1
        }
        animes.add(anime)
    }

    fun removeAnime(id: Int) = animes.remove(animes.firstOrNull { it.id == id })

    fun editAnime(id: Int, newAnime: Anime) {
        val oldAnime = animes.firstOrNull { it.id == id }
        if(oldAnime === null) {
            throw RuntimeException("Anime no existente")
        }
        oldAnime.updateWith(newAnime)
    }

    fun search(text: String) = animes.filter { it.title!!.contains(text) }
    fun getAnime(id: Int) = animes.firstOrNull { it.id == id }
}
