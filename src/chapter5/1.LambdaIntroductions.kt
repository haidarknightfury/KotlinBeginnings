package chapter5

data class Person(val name: String, val age: Int)

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
    val pplList = listOf(Person("haidar", 12), Person("baka", 15), Person("deku", 22), Person("kacchan", 33))
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

    val namesOfOver30 = pplList.filter(predicateGreaterThan30).map(Person::name);

    val maxAge = pplList.maxBy { it.age }?.age;
    val maxPeople = pplList.filter { it.age == maxAge }

    //115

}