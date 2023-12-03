enum class CubeColors(val displayName: String) {
    GREEN("green"), BLUE("blue"), RED("red")
}

fun main() {
    data class Game(val id: Int, val red: Int, val blue: Int, val green: Int)

    fun part1(input: List<String>): Int {
        // 12 red cubes, 13 green cubes, and 14 blue cubes
        val validCubeAmounts = mapOf(
            CubeColors.RED to 12,
            CubeColors.GREEN to 13,
            CubeColors.BLUE to 14,
        )

        val gameIds = (1..input.size).toMutableSet()

        val colorToCubeColors = CubeColors.entries.associateBy(CubeColors::displayName)

        input.forEach { line ->
            val (gameNumber, round) = line.split(":")
            val gameId = gameNumber.split(" ").last().toInt()

            round.split(";").forEach { game ->
                val colorsInGame = game.split(", ").map { cubes ->
                    val (amount, color) = cubes.trim().split(" ")
                    colorToCubeColors[color]!! to amount.toInt()
                }

                if (colorsInGame.sumOf { it.second } > validCubeAmounts.values.sum() || colorsInGame.any { it.second > validCubeAmounts[it.first]!! }) {
                    gameIds.remove(gameId)
                }
            }
        }
        return gameIds.sum()
    }

    fun part2(input: List<String>): Int {
        val colorToCubeColors = CubeColors.entries.associateBy(CubeColors::displayName)

        return input.sumOf { line ->
            val (_, round) = line.split(":")
            val pattern = "\\d+\\s\\w+"
            val regex = Regex(pattern)

            regex.findAll(round).map { match ->
                val (amount, color) = match.value.split(" ")
                colorToCubeColors[color]!! to amount.toInt()
            }.groupBy(
                { it.first },
                { it.second },
            ).mapValues {
                it.value.max()
            }.values.reduce { acc, i -> acc * i }
        }
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day02_test")
    check(part2(testInput) == 2286)

    val input = readInput("Day02")
    part1(input).println()
    part2(input).println()
}
