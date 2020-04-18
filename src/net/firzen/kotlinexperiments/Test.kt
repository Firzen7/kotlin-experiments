package net.firzen.kotlinexperiments

data class MyClass(
    val varA: String?,
    val varB: String
)

fun main() {
    val collection = listOf(
            MyClass(null, "A"),
            MyClass("a", "B"),
            MyClass("c", "C"),
            MyClass("b", "D"),
            MyClass("", "E"),
            MyClass("z", "F"),
            MyClass("b", "G"),
            MyClass(null, "H"),
            MyClass("", "I")
    )

//    fun MyClass.hashCode() : Int {
//        if(varA != null && varA.isNotEmpty())
//            return varA.hashCode()
//        else
//            return
//    }

//    val output = collection.sort
//    val output = collection.sortedBy {it.varA + it.varB}
//    collection.filter { it.varA != null && it.varA.isNotEmpty() }.sortedBy {  }
    val output = collection.sortedBy {if(it.varA != null && it.varA.isNotEmpty()) it.varA + it.varB else {it.varB}}

//    collection.sortedWith(nullsLast(reverseOrder()))
//    val output = collection.map { if(it.varA != null && it.varA.isNotEmpty()) it.varA + it.varB else {Char.MAX_VALUE + it.varB} }
//    output.sortedBy { it }.forEach { println(it) }
    output.forEach { println("MyClass(${it.varA}, ${it.varB})") }

//    println(("a" + "b").hashCode())
}