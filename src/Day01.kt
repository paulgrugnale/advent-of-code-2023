fun main() {
    val wordsToNums =
        mapOf(
            "one" to "1", "1" to "1",
            "two" to "2", "2" to "2",
            "three" to "3", "3" to "3",
            "four" to "4", "4" to "4",
            "five" to "5", "5" to "5",
            "six" to "6", "6" to "6",
            "seven" to "7", "7" to "7",
            "eight" to "8", "8" to "8",
            "nine" to "9", "9" to "9",
        )

    fun part1(input: List<String>): Int {
        return input.sumOf { line ->
            line.filter {
                it.isDigit()
            }.let { digits ->
                (digits.first().toString() + digits.last()).toInt()
            }
        }
    }

    fun part2(input: List<String>): Int {
        return input.sumOf { line ->
            val first = line.findAnyOf(wordsToNums.keys)?.second ?: ""
            val last = line.findLastAnyOf(wordsToNums.keys)?.second ?: ""
            (wordsToNums.getOrDefault(first, "") + wordsToNums.getOrDefault(last, "")).toInt()
        }
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day01_test")
    check(part2(testInput) == 281)

    val input = readInput("Day01")
    part1(input).println()
    part2(input).println()
}
