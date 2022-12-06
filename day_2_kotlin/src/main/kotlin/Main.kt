import java.io.File
import java.lang.Exception

enum class Shape(val score: Int) {
    ROCK(1),
    PAPER(2),
    SCISSORS(3)
}

enum class Outcome(val score: Int) {
    WIN(6),
    LOSE(0),
    DRAW(3)
}

fun readRPSBattlePt1(battle: String): Int {
    val battlePair = battle.split(" ").map { shapeStr ->
        when (shapeStr) {
            "A" -> Shape.ROCK
            "B" -> Shape.PAPER
            "C" -> Shape.SCISSORS
            "X" -> Shape.ROCK
            "Y" -> Shape.PAPER
            "Z" -> Shape.SCISSORS
            else -> throw Exception("Invalid RPS Shape")
        }
    }

    val outcome = runBattlePair(Pair(battlePair.last(), battlePair.first()))
    return getScoreForOutcome(battlePair.last(), outcome)
}

fun getScoreForOutcome(myShape: Shape, outcome: Outcome): Int {
    return myShape.score + outcome.score
}

fun runBattlePair(pair: Pair<Shape, Shape>) = when (pair) {
    Pair(Shape.ROCK, Shape.ROCK) -> Outcome.DRAW
    Pair(Shape.ROCK, Shape.PAPER) -> Outcome.LOSE
    Pair(Shape.ROCK, Shape.SCISSORS) -> Outcome.WIN
    Pair(Shape.PAPER, Shape.PAPER) -> Outcome.DRAW
    Pair(Shape.PAPER, Shape.SCISSORS) -> Outcome.LOSE
    Pair(Shape.PAPER, Shape.ROCK) -> Outcome.WIN
    Pair(Shape.SCISSORS, Shape.SCISSORS) -> Outcome.DRAW
    Pair(Shape.SCISSORS, Shape.ROCK) -> Outcome.LOSE
    Pair(Shape.SCISSORS, Shape.PAPER) -> Outcome.WIN
    else -> throw Exception("Invalid RPS Pair")
}

fun getShapeForOutcome(pair: Pair<Shape, Outcome>) = when (pair) {
    Pair(Shape.ROCK, Outcome.DRAW) -> Shape.ROCK
    Pair(Shape.ROCK, Outcome.LOSE) -> Shape.SCISSORS
    Pair(Shape.ROCK, Outcome.WIN) -> Shape.PAPER
    Pair(Shape.PAPER, Outcome.DRAW) -> Shape.PAPER
    Pair(Shape.PAPER, Outcome.LOSE) -> Shape.ROCK
    Pair(Shape.PAPER, Outcome.WIN) -> Shape.SCISSORS
    Pair(Shape.SCISSORS, Outcome.DRAW) -> Shape.SCISSORS
    Pair(Shape.SCISSORS, Outcome.LOSE) -> Shape.PAPER
    Pair(Shape.SCISSORS, Outcome.WIN) -> Shape.ROCK
    else -> throw Exception("Invalid Shape Outcome Pair")
}

fun readShapeStr(shapeStr: String): Shape = when (shapeStr) {
    "A" -> Shape.ROCK
    "B" -> Shape.PAPER
    "C" -> Shape.SCISSORS
    else -> throw Exception("invalid RPS shape")
}

fun readOutcomeStr(outcomeStr: String): Outcome = when (outcomeStr) {
    "X" -> Outcome.LOSE
    "Y" -> Outcome.DRAW
    "Z" -> Outcome.WIN
    else -> throw Exception("invalid RPS outcome")
}

fun readRPSBattlePt2(battle: String): Int {
    val shapeAndOutcomeStr = battle.split(" ")
    val theirShape = readShapeStr(shapeAndOutcomeStr.first())
    val outcome = readOutcomeStr(shapeAndOutcomeStr.last())
    val myShape = getShapeForOutcome(Pair(theirShape, outcome))

    return getScoreForOutcome(myShape, outcome)
}

fun partOne() {
    val inputFile = File("src/main/resources/day_2.input")
    val lines = inputFile.bufferedReader().readLines()

    val battlesSum = lines.sumOf { readRPSBattlePt1(it) }

    println(battlesSum)
}

fun partTwo() {
    val inputFile = File("src/main/resources/day_2.input")
    val lines = inputFile.bufferedReader().readLines()

    val battlesSum = lines.sumOf { readRPSBattlePt2(it) }

    println(battlesSum)
}

fun main(args: Array<String>) {
    partTwo()
}
