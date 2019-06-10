package net.firzen.kotlinexperiments

import kotlin.math.sqrt
import kotlin.system.measureNanoTime

fun main(args: Array<String>) {
	var primes : List<Int> = ArrayList<Int>()
	val time = measureNanoTime {
		fun isPrime(number: Int) = (2 until sqrt(number.toDouble()).toInt()).all { a -> number % a != 0 }
		fun primes(limit: Int) = (2..limit).filter { a -> isPrime(a) }
		primes = primes(args[0].toInt())
	}

	println("Generated in ${time / 1000000} ms")
	println(primes)
}