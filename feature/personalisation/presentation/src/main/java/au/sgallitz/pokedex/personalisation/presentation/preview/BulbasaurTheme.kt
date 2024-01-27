package au.sgallitz.pokedex.personalisation.presentation.preview

import au.sgallitz.pokedex.personalisation.domain.model.PokemonColors

fun getBulbasaurColors(isDarkTheme: Boolean) = if (isDarkTheme) {
    PokemonColors(
        isDarkTheme = true,

        primary = "#ff9bd9a6",
        onPrimary = "#ff003918",
        primaryContainer = "#ff73af7f",
        onPrimaryContainer = "#ff001b08",
        inversePrimary = "#ff2f6a40",
        secondary = "#ffb3cdb5",
        onSecondary = "#ff203524",
        secondaryContainer = "#ff2e4432",
        onSecondaryContainer = "#ffc0dac1",
        tertiary = "#ff9bd1ff",
        onTertiary = "#ff003350",
        tertiaryContainer = "#ff71a8d5",
        onTertiaryContainer = "#ff00192a",
        background = "#ff111411",
        onBackground = "#ffe1e3dd",
        surface = "#ff111411",
        onSurface = "#ffe1e3dd",
        surfaceVariant = "#ff414941",
        onSurfaceVariant = "#ffc0c9be",
        surfaceTint = "#ff373a36",
        inverseSurface = "#ffe1e3dd",
        inverseOnSurface = "#ff2e312d",
        error = "#ffffb4ab",
        onError = "#ff690005",
        errorContainer = "#ff93000a",
        onErrorContainer = "#ffffdad6",
        outline = "#ff8a9389",
        outlineVariant = "#ff414941",
        scrim = "#52000000"
    )
} else {
    PokemonColors(
        isDarkTheme = false,

        primary = "#ff2f6a40",
        onPrimary = "#ffffffff",
        primaryContainer = "#ff84c290",
        onPrimaryContainer = "#ff003013",
        inversePrimary = "#ff97d5a2",
        secondary = "#ff4d6450",
        onSecondary = "#ffffffff",
        secondaryContainer = "#ffd1ebd1",
        onSecondaryContainer = "#ff384e3b",
        tertiary = "#ff26638c",
        onTertiary = "#ffffffff",
        tertiaryContainer = "#ff83bae7",
        onTertiaryContainer = "#ff002b44",
        background = "#fff8faf4",
        onBackground = "#ff191c19",
        surface = "#fff8faf4",
        onSurface = "#ff191c19",
        surfaceVariant = "#ffdce5da",
        onSurfaceVariant = "#ff414941",
        surfaceTint = "#fff8faf4",
        inverseSurface = "#ff2e312d",
        inverseOnSurface = "#ffeff2eb",
        error = "#ffba1a1a",
        onError = "#ffffffff",
        errorContainer = "#ffffdad6",
        onErrorContainer = "#ff410002",
        outline = "#ff717970",
        outlineVariant = "#ffc0c9be",
        scrim = "#52000000"
    )
}
