package chapter2

import org.w3c.dom.css.Rect
import java.util.*

// Basic function
fun max(a: Int, b: Int): Int {
    return  if (a>b) a else b
}

class Person(
    val name:String,
    val isMarried:Boolean
)

data class Rectangle(val width:Int, val height:Int){
  val isSquare:Boolean // custom Accessors
    get() {
        return height == width;
    }
}

fun createRandomRectangle():Rectangle{
    val random = Random()
    return Rectangle(random.nextInt(), random.nextInt())
}

fun main(args: Array<String>) {
    println(max(1,2))

    // String templates
    val name = if (args.size > 0) args[0] else "Haidar"
    println("Hello $name")
    println("Hello , ${if (args.isNotEmpty()) args[0] else "someone"}")

    // Properties
    val person = Person("haidar", false)
    println(person.name)

    // Custom Accessors
    val shape = Rectangle(20,20)
    println(shape.isSquare)
    val anotherShape = createRandomRectangle()
    println(anotherShape)



}