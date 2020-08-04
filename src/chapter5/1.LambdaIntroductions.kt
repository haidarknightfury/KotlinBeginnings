package chapter5

data class Person(val name: String, val age: Int)
class Book(val title: String, val authors: List<String>)

fun findTheOldest(people: List<Person>) { // finding oldest old way
    var maxAge = 0
    var theOldest: Person? = null
    for (person in people) {
        if (person.age > maxAge) {
            maxAge = person.age
            theOldest = person
        }
    }
    println(theOldest)
}

fun Person.isAdult() = this.age >= 18;

fun main(args: Array<String>) {
    val pplList = listOf(Person("haidar", 12), Person("baka", 12), Person("deku", 22), Person("kacchan", 33))
    val oldest = pplList.maxBy { it.age }
    val oldestsame = pplList.maxBy(Person::age)

    val sum = { x: Int, y: Int -> x + y }
    val total = sum(1, 2);
    println("total is $total");

    { println(42) }() // call lambda function directly
    run { println(42) } // same way but more elegant

    val joined = pplList.joinToString(separator = ",", transform = { p: Person -> p.name })
    println(joined);


    val createPerson = ::Person; // bind to constructor
    val person = createPerson("haidar", 22)

    val predicate = Person::isAdult
    val isAdult = predicate(person);
    println("$person $isAdult")


    // collection lambdas - filter
    val list = listOf(1, 2, 3, 4)
    println(list.filter { it % 2 == 0 })

    val predicateGreaterThan30 = { p: Person -> p.age >= 30 }
    val peoples = pplList.filter(predicateGreaterThan30)


    // collections - map
    val squared = list.map { it * it }
    val names = peoples.map(Person::name)

    // chaining lambda
    val namesOfOver30 = pplList.filter(predicateGreaterThan30).map(Person::name);

    val maxAge = pplList.maxBy { it.age }?.age;
    val maxPeople = pplList.filter { it.age == maxAge }

    //“all”, “any”, “count”, and “find”
    val canBeInClub27 = { p: Person -> p.age >= 27 }
    val allabove27 = pplList.all(canBeInClub27)
    println(allabove27)

    val anyAbove27 = pplList.any { p -> p.age >= 27 }
    println(anyAbove27)

    println("there are ${pplList.count { p -> p.age >= 27 }} greater than 27");

    // group by
    val pplMap = pplList.groupBy { it.age }
    println(pplMap);

    val lst = listOf("a", "aab", "b", "bc")
    println(lst.groupBy(String::first)) // using reference


    // flatMap and flatten
    val books = listOf(Book("title", listOf("x","y")), Book("title2", listOf("xx","y")))
    val auths = books.flatMap { it.authors }.toSet();
    println("authors are $auths")

    println("abc".toList()) // converts to a list [a,b,c]
    val strings = listOf("abcd", "defg")
    val listofindividualitems = strings.flatMap { it.toList()}.toSet()
    println(listofindividualitems)

    val flattened = books.map { it.authors }.flatten();
    println("flattened items is $flattened")

    //  Sequences - avoid creation of temporary list
    pplList.map(Person::name).filter { it.startsWith("b",ignoreCase = true) };

    //  use a sequence whenever you have a chain of operations on a large collection
    pplList.asSequence() // converts to sequence
            .map(Person::name)
            .filter { it.startsWith("b", ignoreCase = true) }
            .toList() // converts back to list

    // lazy run on 1 by 1
    // eager applies on the whole collection
    println(listOf(1, 2, 3, 4).asSequence().map { it * it }.find { it > 3 })

    listOf(1, 2, 3, 4).asSequence()
    .map { print("map($it) "); it * it }
    .filter { print("filter($it) "); it % 2 == 0 }

    // sequence on objects
    val ls = pplList.asSequence().map(Person::name).filter { it.length <=4 }.toList();
    println(ls)



}