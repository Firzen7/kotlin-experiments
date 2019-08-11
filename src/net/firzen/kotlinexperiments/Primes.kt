package net.firzen.kotlinexperiments


fun primes3(limit: Int) {
	for (candidate in 2..limit) {
		var prime = true
		for (i in 2 until candidate) {
			if (candidate % i == 0) {
				prime = false
				break
			}
		}

		if (prime) println(candidate)
	}
}