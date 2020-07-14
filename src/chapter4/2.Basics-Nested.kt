package chapter4

import java.io.Serializable

interface State : Serializable

interface View {
    fun getCurrentState(): State
    fun restoreState(state: State) {}
}

class MyButton : View {
    override fun getCurrentState(): State {
        return ButtonState()
    }

    override fun restoreState(state: State){
        /*..*/
    }

    // Inner class
    class ButtonState : State {} // similar to  static nested class in Java.
}

class Outer{
    inner class Inner{
        // reference Outer class from inner class
        fun getOuterReference():Outer = this@Outer
    }
}