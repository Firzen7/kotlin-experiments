package net.firzen.kotlinexperiments


fun main() {
    usingObject()
    usingLambda()
    varAccess()
}

// Generating primes using anonymous object - alternative of Java's anonymous class
// it is nice, but still quite verbose
private fun usingObject() {
    val gen = object : Generator {
        override fun generate(from: Int, to: Int): List<Int> {
            return primes(from, to)
        }
    }
    val primes = gen.generate(22, 33)

    println(primes)
}

// Generating primes using lambda
private fun usingLambda() {
    val lambda = { primes(22, 33) }     /* lambda is between the {} braces, it accepts no parameters */
    val primes = lambda()               /* here we call lambda function */

    println(primes)

    // lambda below could also be declared as:
    // val lambda2: (Int) -> List<Int> = { limit: Int -> primes(0, limit) }

    val lambda2 = { limit: Int -> primes(0, limit) }    /* this lambda accepts one parameter of type Int */
    val primes2 = lambda2(75)

    println(primes2)
}

// Kotlin lambda functions can write into local variables
private fun varAccess() {
    var number = 0

    val increment = {
        println("Incrementing number ...")
        number++
    }
    val tell = { println("Number is: $number") }

    tell()
    increment()
    tell()
}

interface Generator {
    fun generate(from: Int, to: Int) : List<Int>
}

//class PrimeGenerator : Generator {
//    override fun generate(from: Int, to: Int) = primes(from, to)
//}

private class Dog(val name: String, val age: Int)
