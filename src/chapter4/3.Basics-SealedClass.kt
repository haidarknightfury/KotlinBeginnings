package chapter4

// defining restricted class hierarchies
// handle all the possible subclasses
// restricts the possibility of creating subclasses. All
//the direct subclasses must be nested in the superclass:

sealed class Expr { // Expr class has a private constructor - can be called only inside the class
    class Num(val value: Int) : Expr()
    class Sum(val left: Expr, val right: Expr) : Expr()
}

fun eval(e: Expr): Int =
    when (e) {
        is Expr.Num -> e.value
        is Expr.Sum -> eval(e.right) + eval(e.left)
//        else -> // else is redundant when using sealed class
//            throw IllegalArgumentException("Unknown expression")
    }

fun main(args: Array<String>) {
    println(eval(Expr.Sum(Expr.Sum(Expr.Num(1), Expr.Num(2)), Expr.Num(4))))
}