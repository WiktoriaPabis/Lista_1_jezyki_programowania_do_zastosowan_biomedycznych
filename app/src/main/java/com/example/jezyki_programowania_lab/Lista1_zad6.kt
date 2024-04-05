package com.example.jezyki_programowania_lab

import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith

/**
 * @author  Wiktoria Pabiś
 *
 * Funkcja przyjmuje sekwencję nici kodującej DNA w kierunku od 5' do 3' i zwraca jej komplementarną
 * sekwencję nici matrycowej DNA w kierunku od 3' do 5'.
 * Kod komplementarnej nici matrycowej jest tworzony poprzez zamianę każdej zasady na jej
 * komplementarną zasadę według reguł: A -> T, T -> A, C -> G, G -> C.
 *
 * @param   DNA_kod ciąg znaków reprezentujący sekwencję nici kodującej DNA, który ma zostać
 *                  przekształcony na odpowiadającą mu nić matrycową DNA
 * @return          zwraca ciąg znaków DNA_mat reprezentujący komplementarny kod DNA nici matrycowej,
 *                  w przypadku poprawności wprowadzonych danych wejściowych.
 * @throws  IllegalArgumentException    w przypadku, gdy podana zasada nie istnieje
 */
fun komplement(DNA_kod:String):String{
    var DNA_mat:String = ""
    for (zasada in DNA_kod){
        when(zasada){
            'A' -> DNA_mat = DNA_mat + 'T'
            'T' -> DNA_mat = DNA_mat + 'A'
            'C' -> DNA_mat = DNA_mat + 'G'
            'G' -> DNA_mat = DNA_mat + 'C'

            else ->{
                throw IllegalArgumentException ("Podano nieprawidłowe wartości")
            }
        }
    }

    DNA_mat = DNA_mat.reversed()

    return DNA_mat
}

/**
 * @author  Wiktoria Pabiś
 *
 * Funkcja przyjmuje sekwencję nici matrycowej DNA w kierunku od 3' do 5' i zwraca jej komplementarną
 * sekwencję cząsteczki RNA w kierunku od 5' do 3'.
 * Kod komplementarnej sekwencji łańcucha RNA jest tworzony poprzez zamianę każdej zasady na jej
 * komplementarną zasadę według reguł: A -> U, T -> A, C -> G, G -> C.
 *
 * @param   DNA_matryc  ciąg znaków reprezentujący sekwencję nici matrycowej DNA, który ma zostać
 *                      przekształcony na odpowiadający mu łańcuch RNA
 * @return              zwraca ciąg znaków RNA reprezentujący komplementarny kod łańcucha RNA,
 *                      w przypadku poprawności wprowadzonych danych wejściowych.
 * @throws  IllegalArgumentException    w przypadku, gdy podana zasada nie istnieje
 */
fun transkrybuj(DNA_matryc:String):String{
    var RNA:String = ""
    var DNA_matryc_1 = DNA_matryc.reversed()
    for (zasada in DNA_matryc_1){
        when(zasada){
            'A' -> RNA = RNA + 'U'
            'T' -> RNA = RNA + 'A'
            'C' -> RNA = RNA + 'G'
            'G' -> RNA = RNA + 'C'

            else ->{
                throw IllegalArgumentException ("Podano nieprawidłowe wartości")
            }
        }
    }

    return RNA
}

/**
 * @author  Wiktoria Pabiś
 *
 * Funkcja przyjmuje sekwencję mapę stringów oraz sekwencję nici RNA i zwraca listę stringów.
 * Tłumaczy sekwencję mRNA na sekwencję aminokwasów za pomocą dostarczonej mapy kodonów.
 * Założeniem funkcji jest, że pracuje ona na liczbie zasad azotowych podzielnych przez 3.
 *
 * @param   codonsMap1  mapa kodonów, w której klucz to kodon mRNA, a wartością jest odpowiadający
 *                      aminokwas
 * @param   mRNA        sekwencja mRNA do tłumaczenia na aminokwasy
 * @return              lista aminokwasów, które odpowiadają sekwencji mRNA, z tym założeniem, że
 *                      podana sekwencja zaiwera podzielną przez 3 liczbę zasad azotowych
 * @throws  IllegalArgumentException    w przypadku, gdy liczba zasad azotowych nie jest podzielna
 *                                      przez 3 oraz w przypadku, gdy nie występuje kodon STOP lub
 *                                      rozpoczynający
 */
fun transluj(codonsMap1: Map<String, String>, mRNA: String):List<String>{
    var listAmino:MutableList<String> = mutableListOf()
    var tempCodon:String = ""
    var startTransl:Boolean = false
    var stopTransl:Boolean = false
    var tempListOfCodons:MutableList<String> = mutableListOf()
    var count:Int = 0
    if (mRNA.length % 3 != 0){
        throw IllegalArgumentException("Nieprawidłowa liczba zasad azotowych, proszę podać ilość zasad azotowych podzielną " +
                "przez liczbę 3.")
    }
        for (elem in mRNA){
            tempCodon = tempCodon + elem
            if (count == 2){
                if (startTransl){
                    if (tempCodon == "UAA" || tempCodon == "UAG" || tempCodon == "UGA"){
                        stopTransl = true
                        tempListOfCodons.add(tempCodon)
                    }
                }
                if ((tempCodon == "AUG" || startTransl) && !stopTransl){
                    tempListOfCodons.add(tempCodon)
                    startTransl = true
                }

                tempCodon = ""
                count = -1
            }
            count++
        }
        for (elem in tempListOfCodons){
            listAmino.add(codonsMap1[elem].toString())
        }
    if (!startTransl){
        throw IllegalArgumentException ("Brak kodonu rozpoczynającego")
    }
    if (!stopTransl){
       throw IllegalArgumentException ("Brak kodonu STOP")
    }

    return listAmino
}


/**
 * @author  Wiktoria Pabiś
 *
 * Funkcja zwraca mapę, w której kluczem jest kodon RNA, natomiast wartością odpowiadający
 * mu aminokwas.
 * Mapa zawiera zestawienie kodonów RNA, a także aminokwasy im odpowiadające, według standardowego
 * kodu genetycznego.
 *
 * @return      mapa kodonów RNA wraz z odpowiadającymi im aminokwasami
 */
fun codons():Map<String, String>{
    var codonsMap = mapOf(
//      Mapa amonikwasów wygenerowana za pomocą modelu językowego
        // Alanina
        "GCU" to "Ala", "GCC" to "Ala", "GCA" to "Ala", "GCG" to "Ala",
        // Cysteina
        "UGU" to "Cys", "UGC" to "Cys",
        // Kwas asparaginowy
        "GAU" to "Asp", "GAC" to "Asp",
        // Kwas glutaminowy
        "GAA" to "Glu", "GAG" to "Glu",
        // Fenyloalanina
        "UUU" to "Phe", "UUC" to "Phe",
        // Glicyna
        "GGU" to "Gly", "GGC" to "Gly", "GGA" to "Gly", "GGG" to "Gly",
        // Histydyna
        "CAU" to "His", "CAC" to "His",
        // Izoleucyna
        "AUU" to "Ile", "AUC" to "Ile", "AUA" to "Ile",
        // Lizyna
        "AAA" to "Lys", "AAG" to "Lys",
        // Leucyna
        "UUA" to "Leu", "UUG" to "Leu", "CUU" to "Leu", "CUC" to "Leu", "CUA" to "Leu", "CUG" to "Leu",
        // Metionina lub kodon rozpoczynający transkrypcję
        "AUG" to "Met",
        // Asparagina
        "AAU" to "Asn", "AAC" to "Asn",
        // Prolina
        "CCU" to "Pro", "CCC" to "Pro", "CCA" to "Pro", "CCG" to "Pro",
        // Glutamina
        "CAA" to "Gln", "CAG" to "Gln",
        // Arginina
        "CGU" to "Arg", "CGC" to "Arg", "CGA" to "Arg", "CGG" to "Arg", "AGA" to "Arg", "AGG" to "Arg",
        // Seryna
        "UCU" to "Ser", "UCC" to "Ser", "UCA" to "Ser", "UCG" to "Ser", "AGU" to "Ser", "AGC" to "Ser",
        // Treonina
        "ACU" to "Thr", "ACC" to "Thr", "ACA" to "Thr", "ACG" to "Thr",
        // Walina
        "GUU" to "Val", "GUC" to "Val", "GUA" to "Val", "GUG" to "Val",
        // Tryptofan
        "UGG" to "Trp",
        // Tyrozyna
        "UAU" to "Tyr", "UAC" to "Tyr",
        // Kodony STOP
        "UAA" to "Ter", "UAG" to "Ter", "UGA" to "Ter"
    )

    return codonsMap
}
/**
* @author  Wiktoria Pabiś
 *
 * Funkcja główna programu obsługuje wprowadzenie sekwencji DNA, przekształca ją w nić matrycową,
 * tworzy transkrypcję na sekwencję RNA oraz wykonuje translację na sekwencję aminokwasów
 * i wyświetla wyniki.
*/
fun main(){
    println(
        "Proszę wprowadzić odpowiednią sekwencję nici kodującej DNA w kierunku od 5' do 3' z " +
                "możliwych kombinacji zasad azotowych A T G C:  "

    )
    var DNA_kod:String = readLine()?.toString()!!
    var matryca = komplement(DNA_kod)
    println("Sekwencja nici matrycowej w kierunku od 3' do 5': $matryca")
    var nic_RNA = transkrybuj(matryca)
    println("Sekwencja RNA w kierunku od 5' do 3': $nic_RNA")
    var mapOfCodons = codons()

    var AminoList = transluj(mapOfCodons, nic_RNA)
    println("Sekwencja aminokwasów: ${AminoList.toString()}")
}


class komplementTest{

    @Test
    fun komplementValid(){
        var DNA_kod:String = "ATGCGC"
        var matryca = komplement(DNA_kod)
        assertEquals("GCGCAT", matryca, "Nieprawidłowo utworzona " +
                "sekwencja nici matrycowej DNA")
    }

    @Test
    fun komplementInvalid(){
        var DNA_kod:String = "ATGCXYZ"
        assertFailsWith<IllegalArgumentException>("Niedopuszczalna wartość początkowa"){
            komplement(DNA_kod)
        }
    }
}

class transkrybujTest{

    @Test
    fun transkrybujValid(){
        var DNA_mat:String = "GCGCAT"
        var RNA = transkrybuj(DNA_mat)
        assertEquals("AUGCGC", RNA, "Nieprawidłowo utworzona " +
                "sekwencja nici RNA")
    }

    @Test
    fun transkrybujInvalid(){
        var DNA_mat:String = "ATGCXYZ2"
        assertFailsWith<IllegalArgumentException>("Niedopuszczalna wartość początkowa"){
            komplement(DNA_mat)
        }
    }
}


class translujTest{

    @Test
    fun translujValid(){
        val mapOfCodons = codons()
        var AminoList = transluj(mapOfCodons, "AUGUUUUUCUAG")
        assertEquals(listOf("Met", "Phe", "Phe", "Ter"), AminoList, "Nieprawidłowo wykonana " +
                "translacja")
    }

    @Test
    fun translujWithoutStartCodon(){
        val mapOfCodons = codons()
        assertFailsWith<IllegalArgumentException>("Nieprawidłowo obsłużony wyjątek (kodon rozpoczynający)") {
            transluj(mapOfCodons, "UUUUUCUAG")
        }
    }

    @Test
    fun translujWithoutStopCodon(){
        val mapOfCodons = codons()
        assertFailsWith<IllegalArgumentException>("Nieprawidłowo obsłużony wyjątek (kodon STOP)") {
            transluj(mapOfCodons, "AUGUUUUUC")
        }
    }

    @Test
    fun translujInvalidNumber(){
        val mapOfCodons = codons()
        assertFailsWith<IllegalArgumentException>("Nieprawidłowo obsłużony wyjątek " +
                "(liczba zasad azotowych niepodzielna przez 3)"){
            transluj(mapOfCodons, "AUGUUUA")
        }
    }
}

