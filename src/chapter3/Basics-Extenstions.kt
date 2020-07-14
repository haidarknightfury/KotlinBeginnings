package chapter3

// Basic extension of String
fun String.lastChar()= this.get(this.length-1)


// Extension based on a Collection of type T
fun <T> Collection<T>.joinToString(separator: String = ", ", prefix: String = "", postfix: String = "" ): String {
    val result = StringBuilder(prefix)

    for ((index, element) in this.withIndex()) {
        if (index > 0) result.append(separator)
        result.append(element)
    }
    result.append(postfix)
    return result.toString()
}

// Call another extension in the new extensions
fun Collection<String>.join(separator: String = ", ",  prefix: String = "", postfix: String = "" )
        = joinToString(separator, prefix, postfix)

// Extensions for Base and Inherited class
open class View {
    open fun click() = println("View clicked")
}

class Button: View() {
    override fun click() = println("Button clicked")
}
fun View.showOff() = println("I'm a view!")
fun Button.showOff() = println("I'm a button!")


// Entensions properties
val String.lastChar:Char
    get() {
       return this.get(this.length - 1)
    }

var StringBuilder.lastChar:Char
    get() = this.get(this.length - 1)
    set(value:Char) = this.setCharAt(this.length-1,value)


fun main(args: Array<String>) {
    println("abcd".lastChar())
    println(listOf(1,2,3,4).joinToString())
    println(listOf("one","two").join())

    val view:View = Button()
    view.click() // Button clicked - implementation is button
    view.showOff() // No override happens over extensions - it takes what it has been declared with

    // Extensions properties
    println("Kotlin".lastChar)
    val sb = StringBuilder("Kotlin?")
    sb.lastChar = '!' // calls the setters behind the scene
    println(sb)
}