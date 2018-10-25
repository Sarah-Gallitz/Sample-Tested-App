package sampletestedapp.sarahgallitz.com.sampletestedapp

import org.mockito.invocation.InvocationOnMock

inline fun <reified T : Any> InvocationOnMock.argThatIs(): T {
    val args = this.arguments.mapNotNull { it as? T }
    when (args.size) {
        0 -> throw Exception("No arg of that type found")
        1 -> return args.first()
        else -> throw Exception("Cannot use argThatIs when multiple parameters match, use argsThatAreOfType instead")
    }
}

inline fun <reified T : Any> InvocationOnMock.argsThatAreOfType(): List<T> {
    return this.arguments.mapNotNull { it as? T }
}
