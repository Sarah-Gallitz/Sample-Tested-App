package au.sgallitz.pokedex.core.domain

sealed class ErrorReason {
    object NoNetwork : ErrorReason()
    data class GeneralError(val details: String? = null) : ErrorReason()
}
