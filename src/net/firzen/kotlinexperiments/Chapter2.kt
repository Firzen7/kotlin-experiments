package net.firzen.kotlinexperiments

import java.lang.IllegalArgumentException
import java.util.*

// Functions -----------------------------------------------------------------------------------------------------------

// not a valid code, Kotlin requires a function to execute code
// println("test")

fun main() {
	val name = "Kotlin"
	println("My name is $name")

	strings(121)
	classFun()

	println("Red color: " + Color.RED)

	//primes(100)

	println(primes(1000))
}

fun max(a: Int, b: Int): Int {
//    a = 5  // not permitted - variables are immutable by default (val)
	return if (a > b) a else b;
}

// This is not valid - return type must be specified
//
//fun max2(a: Int, b: Int) {
//    return if(a > b) a else b;
//}

// function with expression body - no return statement
fun max3(a: Int, b: Int): Int = if (a > b) a else b

// functions with expression body does not have to specify return type
fun max4(a: Int, b: Int) = if (a > b) a else b

// Variables -----------------------------------------------------------------------------------------------------------

var plainOldJavaVariable: String = "Hi"   // equivalent of String plainOldJavaVariable = "Hi"; in Java

val dontTouchMe = 42    // this is an Int constant, type of variables is inferred automatically at compile time
// dontTouchMe = 87     // will not compile


// val message;            // won't compile
// val message : String;   // won't compile here

fun test() {
//    val message;           // still not valid, must either have type or be initialized
	val message: String      // this is ok, we can initialize it later

	if (Math.random() > 0.5) {
		message = "It was more than 0.5"
	} else {
		message = "Ouch! Less than 0.5"
	}

	val greeting: String = if (Math.random() == 0.9) "Hello" else "Hi"

	val greeting2 = if (Math.random() == 0.9) "Hello" else "Hi"

	var number = 57
	// number = "sixty one"     // this won't compile - we can omit type when declaring variable, but it must remain
								// the same
}

fun strings(num: Int) {
	println("Number is: $num")
	println("This number is ${if (num > 10) "bigger" else "smaller"} than 10.")
	println("This" + " still " + "works")
}

// Collections ---------------------------------------------------------------------------------------------------------

fun lists() {
	val nationalities = arrayListOf("Czech", "English")
	nationalities.add("Scottish")                       // this is ok, because it doesn't change the actual reference
	// nationalities = arrayListOf("Turkish", "Spanish")   // this is not ok
}

// Classes -------------------------------------------------------------------------------------------------------------

class TestClass                     // minimal definition of class, public by default
private class TestClass2()          // private test class

// class Person(val name)           // types of member fields must be defined

class Person(val name: String)      // one-line declaration of class

fun classFun() {
//	val person = Person()   // will fail, as we didn't define the name of Person
	val person = Person("James")

	println("$person is a student")                 // no toString method by default
	println("Ok, so ${person.name} is a student")   // that's better :-)
}

class Rectangle(val height : Int, val width : Int) {
	val isSquare : Boolean
		get() {
			return height == width
		}

	val isAnswer : Boolean = (height == width)

	fun randomRect() : Rectangle {
		val random = Random()

		// no new operator in Kotlin
		return Rectangle(random.nextInt(), random.nextInt())
	}
}

enum class Color(val red : Int, val green : Int, val blue : Int) {
	RED(255,0, 0),
	GREEN(0, 255, 0),
	YELLOW(255, 255, 0),
	ORANGE(255, 128, 0),
	WHITE(255, 255, 255),
	BLACK(0, 0, 0),
	BLUE(0, 0, 255);

	fun isBlack() = (red == green && green == blue && blue == 0)
}

// Kotlin uses when instead of switch; switch is not supported
// when expression must be exhaustive here
fun getColorString(color: Color) = when (color) {
	Color.RED -> "Red color"
	Color.GREEN -> "Blue color"
	Color.BLUE -> "Green color"
	else -> "Not recognized"
}

fun getWarmth(color : Color) : String {
	// works with return, not the best approach though, Kotlin is being functional here
	when(color) {
		Color.RED, Color.ORANGE, Color.YELLOW -> return "warm"
		Color.GREEN, Color.BLUE -> return "cold"
		Color.WHITE -> return "white"
		Color.BLACK -> return "black"
	}
}

// possible to use when with complex objects as well
fun recognizeNumber(number : String) = when(number) {
	"one" -> 1
	"two" -> 2
	"three" -> 3
	"four" -> 4
	else -> throw Exception("Unknown number!")
}

// using when without argument, can help with optimization
fun mixColor(c1 : Color, c2 : Color) = when {
	(c1 == Color.RED && c2 == Color.YELLOW)
			|| (c2 == Color.RED && c1 == Color.YELLOW) -> Color.ORANGE
	(c1 == Color.BLUE && c2 == Color.YELLOW)
			|| (c2 == Color.BLUE && c1 == Color.YELLOW) -> {
		println("TEST")
		Color.GREEN
	}
	else -> throw Exception("Can't mix colours")
}

// Calculator ----------------------------------------------------------------------------------------------------------

interface Expr
//class Num(value: Int) : Expr                                // without "val value", the field value would be private
class Num(val value: Int) : Expr
class Sum(val left: Expr, val right: Expr) : Expr

fun eval(e: Expr) : Int {
	if(e is Num) {
		val n = e as Num
		return n.value
	}
	else if(e is Sum) {
		return eval(e.right) + eval(e.left)
	}
	throw IllegalArgumentException("Unknown expression!")
}

