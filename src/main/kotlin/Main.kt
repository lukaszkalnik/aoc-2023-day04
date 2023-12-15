package org.example

import java.io.File

fun main() {
    val input = File("input.txt").readLines()
    val scratchcards = input.map { it.split(":")[1] }

    val numberOfCards = MutableList(scratchcards.size) { 1 }

    scratchcards.mapIndexed { index, scratchcard ->
        val splitScratchcard = scratchcard.split("|")
        val winningNumbers = splitScratchcard[0].parseNumbers()
        val chosenNumbers = splitScratchcard[1].parseNumbers()

        val cardsWon = chosenNumbers.count { it in winningNumbers }
        for (i in index + 1..index + cardsWon) {
            numberOfCards[i] += numberOfCards[index]
        }
    }

    println(numberOfCards.sum())
}

fun String.parseNumbers(): List<Int> =
    """\d+""".toRegex().findAll(this).map { it.value.toInt() }.toList()