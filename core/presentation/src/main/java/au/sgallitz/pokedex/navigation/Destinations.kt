package au.sgallitz.pokedex.navigation

interface Destinations {
    fun getHomeDestination(): String

    fun getDetailsDestination(pokemonId: Int): String
}
