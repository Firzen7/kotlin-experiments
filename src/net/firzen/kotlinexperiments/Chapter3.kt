package net.firzen.kotlinexperiments

fun main() {
//	val set = hashSetOf(1, 2, 3, 2)
//	val map = hashMapOf(1 to "One", 2 to "Two", 3 to "Three")

//	val list = arrayListOf<Int>(34, 45, 5645, 32)
//
//	println(set)
//
//	val simpleList = listOf("test", "bar", "baz")
//	println("first item: ${simpleList.first()}")
//	println("last item: ${simpleList.last()}")
//
//	val simpleSet = setOf(4, 3, 6, -5)
//	println("highest number: ${simpleSet.max()}")
//
//	// named arguments are cool, just keep in mind, that it is not possible to use them
//	// when calling Java methods from Kotlin code
//	val primes = primes(end = 34, start = 10)
//	println("Primes using named arguments: $primes")
//
//	println(randomList())

	println("Primes product: ${primesProduct(409734567)}")

	val numbers = listOf(2, 45, 12, 78, -5, 0, 900, 11, 17, 23)
	println("Filtered list: ${numbers.justPrimes()}")
}

// this function has parameters with default values
fun randomList(max: Int = 100, length: Int = 10): List<Int> {
	val list = arrayListOf<Int>()

	for (i in 1..length) {
		list.add((Math.random() * max).toInt())
	}

	return list
}


