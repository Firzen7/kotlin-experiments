package net.firzen.kotlinexperiments

fun main() {
    val button = Button()   // notice that no "new" operator is needed in Kotlin to create an instance
    button.click()
}

interface Clickable {
    fun click()
}

class Button : Clickable {
    override fun click() {
        println("Clicked!")
    }
}

