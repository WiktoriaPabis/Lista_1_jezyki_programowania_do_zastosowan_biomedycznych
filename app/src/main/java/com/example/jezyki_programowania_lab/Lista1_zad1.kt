package com.example.jezyki_programowania_lab

import kotlin.math.sqrt
import org.junit.Test
import kotlin.IllegalArgumentException
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith

/**
 * @author  Wiktoria Pabiś
 *
 * Funkcja oblicza pole trójkąta S wykorzystując wzór Herona.
 *
 * @param   a   pierwszy bok trójkąta
 * @param   b   drugi bok trójkąta
 * @param   c   trzeci bok trójkąta
 *
 * @return      pole trójkąta S, jeśli istnieje trójkąt o podanych bokach
 * @throws  IllegalArgumentException    w przypadku, gdy trójkąt o zadanych bokach nie istnieje
 */
fun heron(a:Double, b:Double, c:Double):Double{
    var S:Double = 0.0
    if(a>0.0 && b>0.0 && c>0.0 && a+b>c && a+c>b && b+c>a){
// Skorzystałam ze wzoru Herona ze strony: https://pl.wikipedia.org/wiki/Wzór_Herona
        val p = 0.5*(a+b+c)
        S = sqrt(p*(p-a)*(p-b)*(p-c))
        return S
    } else{
        throw IllegalArgumentException("Trójkąt o zadanych bokach nie istnieje")
    }
}

/**
 * @author  Wiktoria Pabiś
 *
 * Funkcja główna programu wywołuje funkcję heron dla ustalonych parametrów długości boków.
 * Wyświetla ona obliczone pole trójkąta S.
 */
fun main(){
    var a = 2.0
    var b = 3.0
    var c = 4.0

    var heron_1 = heron(a,b,c)
    println("Pole trójkąta o zadanych bokach wynosi: ${"%.2f".format(heron_1)}")

}


class triangleHeronTest{

    @Test
    fun testValid(){
        val a = 3.0
        val b = 4.0
        val c = 5.0

        val result = heron(a, b, c)

        assertEquals(6.0, result, "Błędnie obliczenie pola trójkąta")
    }

    @Test
    fun testInvalid(){
        val a = 2.0
        val b = 0.0
        val c = 0.0

        assertFailsWith<IllegalArgumentException>("Wyjątek nie został wywołany w przypadku " +
                "podania nieprawidłowych danych"){
            heron(a, b, c)
        }
    }

}
