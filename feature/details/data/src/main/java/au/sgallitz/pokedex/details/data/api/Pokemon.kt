package au.sgallitz.pokedex.details.data.api

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Pokemon(
    val id: Int,
    val name: String,
    val sprites: PokemonSprites
)

@Serializable
data class PokemonSprites(
    val versions: PokemonSpriteVersions
)

@Serializable
data class PokemonSpriteVersions(
    @SerialName("generation-v")
    val generationV: SpritesGenV
)

@Serializable
data class SpritesGenV(
    @SerialName("black-white")
    val blackAndWhite: SpritesBlackAndWhite
)

@Serializable
data class SpritesBlackAndWhite(
    val animated: AnimatedSprites
)

@Serializable
data class AnimatedSprites(
    @SerialName("back_default")
    val back: String?,
    @SerialName("back_shiny")
    val backShiny: String?,

    @SerialName("back_female")
    val backFemale: String?,
    @SerialName("back_shiny_female")
    val backShinyFemale: String?,

    @SerialName("front_default")
    val front: String?,
    @SerialName("front_shiny")
    val frontShiny: String?,

    @SerialName("front_female")
    val frontFemale: String?,
    @SerialName("front_shiny_female")
    val frontShinyFemale: String?
)