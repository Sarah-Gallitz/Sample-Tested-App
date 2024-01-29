package au.sgallitz.pokedex.details.data.repository

import au.sgallitz.pokedex.details.data.api.Pokemon
import au.sgallitz.pokedex.details.domain.model.Images
import au.sgallitz.pokedex.details.domain.model.PokemonDetails
import java.net.URL

internal fun Pokemon.toDomain(): PokemonDetails {
    return PokemonDetails(
        pokemonId = this.id,
        name = this.name,
        images = Images(
            maleAnim = URL(this.sprites.versions.generationV.blackAndWhite.animated.front),
            maleAnimShiny = URL(this.sprites.versions.generationV.blackAndWhite.animated.frontShiny),
            femaleAnim = URL(
                this.sprites.versions.generationV.blackAndWhite.animated.frontFemale
                    ?: this.sprites.versions.generationV.blackAndWhite.animated.front
            ),
            femaleAnimShiny = URL(
                this.sprites.versions.generationV.blackAndWhite.animated.frontShinyFemale
                    ?: this.sprites.versions.generationV.blackAndWhite.animated.frontShiny
            ),
            maleAnimBack = URL(this.sprites.versions.generationV.blackAndWhite.animated.back),
            maleAnimShinyBack = URL(this.sprites.versions.generationV.blackAndWhite.animated.backShiny),
            femaleAnimBack = URL(
                this.sprites.versions.generationV.blackAndWhite.animated.backFemale
                    ?: this.sprites.versions.generationV.blackAndWhite.animated.back
            ),
            femaleAnimShinyBack = URL(
                this.sprites.versions.generationV.blackAndWhite.animated.backShinyFemale
                    ?: this.sprites.versions.generationV.blackAndWhite.animated.backShiny
            )
        )
    )
}
