package net.firzen.kotlinexperiments

fun isPrime(number: Int) = (2 until Math.sqrt(number.toDouble()).toInt()).all { a -> number % a != 0 }
fun primes(limit: Int) = (2..limit).filter { a -> isPrime(a) }

fun primes3(limit: Int) {
	for(candidate in 2..limit) {
		var prime = true
		for(i in 2 until candidate) {
			if(candidate % i == 0) {
				prime = false
				break
			}
		}

		if(prime) println(candidate)
	}
}