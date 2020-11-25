package net.firzen.kotlinexperiments

import kotlin.math.abs

fun main() {
    val s = Singleton
    s.prime = 17
    s.show()

    val m = Singleton
    m.show()

    Singleton.show()

    val numbers = listOf(5, -8, 54, 0, 32, 21, -11, -366, 54, 8)
    println(numbers.sortedWith(AbsValueComparator))

    val window = Window()

    // Objects can be anonymous. They work the same way as the anonymous classes in Java,
    // and have the same syntax as normal objects, which implement an interface.
    // Anonymous objects can implement multiple interfaces, or no interface at all - unlike Java anon. classes.
    window.addMouseListener(object : MouseAdapter {
        override fun mouseClicked() {
            println("Clicked")
        }

        override fun mouseEntered() {
            println("Entered")
        }
    })
}

// objects represent singletons
// they CANNOT have constructors
// they, however, CAN have init block
object Singleton {
    init {
        println("Singleton initialized")
    }

    var prime = 11

    fun show() {
        println(prime)
    }
}

// objects can inherit from classes; they are good to be used when we don't need to store any state
// it is also possible to use nested objects inside of class
object AbsValueComparator : Comparator<Int> {
    override fun compare(p0: Int, p1: Int) = abs(p0).compareTo(abs(p1))
}

interface JsonParser<T> {
    fun fromJson(json: String) : T
}

// companion objects can be used to create static members
// companion objects can implement an interface!
class Symbol {
    companion object : JsonParser<Symbol> {
        override fun fromJson(json: String): Symbol {
            return Symbol()
        }
    }
}

// the same is valid for plain objects!
object Triangle : JsonParser<Triangle> {
    override fun fromJson(json: String): Triangle {
        return Triangle
    }
}

// extension functions can be used to add new function into existing companion objects;
// sadly, this doesn't work with classes that don't have any companion object
fun Symbol.Companion.fromXml(xml: String) : Symbol {
    return Symbol()
}

class Window {
    fun addMouseListener(listener: MouseAdapter) {
        println("Adding mouse listener ...")
    }
}

interface MouseAdapter {
    fun mouseClicked()
    fun mouseEntered()
}
