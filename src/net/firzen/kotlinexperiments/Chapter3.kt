package net.firzen.kotlinexperiments

import kotlin.math.pow

fun main() {
	val set = hashSetOf(1, 2, 3, 2)
	val map = hashMapOf(1 to "One", 2 to "Two", 3 to "Three")

	val list = arrayListOf<Int>(34, 45, 5645, 32)

	println(set)

	val simpleList = listOf("test", "bar", "baz")
	println("first item: ${simpleList.first()}")
	println("last item: ${simpleList.last()}")

	val simpleSet = setOf(4, 3, 6, -5)
	println("highest number: ${simpleSet.max()}")

	// named arguments are cool, just keep in mind, that it is not possible to use them
	// when calling Java methods from Kotlin code
	val primes = primes(end = 34, start = 10)
	println("Primes using named arguments: $primes")

	println(randomList())

	println("Primes product: ${primesProduct(409734567)}")

	val numbers = listOf(2, 45, 12, 78, -5, 0, 900, 11, 17, 23)
	println("Filtered list: ${numbers.justPrimes()}")

	// vararg arguments
	println(squares(4, 5, 2, 11))

	// infix notation and pairs
	val pair1 = 1.to("one")	// regular way to call builtin to function
	println(pair1)

	val pair2 = 1 to "one"			// calling function "to" using infix notation
	println(pair2)

	val myPair = 5 pair 7
	println(myPair)

	splitString()
	tripleQuoteStrings()
	localFunctions()
}

// this function has parameters with default values
fun randomList(max: Int = 100, length: Int = 10): List<Int> {
	val list = arrayListOf<Int>()

	for (i in 1..length) {
		list.add((Math.random() * max).toInt())
	}

	return list
}

// accepts arbitrary number of arguments!
fun squares(vararg values : Int) : List<Int> = values.map { it.toDouble().pow(2).toInt() }

// custom infix function
infix fun Any.pair(other: Any) = Pair(this, other)

fun splitString() {
	val name = "Harry.+*-MM.+Potter"

	// Kotlin's default split function does not take regular expression as an argument
	val initials = name.split(".+*-MM.+")

	val firstName = initials[0]
	val secondName = initials[1]

	println("Name of the chosen one was $firstName $secondName.")

	val cipher = "soxZmyyyAsxxaahmzbelowx+ssooosweeRabove"

	val secret = cipher.split(Regex("(x.*y)|(x.*mz)|(x.*R)"))

	println(secret)
	println("Remember: ${secret[1]} ${secret[3]} ${secret[0]} ${secret[2]}.")
}

fun tripleQuoteStrings() {
	// triple quote strings can be useful for regular expressions
	val unescaped = """This \string\ is un\es\ca\ped!"""
	println(unescaped)

	// also they can be used to write down strings with linebrakes comfortably,
	// could be nice for testing purposes :-)
	val xml = """<xml>
                    <name>Alfonso</name>
                    <age>15</age>
|				 </xml>""".trimMargin()

	// hmm, doesn't seem to be as reliable
	println(xml)
}

// Kotlin supports local functions, yay!!
fun localFunctions() {
	fun yes() {
		print("Yes!")
	}

	fun it() : String = " It"

	fun can() : String{
		fun beDone() = "be done!"
		return " can ${beDone()}"
	}

	yes()
	print(it())
	println(can())
}


