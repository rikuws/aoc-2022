import java.io.File

fun itemPriority(item: Char) = when (item.isUpperCase()) {
    true -> item.code - 38
    false -> item.code - 96
}

fun rucksackPrioritySum(rucksackContents: String): Int {
    val compartments = rucksackContents.chunked(rucksackContents.length / 2)
    return itemPriority(
        compartments
            .first()
            .asSequence()
            .find { compartments.last().contains(it) }
            ?: throw Exception("Something wrong with the rucksack"))
}

fun partOne(): Int {
    val inputFile = File("src/main/resources/ex3.input")
    val lines = inputFile.bufferedReader().readLines()

    return lines.sumOf { rucksackPrioritySum(it) }
}

fun commonItemForGroup(rucksacks: Sequence<String>): Int {
    val commonItem = rucksacks
        .first()
        .find {
            rucksacks
                .drop(1)
                .first()
                .contains(it) &&
            rucksacks
                .last()
                .contains(it)
        } ?: throw Exception("Something wrong with the rucksacks")

    return itemPriority(commonItem)
}

fun partTwo(): Int {
    val inputFile = File("src/main/resources/ex3.input")
    val lines = inputFile.bufferedReader().readLines().chunked(3)

    return lines.sumOf { commonItemForGroup(it.asSequence()) }
}

fun main(args: Array<String>) {
    println("Part 1: ${partOne()}\nPart 2: ${partTwo()}")
}