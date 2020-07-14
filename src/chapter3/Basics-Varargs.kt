package chapter3

fun parsePath(path: String) {
    val directory = path.substringBeforeLast("/") // Other utility function for file parsing
    val fullName = path.substringAfterLast("/")

    val fileName = fullName.substringBeforeLast(".")
    val extension = fullName.substringAfterLast(".")

    println("Dir: $directory, name: $fileName, ext: $extension")
}


fun parsePathRegExp(path: String) {
    // /Users/yole/kotlin-book/chapter.adoc
    val regex = """(.+)/(.+)\.(.+)""".toRegex() // Parentheses are groups - Backslash to escape characters
    val matchResult = regex.matchEntire(path)
    if (matchResult != null) {
        val (directory, filename, extension) = matchResult.destructured
        println("Dir: $directory, name: $filename, ext: $extension")
    }
}

fun main(args: Array<String>) {

    val list = listOf("args", *args) // spread operator - spread an array
    println(list)

    println("12.345-6.A".split("\\.|-".toRegex())) // Easily convert string to regex and split- Split by dot or -
    println("12.345-6.A".split(".", "-"))
    parsePath("/Users/yole/kotlin-book/chapter.adoc")

    // multiline tripple quoted strings
    val kotlinLogo = """| //
                   .|//
                   .|/ \"""
    println(kotlinLogo.trimMargin("."))

}