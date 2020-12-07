package net.firzen.kotlinexperiments

import kotlin.math.sqrt

// in this file, there are top level utility functions

fun isPrime(number: Int) =
	number > 1 && (2..sqrt(number.toDouble()).toInt()).all { a -> number % a != 0 }

fun primes(start: Int = 2, end: Int) = ((if (start > 2) start else 2)..end).filter { a -> isPrime(a) }

fun primesProduct(number: Int) : ArrayList<Int> {
	var currentNumber = number
	val product = arrayListOf<Int>()

	while(currentNumber > 1) {
		val nearest = nearestPrime(currentNumber)
		product.add(nearest)
		currentNumber /= nearest
	}

	return product
}

private fun nearestPrime(number : Int) : Int {
	for(i in 2..number) {
		if(isPrime(i) && number % i == 0) return i;
	}

	throw UnknownError("Couldn't find nearest prime for number $number")
}

// extension function for integer collections, which filters out all the numbers that are not primes
// note that extension functions cannot be overrode
fun Collection<Int>.justPrimes() : Collection<Int> = this.filter { a -> isPrime(a) }
