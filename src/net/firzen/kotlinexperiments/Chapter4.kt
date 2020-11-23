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

    userTest()

    dataClassTest()

    byTest()
}

/**
 * By keyword is used to create delegates automatically without too much of boilerplate code.
 * Normally, the Decorator pattern is used for implementing delegates.
 */
private fun byTest() {
    val decorated = DecoratedCollection<Int>()
    decorated.add(5)
    decorated.add(7)

    decorated.forEach { println(it) }

    val delegated = DelegatedCollection<Int>()
    delegated.add(5)
    delegated.add(7)

    delegated.forEach { println(it) }
}

class DelegatedCollection<T> (private val innerList: MutableCollection<T> = arrayListOf())
    : Collection<T> by innerList {

    fun add(element: T) = innerList.add(element)
}

/**
 * This is a child of Collection implemented by traditional Decorator pattern.
 * Notice the amount of boilerplate code.
 */
class DecoratedCollection<T> : MutableCollection<T> {
    private val innerList = arrayListOf<T>()

    override fun add(element: T) = innerList.add(element)
    override fun addAll(elements: Collection<T>): Boolean = innerList.addAll(elements)
    override val size: Int get() = innerList.size
    override fun contains(element: T): Boolean = innerList.contains(element)
    override fun containsAll(elements: Collection<T>): Boolean = innerList.containsAll(elements)
    override fun isEmpty(): Boolean = innerList.isEmpty()
    override fun iterator(): MutableIterator<T> = innerList.iterator()
    override fun clear() = innerList.clear()
    override fun remove(element: T): Boolean = innerList.remove(element)
    override fun removeAll(elements: Collection<T>): Boolean = removeAll(elements)
    override fun retainAll(elements: Collection<T>): Boolean = retainAll(elements)
}

private fun dataClassTest() {
    val alan = BestClass("Alan", 19)
    println("Data classes have pre-generated methods equals, hashCode and toString!")
    println("${alan.toString()} --> ${alan.hashCode()}")

    // Also, data classes contain pre-generated copy function!!
    val john = alan.copy(name = "John")
    println("${john.name} was born, but ${alan.name} remained unchanged!")
    println("${john.name}'s hash is ${john.hashCode()}, and ${alan.name}'s is ${alan.hashCode()}")
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

// This is still possible, as long as we are in the same file.
// It wouldn't work outside of this file.
class Cat : Animal(4)

// Kotlin classes more in depth:

fun userTest() {
    val user = User("Mike")
    println(user.nickname)

    val user1 = User1("Fred")
    println(user1.nickname)

    val user3 = User3("LilHusky")
//    println(user3.nickname)    INVALID CODE!

    val specialUser = SpecialUser("Jetty")
    println("Age: ${specialUser.age}, Nick: ${specialUser.nickname}")

    val ee = Empty()
}

class User(_nickname: String) {     // <-- primary constructor
    val nickname: String            // field

    // primary initializer
    // (to be used together with primary constructors)
    init {
        nickname = _nickname
    }
}

// this class is equivalent to class User
class User1(_nickname: String) {
    val nickname = _nickname
}

// equivalent of User and User1
class User2(val nickname: String)

// nickname won't be accessible with this declaration
class User3(nickname: String)


// primary constructor with default parameters
open class NormalUser(val age: Int, val nickname: String = "No nickname")

// println("Age: ${specialUser.age}, Nick: ${specialUser.nickname}") <-- works normally
class SpecialUser(nickname: String) : NormalUser(21, nickname)

// this class still has a default constructor
class Empty

data class BestClass(val name: String, val age: Int)
