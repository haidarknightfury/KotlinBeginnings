package chapter2

import java.util.*

fun fizzBuzz(i: Int) = when {
    i % 15 == 0 -> "FizzBuzz "
    i % 3 == 0 -> "Fizz "
    i % 5 == 0 -> "Buzz "
    else -> "$i "
}

fun isLetter(c:Char)= c in 'a'..'z' || c in 'A'..'Z'
fun isNotDigit(c: Char) = c !in '1'..'9'

fun recognize(c: Char) = when (c) {
    in '0'..'9' -> "It's a digit!"
    in 'a'..'z', in 'A'..'Z' -> "It's a letter!"
    else -> "I don't know…​"
}


fun main(args: Array<String>) {
    // Basic for loops
    for (i in 1..100){
        print(fizzBuzz(i))
    }

    // loop backwards
    for (i in 100 downTo 1 step 2) {
        print(fizzBuzz(i))
    }

    // Iterating over maps
    var binaryReps = TreeMap<Char,String>()
    for (c in 'A'..'F'){
        binaryReps[c] = Integer.toBinaryString(c.toInt())
    }
    for((letter,binary) in binaryReps){
        println("$letter -> $binary")
    }

    // Range checks
    println(isLetter('q'))
    println(isNotDigit('x'))
    println(recognize('8'))
}