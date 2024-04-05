package com.example.jezyki_programowania_lab

import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith

/**
 * @author  Wiktoria Pabiś
 *
 * Funkcja generuje ciąg Collatza obliczany dla danej liczby początkowej oraz zapisuje go w liście.
 *
 * @param   sequence    lista, do której zostaje zapisany ciąg Collatza
 * @param   c0          początkowa liczba w ciągu Collatza
 * @throws  IllegalArgumentException    w przypadku podania liczby, która nie jest liczbą naturalną
 */
// Korzystałam z opisu problemu ze strony: https://pl.wikipedia.org/wiki/Problem_Collatza
fun Collatz(sequence:MutableList<Int>, c0:Int){
    var work:Boolean = true
    var Cn:Int = c0
    var i:Int = 0
    if (Cn <= 0){
        throw IllegalArgumentException("Nie podano liczby naturalnej")
    }
    while(work){
        if (Cn % 2 == 0){
            Cn = Cn/2
            sequence.add(Cn)
        } else{
            Cn = 3*Cn+1
            sequence.add(Cn)
        }
        if(i >= 2){
            if (sequence[i] == 1 && sequence[i-1] == 2 && sequence[i-2] == 4){
                work = false
            }

        }

        i++
    }
}

/**
 * @author  Wiktoria Pabiś
 *
 * Funkcja pobiera początkową wartość ciągu Collatza od użytkownika [c0].
 * Funkcja wywołuje funkcję collatz oraz wyświetla ciąg Collatza.
 * Funkcja wykonuje testy dla wartości c0 w zakresie 1-100 000, po to, aby znaleźć maksymalną
 * wartość elementu ciągu, maksymalną długość ciągu dla któregoś z tych c0 oraz podaje te
 * wartości c0.
 */
fun main(){
    var sequence:MutableList<Int> = mutableListOf()
    println("Podaj początkową wartość c0: ")
    var c0 = readLine()?.toInt()!!
    Collatz(sequence, c0)
    println("Cykl Collatza: $sequence")

    println("Testy na wartościach c0 z zakresu 1-100000")
    var sequence_1:MutableList<Int> = mutableListOf()
    var maxCnValue = 0
    var c0Value = 0
    var maxCollatzLength = 0
    var c0CollatzLength = 0
    for (c0 in 1..100000){
        Collatz(sequence_1, c0)
        if (maxCnValue < sequence_1.max()){
            maxCnValue = sequence_1.max()
            c0Value = c0
        }
        if (maxCollatzLength < sequence_1.size){
            maxCollatzLength = sequence_1.size
            c0CollatzLength = c0
        }

        sequence_1 = mutableListOf()
    }

    println("Maksymalna wartość elementu ciągu: $maxCnValue dla c0: $c0Value \nMaksymalna długość" +
            " ciągu: $maxCollatzLength dla c0: $c0CollatzLength")
}


class collatzTest{

    @Test
    fun testValid(){
        val sequence = mutableListOf<Int>()
        val c0 = 10

        Collatz(sequence, c0)

        var validResult = listOf(5, 16, 8, 4, 2, 1)

        assertEquals(validResult, sequence, "Ciąg Collatza obliczony niepoprawnie")
    }

    @Test
    fun testInvalid(){
        val sequence = mutableListOf<Int>()
        val c0 = -20

        assertFailsWith<IllegalArgumentException>("Niedopuszczalna wartość początkowa") {
            Collatz(sequence, c0)
        }
    }
}
