package au.sgallitz.pokedex.details.data

import au.sgallitz.pokedex.details.data.repository.RemotePokemonDetailsRepository
import au.sgallitz.pokedex.details.domain.repository.PokemonDetailsRepository
import org.koin.dsl.module

object DiDetailsData {
    val module = module {
        single<PokemonDetailsRepository> {
            RemotePokemonDetailsRepository(get())
        }
    }
}
