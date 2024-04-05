package com.example.jezyki_programowania_lab

import java.lang.IllegalArgumentException
import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith

/**
 * @author  Wiktoria Pabiś
 *
 * Znajduje część wspólną dwóch zbiorów x i y.
 * Elementy, które występują w obu listach tymczasowych, są oznaczane jako null w listach tymczasowych
 * [x_temp], [y_temp].
 *
 * @param   x   pierwsza lista liczb całkowitych
 * @param   y   druga lista liczb całkowitych
 * @return      lista liczb całkowitych, która zawiera elementy wspólne obu list
 * @throws  IllegalArgumentException    w przypadku kiedy nie ma części wspólnej zbioru
 */
fun wspolne(x:MutableList<Int>, y:MutableList<Int>):MutableList<Int?>{
    var common_part:MutableList<Int?> = mutableListOf()
    var x_temp:MutableList<Int?> = x.toMutableList()
    var y_temp:MutableList<Int?> = y.toMutableList()
    for (i:Int in 0..x_temp.size-1){
        for (j:Int in 0..y_temp.size-1){
            if (x_temp[i] == y_temp[j]){
                common_part.add(x_temp[i])
                x_temp[i] = null
                y_temp[j] = null
                break
            }
        }
    }
    if (common_part.size == 0){
       throw IllegalArgumentException("Brak części wspólnej zbiorów x i y")
    }
    return common_part
}


/**
 * @author  Wiktoria Pabiś
 *
 * Funkcja main tworzy dwa zbiory [x] i [y] bedące mutowalnymi listami.
 * Występuje wywołanie funkcji wspolne oraz wyświetlenie stosownych komunikatów.
 */
fun main(){
    var x:MutableList<Int> = mutableListOf(2,2,2,3,4,4,5)
    var y:MutableList<Int> = mutableListOf(2,2,8,9,4,4,4,5,5)
    var result = wspolne(x,y)
    println("Zbiór x: $x")
    println("Zbiór y: $y")
    println("Część wspólna zbiorów: $result")
}


class wspolneElementsTest{

    @Test
    fun testCommon(){
        val x:MutableList<Int> = mutableListOf(2, 2, 2, 3, 4, 4, 5)
        val y:MutableList<Int> = mutableListOf(2, 2, 8, 9, 4, 4, 4, 5, 5)

        val result = wspolne(x, y)

        assertEquals(listOf(2, 2, 4, 4, 5), result.filterNotNull(), "Elementy wspólne " +
                "zostały źle obliczone")
    }

    @Test
    fun testNoCommon(){
        val x:MutableList<Int> = mutableListOf(9, 12, 18)
        val y:MutableList<Int> = mutableListOf(4, 5, 6)

        val exception = assertFailsWith<IllegalArgumentException>("Zbiory x i y nie " +
                "posiadają części wspólnej"){
            wspolne(x, y)
        }
    }
}
