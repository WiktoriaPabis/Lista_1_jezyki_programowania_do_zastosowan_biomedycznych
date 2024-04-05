package com.example.jezyki_programowania_lab

import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith

/**
 * @author  Wiktoria Pabiś
 *
 * Funkcja oblicza ciąg Fibonacciego o zadanej liczbie elementów i zapisuje go do listy.
 *
 * @param   sequence    mutowalna lista, do której zostanie zapisany ciąg Fibonacciego
 * @param   n           zadana liczba elementów, dla której należy utworzyć ciąg Fibonacciego
 * @throws  IllegalArgumentException    w przypadku gdy ilość elementów jest niedopuszczalna (n<=0)
 */
// Korzystałam ze wzoru na ciąg Fibonacciego: https://pl.wikipedia.org/wiki/Ciąg_Fibonacciego
fun Fibonacci(sequence:MutableList<Int>, n:Int){
    if (n<=0){
      throw IllegalArgumentException ("Nieprawidłowe wartości")
    }
    sequence.add(0,1)
    if (n == 1){
        return
    }

    sequence.add(1,1)
    if (n == 2){
        return
    }

    for (i in 2 until  n){
      sequence.add((sequence[i-1])+(sequence[i-2]))
    }
}


/**
 * @author  Wiktoria Pabiś
 *
 * Funkcja główna programu, która pobiera od użytkownika liczbę elementów ciągu Fibonacciego do
 * wygenerowania i wyświetla wynik.
 */
fun main(){
    var FibSequence:MutableList<Int> = mutableListOf()
    println("Podaj ilość elementów ciągu: ")
    var elem = readLine()?.toInt()!!
    Fibonacci(FibSequence, elem)
    println("Ciąg Fibonacciego: $FibSequence")
}

class fibonacciTest{

    @Test
    fun testAboveZero() {
        val sequence = mutableListOf<Int>()
        val n = 12

        Fibonacci(sequence, n)

        assertEquals(listOf(1, 1, 2, 3, 5, 8, 13, 21, 34, 55, 89, 144), sequence,
            "Nieprawidłowy ciąg Fibonacciego")
    }

    @Test
    fun testBelowZero(){
        val sequence = mutableListOf<Int>()
        val n = -5

        assertFailsWith<IllegalArgumentException>("Wyjątek nie został prawidłowo obsłużony"){
            Fibonacci(sequence, n)
        }
    }

    @Test
    fun testZero() {
        val sequence = mutableListOf<Int>()
        val n = 0

        assertFailsWith<IllegalArgumentException>("Wyjątek nie został prawidłowo obsłużony"){
            Fibonacci(sequence, n)
        }
    }
}
