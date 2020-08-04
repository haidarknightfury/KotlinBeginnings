package chapter4

import java.awt.event.MouseAdapter
import java.awt.event.MouseEvent
import java.io.File

//1. equals, hashCode, and toString

class Client(val name: String, val postalCode: Int) {

    override fun toString() = "Client(name=$name, postalCode=$postalCode)" //Overriding toString

    // equals - In java must call the equals
    // ==  in kotlin - automatically calls the equals
    override fun equals(other: Any?): Boolean {
        if (other == null || other !is Client) // is -> instanceOf
            return false
        return name == other.name && postalCode == other.postalCode
    }

    // The hashCode method should be always overridden together with equals
    override fun hashCode(): Int = name.hashCode() * 31 + postalCode
}

//2. data class - automatically generated toString, equals and hashCode
data class Customer(val name: String, val postalCode: Int) {

    // copy method also generated for every data class
    //  fun copy(name: String = this.name, postalCode: Int = this.postalCode) = Client(name, postalCode)
}


//3. delegation BY - delegating implementation to another class - Do not have to implement all the methods in an interface
class OLDDelegatingCollection<T> : Collection<T> { // has to override all the methods in the Collection Interface
    private val innerList = arrayListOf<T>()
    override val size: Int get() = innerList.size
    override fun isEmpty(): Boolean = innerList.isEmpty()
    override fun contains(element: T): Boolean = innerList.contains(element)
    override fun iterator(): Iterator<T> = innerList.iterator()
    override fun containsAll(elements: Collection<T>): Boolean =
        innerList.containsAll(elements)
}

class DelegatingCollection<T>(
    innerList: Collection<T> = ArrayList<T>() // delegate the methods to an ArrayList
) : Collection<T> by innerList {} // no need to override methods - override only what you want

class CountingSet<T>(val innerSet: MutableCollection<T> = HashSet<T>()) : MutableCollection<T> by innerSet {
    var objectsAdded = 0

    override fun add(element: T): Boolean {
        println("adding element $element to set")
        objectsAdded++
        return innerSet.add(element)
    }

    override fun addAll(c: Collection<T>): Boolean {
        objectsAdded += c.size
        return innerSet.addAll(c)
    }
}

//4. object keyword
// Object declaration is a way to define a singleton.
// Companion objects can contain factory methods and other methods that are  related to this class - don’t require a class instance to be called - members accessed via class name
// Object expression is used instead of Java’s anonymous inner class


// implemented as singleton - in java - private constructor and static instance method - defines class and variable in one declaration
// can inherit from class or interface
object Payroll {
    val allEmployees = arrayListOf<Person>()

    fun calculateSalary() {
        for (person in allEmployees) {

        }
    }
}

object CaseInsensitiveFileComparator : Comparator<File> { // comparators - are singleton - need only one per application
    override fun compare(file1: File, file2: File): Int {
        return file1.path.compareTo(file2.path, ignoreCase = true)
    }
}


data class Person(val name: String) {
    object NameComparator : Comparator<Person> { // object nested in class
        override fun compare(p1: Person, p2: Person): Int =
            p1.name.compareTo(p2.name)
    }
}


// 5. companion objects
// Classes in Kotlin can’t have static members; - use top-level functions
// used for factory methods - to create objects

class A {
    companion object {
        fun bar() {
            println("Companion object called")
        }
    }
}


class Employee private constructor(val nickname: String) {

    // companion object - cannot be overriden in subclass
    // It can be named, implement an interface, or have extension functions or properties.
    companion object {
        fun newSubscribingEmployee(email: String) =
            User(email.substringBefore('@')) // access to private constructor

        fun newFacebookEmployee(accountId: Int) =
            User(getFacebookName(accountId))// can access private method

        private fun getFacebookName(accountId: Int): String {
            return accountId.toString()
        }
    }
}

class Student(val name: String) {
    companion object Loader {
        fun fromJSON(jsonText: String): Student = Student(jsonText)
    }
}

// interface for companion objects
interface JSONFactory<T> {
    fun transform(text: String): T
}

class Animal(val name: String) {
    companion object : JSONFactory<Animal> { // companion object can implement interface

        override fun transform(text: String): Animal { //override transform method
            return Animal(text)
        }
    }
}

fun <T> loadFromJson(factory: JSONFactory<T>, txt: String): T {
    return factory.transform(txt)
}


// extensions on companion objects
// business logic module
data class Connection(val name: String) {
    companion object {
    }
}

fun Connection.Companion.createConnection(name: String = "default") {
    Connection(name)
}


// 6. Anonymous Inner class
class Window {
    lateinit var mouseAdapter: MouseAdapter;

    fun addMouseListener(mouseAdapter: MouseAdapter) {
        this.mouseAdapter = mouseAdapter;
    }
}

fun countClicks(window: Window) {
    var clickCount = 0 // local variable
    window.addMouseListener(object : MouseAdapter() {
        override fun mouseClicked(e: MouseEvent) {
            clickCount++ // can access and update local variable from inner anonymous class
        }
    })
// ...
}



fun main(args: Array<String>) {
    val bob = Customer("Haidar", 123)
    val mimi = bob.copy(name = "mimi") // using copy to copy a new object
    println("$mimi properties")

    val countingSet = CountingSet<Int>();
    countingSet.add(1)
    println(countingSet.objectsAdded)

    Payroll.allEmployees.add(Person("haidar"))

    println(CaseInsensitiveFileComparator.compare(File("/User"), File("/user")))
    val files = listOf(File("/Z"), File("/a"))
    println(files.sortedWith(CaseInsensitiveFileComparator)) //sortedWith function, which returns a list sorted according to the specified comparator.

    val persons = listOf(Person("Bob"), Person("Alice"))
    println(persons.sortedWith(Person.NameComparator))

    A.bar() // like static method but can access private members i.e private constructor

    Employee.newSubscribingEmployee("haidar@mail.com") // use companion object as factory method
    Employee.newFacebookEmployee(123)

    // Calling companion object function - can be done both ways
    Student.Loader.fromJSON("json")
    Student.fromJSON("baka")

    Animal.Companion.transform("OHayo")
    Animal.transform("Ohayo")
    loadFromJson(Animal, "hello") // passes the companioin object to the function loadFromJson

    val connection = Connection.createConnection()
    println("$connection created")


    val window = Window()
    window.mouseAdapter = object : MouseAdapter() { // anonymmous inner class - name is ommited
        override fun mouseClicked(e: MouseEvent) {
            // anonymous inner class
        }
    }

    val listener = object : MouseAdapter() { // overwrite method - using the object - listener is a singleton
    }
    window.mouseAdapter = listener;


}

