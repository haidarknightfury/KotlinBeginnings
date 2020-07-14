package chapter3

class User(val id: Int, val name: String, val address: String)

fun saveUser(user: User) {
    if (user.name.isEmpty()) {
        throw IllegalArgumentException(
            "Can't save user ${user.id}: empty Name"
        )
    }
    if (user.address.isEmpty()) {
        throw IllegalArgumentException(
            "Can't save user ${user.id}: empty Address"
        )
    }

    // Save user to the database
}


fun saveUser2(user: User) {
    fun validate(user: User, value: String, fieldName: String) { // code reuse - nested functions
        if (value.isEmpty()) {
            throw IllegalArgumentException(
                "Can't save user ${user.id}: empty $fieldName"
            )
        }
    }
    validate(user, user.name, "Name")
    validate(user, user.address, "Address")

    // Save user to the database
}

fun saveUser3(user: User) {
    fun validate(value: String, fieldName: String) { // Use closure - use accessible to inner functions
        if (value.isEmpty()) {
            throw IllegalArgumentException(
                "Can't save user ${user.id}: empty $fieldName"
            )
        }
    }
    validate(user.name, "Name")
    validate(user.address, "Address")
    // Save user to the database
}


// 4. Use extension of User to validation
fun User.validateBeforeSave() {
    fun validate(value: String, fieldName: String) {
        if (value.isEmpty()) {
            throw IllegalArgumentException(
                "Can't save user $id: empty $fieldName")
        }
    }
    validate(name, "Name")
    validate(address, "Address")
}
fun saveUser4(user:User){
    user.validateBeforeSave();
    // saveUser
}

fun main(args: Array<String>) {
    saveUser(User(1, "", ""))
    saveUser2(User(1, "", ""))


}