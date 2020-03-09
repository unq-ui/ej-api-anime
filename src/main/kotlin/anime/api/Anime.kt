package anime.api

class Anime(
    var id: Int?            = null,
    var title: String?      = null,
    var image: String?      = null,
    var type: String?       = null,
    var episodes: Int?      = null,
    var members: Int?       = null,
    var score: Int?         = null,
    var startDate: String?  = null,
    var endDate: String?    = null,
    var urlDetails: String? = null
){

    fun updateWith(anime: Anime) {
        this.title      = anime.title
        this.image      = anime.image
        this.type       = anime.type
        this.episodes   = anime.episodes
        this.members    = anime.members
        this.score      = anime.score
        this.startDate  = anime.startDate
        this.endDate    = anime.endDate
        this.urlDetails = anime.urlDetails
    }
}
