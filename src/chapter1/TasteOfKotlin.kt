package chapter1

data class Person(val name: String, val age: Int? = null)

fun main(args: Array<String>) {
    val persons = listOf<Person>(Person("haidar", age = 25), Person("Alice"))
    val oldest = persons.maxBy { it.age ?:0}
    println("oldest person is $oldest")

    // destructuring objects
    val (name,age) = oldest!!;
    println("name and age of oldest person is $name $age")

}