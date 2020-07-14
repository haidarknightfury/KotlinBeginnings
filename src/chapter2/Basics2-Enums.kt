package chapter2
import chapter2.Color.*;

enum class Car{
    Toyota, Mercedes
}

// Enum properties
enum class Color(val r:Int, val g:Int, val b:Int){
    RED(255, 0, 0), ORANGE(255, 165, 0),
    YELLOW(255, 255, 0), GREEN(0, 255, 0), BLUE(0, 0, 255),
    INDIGO(75, 0, 130), VIOLET(238, 130, 238);

    fun rgb() = (r * 256 + g) * 256 + b // Method on enum
}

fun getMnemonic(color:Color) = when (color){ // Use of when with enums
    Color.RED -> "Richard"
    Color.ORANGE -> "Of"
    Color.YELLOW -> "York"
    Color.GREEN -> "Gave"
    Color.BLUE -> "Battle"
    Color.INDIGO -> "In"
    Color.VIOLET -> "Vain"
}

fun getWarmth(color: Color) = when(color){
    Color.RED,Color.ORANGE, Color.YELLOW -> "warm"
    Color.GREEN -> "neutral"
    Color.BLUE, Color.INDIGO, Color.VIOLET -> "cold"
}

fun getWarmth2(color: Color) = when(color){ // import chapter2.Color.*; - get Enum values directly - similar to import static
    RED,ORANGE, YELLOW -> "warm"
    GREEN -> "neutral"
    BLUE, INDIGO, VIOLET -> "cold"
}

fun mix(c1: Color, c2: Color) = // function with one statement, no need for braces and return value
    when (setOf(c1, c2)) { // With arbitrary objects - Combination of Enum values
        setOf(RED, YELLOW) -> ORANGE
        setOf(YELLOW, BLUE) -> GREEN
        setOf(BLUE, VIOLET) -> INDIGO
        else -> throw Exception("Dirty color")
    }

fun mixOptimized(c1: Color, c2: Color) = // when without arguments
    when {
        (c1 == RED && c2 == YELLOW) || (c1 == YELLOW && c2 == RED) ->  ORANGE // each evaluation must be a boolean expression
        (c1 == YELLOW && c2 == BLUE) || (c1 == BLUE && c2 == YELLOW) -> GREEN
        (c1 == BLUE && c2 == VIOLET) || (c1 == VIOLET && c2 == BLUE) -> INDIGO
        else -> throw Exception("Dirty color")
    }

fun main(args: Array<String>) {
    println(Color.RED.rgb())
    println(getMnemonic(Color.RED)) // Use of when
    println(getWarmth(Color.BLUE)) // when with several values

}