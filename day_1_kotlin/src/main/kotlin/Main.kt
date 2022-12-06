import arrow.core.None
import java.io.File

tailrec fun asGroups(lines: List<String>, accGroup: List<List<String>> = emptyList()): List<List<String>> {
    val group = lines.takeWhile { it != "" }

    if (group.isEmpty()) {
        return accGroup
    }

    return asGroups(lines.drop(group.size + 1), accGroup + listOf(group))
}

fun partOne() {
    val inputFile = File("src/main/resources/ex_1.input")
    val lines = inputFile.bufferedReader().readLines()

    val groups = asGroups(lines).asSequence()

    val max = groups.map { it.sumOf { s -> s.toInt() } }.max()
    println(max)
}

fun partTwo() {
    val inputFile = File("src/main/resources/ex_1.input")
    val lines = inputFile.bufferedReader().readLines()

    val groups = asGroups(lines).asSequence()

    val topThree = groups.map { it.sumOf { s -> s.toInt() } }.sortedDescending().take(3).sum()
    println(topThree)
}

fun main(args: Array<String>) {
    partTwo()
}
