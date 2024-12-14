package me.anno.aoc24.day14

import me.anno.aoc23.day10.directions
import me.anno.utils.Utils.readLines
import me.anno.utils.Vector2i

// WTF is a Christmas tree? -> I looked it up on Reddit, but I really didn't know what to expect
// the solution is my own approach though
fun main() {
    val robots = readLines(24, 14, "data.txt")
        .map { line -> parseRobot(line) }
    val spaceSize = Vector2i(101, 103)
    var time = 0
    while (true) {
        if (isChristmasTree(robots, time, spaceSize)) {
            printRobotMap(robots, time, spaceSize)
            break
        }
        time++
    }
}

val directions1 = directions

fun isChristmasTree(robots: List<Robot>, time: Int, spaceSize: Vector2i): Boolean {
    // it is a Christmas tree, if many robots have a neighbor
    val minNeighbors = robots.size * 2
    val positions = robots.map { it.getPositionAt(time, spaceSize) }
    val map = positions.toHashSet()
    val numNeighbors = positions.sumOf { pos ->
        directions1.count { dir ->
            pos + dir in map
        }
    }
    return numNeighbors >= minNeighbors
}