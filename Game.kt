import kotlin.math.floor
import kotlin.random.Random

const val TIMESTAMPS_COUNT = 50000
const val PROBABILITY_SCORE_CHANGED = 0.0001
const val PROBABILITY_HOME_SCORE = 0.45
const val OFFSET_MAX_STEP = 3

data class Score(val home: Int, val away: Int)
data class Stamp(val offset: Int, val score: Score)

fun main() {
    val gameStamps = generateGame()
    val offset = 100000000 // For example, we are looking for an account at this offset
    val score = getScore(gameStamps, offset)
    println("Счет на смещении $offset: Home ${score.home}, Away ${score.away}")
}


fun generateGame(): Array<Stamp> {
    val stamps = Array(TIMESTAMPS_COUNT) { _ -> Stamp(0, Score(0, 0)) }
    var currentStamp = stamps[0]
    for (i in 1 until TIMESTAMPS_COUNT) {
        currentStamp = generateStamp(currentStamp)
        stamps[i] = currentStamp
    }
    return stamps
}

fun generateStamp(previousValue: Stamp): Stamp {
    val scoreChanged = Random.nextFloat() > 1 - PROBABILITY_SCORE_CHANGED
    val homeScoreChange = if (scoreChanged && Random.nextFloat() > 1 - PROBABILITY_HOME_SCORE) 1 else 0
    val awayScoreChange = if (scoreChanged && homeScoreChange == 0) 1 else 0
    val offsetChange = (floor(Random.nextFloat() * OFFSET_MAX_STEP) + 1).toInt()

    return Stamp(
        previousValue.offset + offsetChange,
        Score(
            previousValue.score.home + homeScoreChange,
            previousValue.score.away + awayScoreChange
        )
    )
}

fun getScore(gameStamps: Array<Stamp>, offset: Int): Score {
    // Edge case: if offset is less than the first stamp's offset, return the initial score
    if (offset < gameStamps[0].offset) {
        return gameStamps[0].score
    }

    // Edge case: if offset is greater than or equal to the last stamp's offset, return the last score
    if (offset >= gameStamps.last().offset) {
        return gameStamps.last().score
    }

    // Binary search to find the appropriate stamp
    var left = 0
    var right = gameStamps.size - 1

    while (left <= right) {
        val mid = left + (right - left) / 2

        if (gameStamps[mid].offset == offset) {
            return gameStamps[mid].score
        } else if (gameStamps[mid].offset < offset) {
            left = mid + 1
        } else {
            right = mid - 1
        }
    }

    // At the end of the loop, right will point to the largest index such that gameStamps[right].offset <= offset
    return gameStamps[right].score
}




