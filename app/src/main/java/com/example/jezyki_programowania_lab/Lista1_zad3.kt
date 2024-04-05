package com.example.jezyki_programowania_lab

import org.junit.Test
import kotlin.test.assertEquals

/**
 * @author  Wiktoria Pabiś
 *
 * Funkcja generuje wszystkie podzbiory zbioru znaków [x].
 *
 * Generowanie wszystkich możliwych podzbiorów zbioru znaków [x] realizowane jest przez rekurencję
 * Zastosowano dwa rekurencyjne wywołania funkcji generate w celu uzyskania wszystkich możliwych
 * kombinacji zbioru. Wywołania różnią się uwzględnianiem i nie uwzględnianiem na każdym poziomie
 * rekurencyjnym danego znaku ze zbioru [x].
 * Każdy podzbiór jest zapisywany jako zbiór na liście wynikowej po spełnieniu warunku dotarcia
 * do ostatniego poziomu rekurencji.
 *
 * @param   index   zmienna odpowiadająca za liczbę poziomów rekurencji oraz pobieranie znaków
 *                  z początkowej listy.
 * @param   subset  zbiór znaków, dla których generowane są podzbiory
 * @param   list    początkowa lista znaków, dla której generowane są podzbiory
 * @param   result  lista wynikowa zawierająca wygenerowane podzbiory znaków
 * @return          lista zawierająca wszystkie podzbiory zbioru [x]
 */
fun generate(index:Int, subset:Set<Char>, list:List<Char>, result:MutableList<Set<Char>>){
    if (index == list.size){
        result.add(subset)
        return
    }

    generate(index + 1, subset + list[index], list, result)
    generate(index + 1, subset, list, result)
}

/**
 * @author  Wiktoria Pabiś
 *
 * Funkcja tworzy listę dla zbioru [x], tworzy wynikową listę zbiorów oraz wywołuje funkcję generate
 * z indeksem o wartości 0.
 *
 * @param   x   zbiór znaków, dla którego generowane są podzbiory
 * @return      lista zawierająca wszystkie podzbiory zbioru [x]
 */
fun podzbiory(x:Set<Char>):List<Set<Char>>{
    val list = x.toList()
    val result = mutableListOf<Set<Char>>()

    generate(0, emptySet(), list, result)
    return result
}

/**
 * @author  Wiktroia Pabiś
 *
 * Funkcja tworzy zbiór znaków, wywołuje funkcję podzbiory oraz wyświetla listę wszystkich
 * podzbiorów zbioru znaków [x].
 * Zaimplementowano strukturę danych typu set ze względu na brak konieczności sprawdzania powtórzeń
 * znaków w zbiorze.
 */
fun main(){
    val x: Set<Char> = setOf('a', 'b', 'c', 'd')
    val result = podzbiory(x)
    println(result)
}


class podzbioryTest{

    @Test
    fun testDefine() {
        val x = setOf('a', 'b', 'c', 'd')
        val result = podzbiory(x)
        val subsets = listOf(
            emptySet(),
            setOf('a'),
            setOf('b'),
            setOf('c'),
            setOf('d'),
            setOf('a', 'b'),
            setOf('a', 'c'),
            setOf('a', 'd'),
            setOf('b', 'c'),
            setOf('b', 'd'),
            setOf('c', 'd'),
            setOf('a', 'b', 'c'),
            setOf('a', 'b', 'd'),
            setOf('a', 'c', 'd'),
            setOf('b', 'c', 'd'),
            setOf('a', 'b', 'c', 'd')
        )

        assertEquals(subsets.size, result.size, "Nieprawidłowa ilość podzbiorów")
        subsets.forEach{ subset ->
            assert(result.contains(subset), { "Nieprawidłowo utworzony podzbiór" })
        }
    }

    @Test
    fun testEmpty() {
        val x = emptySet<Char>()
        val result = podzbiory(x)

        assertEquals(listOf(x), result, "Źle obsłużony przypadek pustego zbioru")
    }
}
