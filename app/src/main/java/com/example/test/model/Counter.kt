package com.example.test.model

class Counter {
    var number: Int = INIT_VALUE
        private set

    fun minus(): Boolean {
        if (isMin()) return false
        number--
        return true
    }

    fun plus(): Boolean {
        if (isMax()) return false
        number++
        return true
    }

    private fun isMin(): Boolean {
        return number == MIN_VALUE
    }

    private fun isMax(): Boolean {
        return number == MAX_VALUE
    }

    companion object {
        private const val INIT_VALUE = 3
        private const val MIN_VALUE = 1
        private const val MAX_VALUE = 5
    }
}
