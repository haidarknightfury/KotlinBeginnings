package chapter2

interface Expr
class Num(val value: Int) : Expr // Num is a class and implements an Expr
class Sum(val left: Expr, val right: Expr) : Expr // Sum has 2 properites which are expr

fun eval1(e: Expr): Int {
    if (e is Num) {
        val n = e as Num // redundant casting
        return n.value
    }
    if (e is Sum) {
        return eval1(e.right) + eval1(e.left)
    }
    throw IllegalArgumentException("Unknown expression")
}

fun eval2(e: Expr): Int =
    if (e is Num) {
        e.value
    } else if (e is Sum) {
        eval2(e.right) + eval2(e.left)
    } else {
        throw IllegalArgumentException("Unknown expression")
    }

fun eval(e: Expr): Int  =
    when (e){
        is Num -> e.value // Smart cast, no need to cast e as Num to get the value
        is Sum -> eval(e.left) + eval(e.right)
        else -> throw IllegalAccessException()
    }

fun evalWithLogging(e: Expr): Int =
    when (e) {
        is Num -> {
            println("num: ${e.value}") // curly braces - Allow more lines in between
            e.value
        }
        is Sum -> {
            val left = evalWithLogging(e.left)
            val right = evalWithLogging(e.right)
            println("sum: $left + $right")
            left + right
        }
        else -> throw IllegalArgumentException("Unknown expression")
    }

fun main(args: Array<String>) {
    println(eval(Sum(Sum(Num(1), Num(2)), Num(4))))
}