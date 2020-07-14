package chapter3

val set = hashSetOf<Int>(1,2,3,4)
val list = arrayListOf<Int>(1,2,3,4,5,5)
val map = hashMapOf<Int,String>( 1 to "One", 2 to "Two")


fun <T> joinToString(collection: Collection<T>,  separator: String,  prefix: String,  postfix: String ): String {
    val result = StringBuilder(prefix)
    for ((index, element) in collection.withIndex()) { // get the index as well in pairs (Index,Value)
        if (index > 0) result.append(separator)
        result.append(element)
    }
    result.append(postfix)
    return result.toString()
}

fun main(args: Array<String>) {
    println(set.javaClass) // similar to java.getClass()

    val strings = listOf("first", "second", "fourteenth") // create immutable list
    println(strings.last()) //more methods available

    val numbers = setOf<Int>(1,14,2)
    println(numbers.max()) // extensions available only to certain types of arrays

    val list = listOf(1, 2, 3)
    println(joinToString(list, "; ", "(", ")"));

    for ((index,value) in list.withIndex()){
        println("$index -> $value")
    }

}