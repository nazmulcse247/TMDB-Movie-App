package com.iamnazmul.tmdbmovie.common.constant

object AppConstant {

    const val API_KEY =
        "eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiJmNDk0NGYzYWU2Y2VjMjQ0ZjdlYTA4MmM0Y2QxM2ZiYSIsIm5iZiI6MTY3NzM4NDA2OC4xMDgsInN1YiI6IjYzZmFkOTg0ODRmMjQ5MDA4NjE0ZjM5MiIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.snPP-bqEG8PY8sJi8e4-UeBeRZybIy5RPaPRqb9UAiI"

    const val BASE_URL = "https://api.themoviedb.org/3/"
    private const val BASE_URL_IMAGE = "https://image.tmdb.org/t/p/original"
    private const val BASE_URL_IMAGE_YOUTUBE = "https://img.youtube.com/vi/"
    private const val SIZE_IMG_YOUTUBE = "/hq720.jpg"
    const val NETWORK_PAGE_SIZE = 10
    const val STARTING_PAGE = 1


    object Preferences {
        const val LANGUAGE_CODE = "languageCode"
        const val LANGUAGE_NAME = "languageName"
        const val SHARED_PREF_NAME = "mova_shared_pref"
        const val DARK_MODE = "darkMode"
    }

    fun getPosterPath(posterPath: String?): String {
        return BASE_URL_IMAGE + posterPath
    }

    fun getBackDropPath(backDropPath: String?): String {
        return BASE_URL_IMAGE + backDropPath
    }

    fun getYouTubePath(youTubePath: String?): String {
        return BASE_URL_IMAGE_YOUTUBE + youTubePath + SIZE_IMG_YOUTUBE
    }

    fun getFlagPath(iso6391: String): String {
        return "https://www.unknown.nu/flags/images/$iso6391-100"
    }
}