package chapter4

interface Clickable {
    public fun click();
    fun showOff() = println("I was clicked")
}

interface Focusable {
    fun setFocus(b: Boolean) {
        println("I ${if (b) "got" else "lost"} focus.")
    }

    fun showOff() = println("I am focusable")
}


class Button : Clickable, Focusable { // Implement interface using :

    override fun click() { // override is inline compared to java which is @Override
        println("I was clicked")
    }

    override fun showOff() {
        // “super” qualified by the supertype same member is inherited. name in angle brackets specifies the
        //parent whose method you want to call.
        super<Clickable>.showOff();
        super<Focusable>.showOff();
    }
}

// By default a class is final
open class RichButton : Clickable {

    fun disable() {} // final by default - cannot override this function

    open fun animate() {} // open to override

    // mark as final to prevent further override
    final override fun click() {} //override method
}

// open - Can be overridden
// final - cannot be overriden
// abstract - must be overriden

// visibility

// public - visible everywhere
// internal - visible in a module
// protected - visible in subclasses
// private  - visible in a class


internal open class TalkativeButton : Focusable {
    private fun yell() = println("Hey!")
    protected fun whisper() = println("Let's talk!")
}
//
//fun TalkativeButton.giveSpeech(){ // giveSpeech is public function trying to violate internal
//
//}

abstract class Animated { // abstract class - cannot create an instance of it

    abstract fun animate() // function is abstract- no implementation - must be overridden

    open fun stopAnimating() {}

    fun animateTwice() {}
}

fun main(args: Array<String>) {
    Button().click()
    val button = Button()
    button.showOff()
    button.setFocus(true)
    button.click()
}

