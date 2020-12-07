package net.firzen.kotlinexperiments

fun main() {
//    imperative()
//    functional()

//    val test = listOf("ahoj", "toto", "je", "pokus")
//    println(test.minBy { it.length })

//    val p = Propiska()
//    p.barva

//    val obd = Obdelnik()
//    obd.barva

    val sk1 = Sklenicka()
    sk1.pocetHran = 7
    sk1.vyska = 15

    val sk2 = Sklenicka()

    println(sk1.pocetHran)
    println(sk1)
    println(sk2)

    Sklenicka().pocetHran = 70

    val pocet = Sklenicka().pocetHran

    println(pocet)
}

data class Sklenicka(var pocetHran : Int = 5, var vyska : Int = 12,
                     var material : String = "sklo",
                     var barva : String = "N/A") {

}

//open class Tvar() {
//    protected var barva = "zelena"
//}
//
//class Obdelnik : Tvar() {
//    fun rekniBarvu() : String {
//        return barva
//    }
//}
//
//class Trojuhelnik : Tvar() {
//
//}

class Propiska {
    private val barva = "seda"
    private val delka = 13
    private val otevrena = false



}

fun imperative() {
    val animals = ArrayList<String>()

    animals.add("dog")
    animals.add("cat")
    animals.add("giraffe")
    animals.add("lion")
    animals.add("tiger")
    animals.add("dolphin")

    val length = animals.size
    for(i in 0 until length) {
        animals[i] = animals[i].length.toString()
    }

    for(i in 0 until length) {
        println(animals[i])
    }
}

fun functional() = println(
        listOf("dog", "cat", "giraffe", "lion", "tiger", "dolphin")
                .map {
                    it.length
                }
)

fun List<String>.map2(transformationFunction: (String) -> String) : List<String> {
    // vytvoříme výstupní seznam typu String
    val output = ArrayList<String>()

    // projdeme vstupní seznam, a s každým jeho prvkem zavoláme transformační funkci,
    // a její výsledek uložíme do výstupního seznamu
    for(item in this) {
        output.add(transformationFunction(item))
    }

    // vrátíme výstupní seznam jako výsledek
    return output
}
