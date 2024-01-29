package au.sgallitz.pokedex.details.presentation.details

import androidx.compose.ui.text.capitalize
import androidx.compose.ui.text.intl.Locale
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import au.sgallitz.pokedex.core.domain.DomainException
import au.sgallitz.pokedex.details.domain.usecase.GetPokemonDetails
import au.sgallitz.pokedex.details.presentation.details.DetailsUiState.HasData.PokemonData
import au.sgallitz.pokedex.mvi.MviViewModel
import au.sgallitz.pokedex.personalisation.domain.usecase.GetPokemonColors
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch

class DetailsViewModel(
    private val getDetails: GetPokemonDetails,
    private val getPokemonColors: GetPokemonColors,
    savedStateHandle: SavedStateHandle
) : MviViewModel<DetailsUiState, DetailsUiEvent, DetailsNavigationRequest>() {
    private val _uiState = MutableStateFlow<DetailsUiState>(DetailsUiState.Loading)
    override val uiState: StateFlow<DetailsUiState> = _uiState

    private val pokemonId: Int = checkNotNull(savedStateHandle["pokemonId"])

    init {
        viewModelScope.launch {
            try {
                val details = getDetails.execute(pokemonId)
                val colors = getPokemonColors.execute(pokemonId)

                details.combine(colors) { a, b -> Pair(a, b) }
                    .collect {
                        println("XXXXXX\n\n" + it.second)
                        val detail = it.first
                        val colorSet = it.second
                        if (_uiState.value is DetailsUiState.HasData) {
                            _uiState.updateIf<DetailsUiState.HasData, DetailsUiState> {
                                this.copy(
                                    data = this.data.copy(
                                        id = detail.pokemonId,
                                        pokemonName = detail.name.capitalize(Locale.current),
                                        pokemonNumber = "#" + "%05d".format(detail.pokemonId),
                                        animations = detail.images,
                                        pokemonColors = colorSet
                                    )
                                )
                            }
                        } else {
                            _uiState.value = DetailsUiState.HasData(
                                PokemonData(
                                    id = detail.pokemonId,
                                    pokemonName = detail.name.capitalize(Locale.current),
                                    pokemonNumber = "#" + "%05d".format(detail.pokemonId),
                                    isShowingShiny = false,
                                    isShowingFemale = false,
                                    isShowingBack = false,
                                    animations = detail.images,
                                    pokemonColors = colorSet
                                )
                            )
                        }
                    }
            } catch (e: DomainException) {
                _uiState.value = DetailsUiState.HasError(e.errorReason)
            }
        }
    }

    override fun process(event: DetailsUiEvent) {
        when (event) {
            is DetailsUiEvent.BackPressed -> navigate(DetailsNavigationRequest.CloseDetails)
            is DetailsUiEvent.BottomOfListReached -> {}

            DetailsUiEvent.ShowFront -> {
                _uiState.updateIf<DetailsUiState.HasData, DetailsUiState> {
                    this.copy(data = this.data.copy(isShowingBack = false))
                }
            }

            DetailsUiEvent.ShowBack -> {
                _uiState.updateIf<DetailsUiState.HasData, DetailsUiState> {
                    this.copy(data = this.data.copy(isShowingBack = true))
                }
            }

            DetailsUiEvent.ShowMaleImage -> {
                _uiState.updateIf<DetailsUiState.HasData, DetailsUiState> {
                    this.copy(data = this.data.copy(isShowingFemale = false))
                }
            }

            DetailsUiEvent.ShowFemaleImage -> {
                _uiState.updateIf<DetailsUiState.HasData, DetailsUiState> {
                    this.copy(data = this.data.copy(isShowingFemale = true))
                }
            }

            DetailsUiEvent.ShowNormal -> {
                _uiState.updateIf<DetailsUiState.HasData, DetailsUiState> {
                    this.copy(data = this.data.copy(isShowingShiny = false))
                }
            }

            DetailsUiEvent.ShowShiny -> {
                _uiState.updateIf<DetailsUiState.HasData, DetailsUiState> {
                    this.copy(
                        data = this.data.copy(
                            isShowingShiny = true
                        )
                    )
                }
            }
        }
    }
}


inline fun <reified T, V> MutableStateFlow<V>.updateIf(
    update: T.() -> T
) where T : V {
    val current = this.value
    if (current is T) {
        this.value = current.update()
    }
}