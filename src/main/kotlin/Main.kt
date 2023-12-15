package org.example

import java.io.File
import kotlin.math.pow

fun main() {
    val input = File("input.txt").readLines()
    val scratchcards = input.map { it.split(":")[1] }
    val sumOfPoints = scratchcards.sumOf { scratchcard ->
        val splitScratchcard = scratchcard.split("|")
        val winningNumbers = splitScratchcard[0].parseNumbers()
        val chosenNumbers = splitScratchcard[1].parseNumbers()

        chosenNumbers.filter { winningNumbers.contains(it) }
            .let { if (it.isEmpty()) 0 else 2.toDouble().pow(it.size - 1).toInt() }
    }

    println(sumOfPoints)
}

fun String.parseNumbers(): List<Int> =
    """\d+""".toRegex().findAll(this).map { it.value.toInt() }.toList()