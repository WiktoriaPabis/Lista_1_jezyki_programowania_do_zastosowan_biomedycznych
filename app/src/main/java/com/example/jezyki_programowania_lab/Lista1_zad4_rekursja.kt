package com.example.jezyki_programowania_lab

import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith

/**
 * @author  Wiktoria Pabiś
 *
 * Funkcja oblicza ciąg Fibonacciego wywołując dla każdego elementu ciągu funkcję fibonacciElement
 * i obliczone elementy zapisuje do mutowalnej listy intów.
 *
 * @param   sequence    mutowalna lista, do której zostanie zapisany ciąg Fibonacciego
 * @param   n           zadana liczba elementów, dla której należy utworzyć ciąg Fibonacciego
 * @throws  IllegalArgumentException     w przypadku gdy ilość elementów jest niedopuszczalna (n<=0)
 */
// Korzystałam ze schematu algorytmu na ciąg Fibonacciego: https://www.korepetycjezinformatyki.pl/ciag-fibonacciego/
fun Fibonacci_1(sequence: MutableList<Int>, n: Int){
    if (n <= 0){
        throw IllegalArgumentException ("Nie można utworzyć ciągu")
    } else{
        for (i in 0 until n){
            sequence.add(fibonacciElement(i))
        }
    }
}

/**
 * @author  Wiktoria Pabiś
 *
 * Funkcja rekurencyjna oblicza liczbę i-tą w ciągu Fibonacciego.
 *
 * @param   i   numer elementu ciągu Fibonacciego
 * @return      wartość i-tego elementu ciągu Fibonacciego
 */
fun fibonacciElement(i: Int): Int {
    return if (i <= 1) {
        1
    } else {
        fibonacciElement(i - 1) + fibonacciElement(i - 2)
    }
}

/**
 * @author  Wiktoria Pabiś
 *
 * Funkcja główna programu, która pobiera od użytkownika liczbę elementów ciągu Fibonacciego do
 * wygenerowania i wyświetla wynik.
 */
fun main() {
    val FibSequence: MutableList<Int> = mutableListOf()
    println("Podaj ilość elementów ciągu: ")
    val elem = readLine()?.toInt()!!
    Fibonacci_1(FibSequence, elem)
    println("Ciąg Fibonacciego: $FibSequence")
}

class fibonacciTest_1{

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

