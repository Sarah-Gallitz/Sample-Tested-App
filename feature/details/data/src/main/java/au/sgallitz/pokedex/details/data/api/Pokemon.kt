package au.sgallitz.pokedex.details.data.api

import kotlinx.serialization.Serializable

@Serializable
data class Pokemon(
    val id: Int,
    val name: String
)
