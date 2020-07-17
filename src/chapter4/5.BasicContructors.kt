package chapter4

// default values can be used
open class User(
    val nickname: String,
    val isSubscribed: Boolean = true
) // “val” means the corresponding property is generated for the constructor parameter.

class SimilarUser constructor(_nickname: String) { // primary constructor with 1 parameter - no val
    val nickname: String

    init { // initialiser block - initialisation code
        nickname = _nickname
    }
}

// If all the constructor parameters have default values, the compiler generates  an additional constructor without parameters
class TwitterUser(nickname: String) :
    User(nickname) { // extends User - must pass the nickname - in java super is called
}


// default constructors
open class BasicButton {}// default constructor created - no paramters
class RadioButton : BasicButton() {}// extending a class - always use () - invokes the constructoro
class Secretive private constructor() {}// private constructor


open class Human {
    val name: String
    //val attribute: String

    constructor(name: String) {
        this.name = name;
    }
}


fun main(args: Array<String>) {
    val user = User("user", isSubscribed = false) // specify parameters
    println(user);

    // val secretive = Secretive() - does not work
}