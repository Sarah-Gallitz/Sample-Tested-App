package au.sgallitz.pokedex.core.domain

sealed class ErrorReason {
    data object NoNetwork : ErrorReason()
    data class GeneralError(val details: DetailedError? = null) : ErrorReason()
}
