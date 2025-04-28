package me.dwidar.aflamy.shell.local_storage_manager.models

import io.realm.kotlin.types.RealmObject

class RealmMovieModel : RealmObject {
    var adult: Boolean =  false
    var backdropPath: String = ""
    var id: Int = -1
    var originalLanguage: String = ""
    var originalTitle: String = ""
    var overview: String = ""
    var popularity: Double = 0.0
    var posterPath: String? = null
    var releaseDate: String = "2025-03-26"
    var title: String = ""
    var video: Boolean = false
    var voteAverage: Double = 0.0
    var voteCount: Int = 0
}