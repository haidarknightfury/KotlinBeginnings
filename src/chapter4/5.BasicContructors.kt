package chapter4

//1. default values can be used
open class User(
    val nickname: String,
    val isSubscribed: Boolean = true
) // “val” means the corresponding property is generated for the constructor parameter - no need to specify again


//2. Primary constructor
class SimilarUser constructor(_nickname: String) { // primary constructor with 1 parameter - no val - must specify val property in body
    val nickname: String

    init { // initialiser block - initialisation code
        nickname = _nickname
    }
}

//3. Extending a class
// If all the constructor parameters have default values, the compiler generates  an additional constructor without parameters
class TwitterUser(nickname: String) :
    User(nickname) { // extends User - must pass the nickname - in java super is called
}

//4. Extending class with no parameters
// default constructors
open class BasicButton {}// default constructor created - no paramters
class RadioButton : BasicButton() {}// extending a class - always use () - invokes the constructoro
class Secretive private constructor() {}// private constructor


//5. Class with 2 constructors
open class Human {
    val name: String
    val attribute: String

    constructor(name: String) { // constructor 1
        this.name = name;
        this.attribute = "";
    }

    constructor(name: String, attribute: String) { //constructor 2
        this.name = name;
        this.attribute = attribute;
    }
}


// 6. Specifying property in interface
interface IUser {
    val nickname: String // abstract property declarations
}

// override in default constructor - override the nickname
class PrivateUser(override val nickname: String) : IUser {}


// 7. Custom geter for overriding property
class SubscribingUser(val email: String) : IUser {
    override val nickname: String
        get() = email.substringBefore("@") // custom getter
}

class FBUser(val accountId: Int) : IUser {
    override val nickname: String
        get() = getFBUser(accountId)
}

fun getFBUser(accountId: Int): String {
    return "";
}


// 8. specifying setter

class Utilisateur(val name: String) {
    var address: String = "unspecified"
        set(value: String) {
            println(""" Address was changed for $name: "$field" -> "$value".""".trimIndent())
            field = value
        }
}

// 9. changing accessor visibility
class LengthCounter {

    var counter: Int = 0 // cannot set counter outside class
        private set

    fun addWord(word: String) {
        counter += word.length
    }
}


fun main(args: Array<String>) {
    val user = User("user", isSubscribed = false) // specify parameters
    println(user);

    // val secretive = Secretive() - does not work

    val utilisateur = Utilisateur("haidar")
    utilisateur.address = "rue couvent";

    val lengthCounter = LengthCounter()
    // lengthCounter.counter = "10" - can't assign because setter is private
    lengthCounter.addWord("abc")

    // lateinit modifier on a non-null property specifies that this property is
    //initialized later, after the constructor is called, which is a common case for
    //some frameworks.

    // Lazy initialized properties, as part of the more general delegated properties
    //feature

    // @JvmField annotation on a property exposes a public field without accessors.

    // const modifier makes working with annotations more convenient and lets you use a property of a primitive type or String as an annotation argument.
}