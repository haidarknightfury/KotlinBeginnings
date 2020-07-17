package chapter4

import java.io.Serializable

interface state:Serializable

interface MyView {
    fun getCurrentState(): State
    fun restoreState(state: State) {}
}

class AnotherButton : MyView {
    override fun getCurrentState(): State = ButtonState()
    override fun restoreState(state: State) { /*...*/ }
    class ButtonState : State { /*...*/ } // SIMILAR to static class nested class
}