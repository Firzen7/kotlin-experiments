package net.firzen.kotlinexperiments

import java.io.Serializable

fun main() {
    val button = Button()   // notice that no "new" operator is needed in Kotlin to create an instance
    button.click()
    button.showOff()

    val talkative = TalkativeButton()
    talkative.click()

    val bigClass = BigClass()
    bigClass.intro()

    Animal.Dog().identify()
    Animal.Chicken().identify()

    Cat().identify()
}

interface Clickable {
    fun click()
    fun showOff() = println("I am showing off!")
}

// Kotlin classes are final by default, open class is a "normal" Java class equivalent
open class Button : Clickable {
    override fun click() {
        println("Clicked!")
    }
}

// internal = visible inside of module, depends on build or IDE
internal open class TalkativeButton : Button() {
    private fun yell() = println("Hey!")
    protected fun whisper() = println("Let's talk!")
}

// this function has to be internal or less visible, we can't expose the internal class
internal fun TalkativeButton.giveSpeech() {
    // yell() <-- can't access, it is private in TalkativeButton
    // whisper() <-- also can't access, since it is protected

    click()  // <-- this is fine
}

interface State : Serializable

class BigClass {
    val number = 5

    fun intro() {
        NestedClass().introduce()
        InnerClass().introduce()
    }

    fun getNum() = number

    // nested classes don't have access to member fields and functions of outer class
    // Java equivalent: static class NestedClass
    class NestedClass {
        fun introduce() {
            println("I am a nested class. I can't tell you the number.")
            // getNum()    // invalid code
        }
    }

    // inner classes work just like the Java ones
    // Java equivalent: class InnerClass
    inner class InnerClass {
        fun introduce() {
            println("I am an inner class. The number is $number.")
            getNum()
        }
    }
}


// sealed classes list their subclasses specifically
sealed class Animal(private val legs: Int) {
    fun identify() {
        println("I have $legs legs.")
    }

    class Dog : Animal(4)
    class Chicken : Animal(2)
    class Snake : Animal(0)
}

// This is still possible,as long as we are in the same file.
// It wouldn't work outside of this file.
class Cat : Animal(4)
