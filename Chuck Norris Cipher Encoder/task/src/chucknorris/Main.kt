package chucknorris

class SeriesItem(
    val char: Char,
    var count: Int
){
    override fun toString(): String = "${this.char} = ${this.count}"
}

fun toBinary(x: Int, len: Int): String =
    Integer
        .toBinaryString(x)
        .padStart(7, '0')

fun main() {

    var theEnd = false
    while (theEnd != true) {
        println("Please input operation (encode/decode/exit):")
        var input = readln()!!
        when (input) {
            "encode" -> encode()
            "decode" -> decode()
            "exit" -> {
                theEnd = true
                println("Bye!")
            }
            else -> {
                println("There is no '$input' operation")
            }
        }
    }

}
    fun encode() {
        println("Input string:")
        val str = readln()!!
        println("Encoded string:")
        var strBin = ""

        for (element in str) {
            strBin += toBinary(element.code, 7)
        }
        var series = mutableListOf<SeriesItem>()

        var last = SeriesItem(strBin[0], 1)
        series.add(last)

        for (char in strBin.substring(1)) {
            if (last.char != char) {
                last = SeriesItem(char, 1)
                series.add(last)
            } else {
                last.count++
            }

        }
        series.forEach {
            if (it.char == '0') print("00 ") else print("0 ")
            repeat(it.count) {
                print('0')
            }
            print(" ")
        }
        println()
    }

fun decode() {

    println("Input encoded string:")
    var encodStr = readln()!!

    var countZ = encodStr.count{ p -> p != ' ' && p != '0' }
    var countCpace = encodStr.count{ p -> p== ' '}

    if ( countZ == 0  && countCpace % 2 != 0) {
    var seriesEncod = mutableListOf<SeriesItem>()

    var newstr = ""
    var count = 0
    var lasti = 0
    var x = ""
    encodStr = encodStr + " "
    var newBin = ""
    for (i in 0..encodStr.length - 1  ) {
        if (encodStr[i] == ' ')
        { count++
           if (count % 2 == 0) {
            newstr = encodStr.subSequence(lasti,i) as String
            //   println(newstr)
              if (newstr.substringBefore(' ') == "0")
                  x = "1" else
                      if (newstr.substringBefore(' ') == "00") x = "0" else {
                          println("Encoded string is not valid.")
                          return
                      }
               repeat(newstr.substringAfter(' ').count()) {newBin += x}
            lasti = i + 1
            }
        }
    }
lasti = 0
        var countChar = newBin.count()
       if (countChar % 7 == 0 )
       {
           newBin = newBin + " "
           println("Decoded string:")
           var rezult = ""

            for (i in 0..newBin.lastIndex step 7) {
                x = newBin.substring(lasti, i).toString()
                 if (x.length > 1) rezult += x.toInt(2).toChar()
                    lasti = i
            }

    println(rezult)

} else  println("Encoded string is not valid.")
    } else println("Encoded string is not valid.")

}
