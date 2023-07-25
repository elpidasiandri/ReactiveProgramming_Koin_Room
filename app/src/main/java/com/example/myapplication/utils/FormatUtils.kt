package com.example.myapplication.utils

object FormatUtils {

    fun formatNumberForViewing(num: Long): String {
        val numToString = num.toString()
        return when (num) {
            in 0..999 -> numToString
            in 1000..9999 -> {
                checkFirstDecimal(numToString, 1)
            }

            in 10000..99999 -> {
                checkFirstDecimal(numToString, 2)
            }

            in 100000..999999 -> {
                checkFirstDecimal(numToString, 3)
            }

            else -> {
                val millionString = numToString.dropLast(6)
                millionString + "M"
            }
        }
    }

    private fun checkFirstDecimal(numToString: String, digitToCheck: Int): String {
        val tenThousands = numToString.dropLast(3)
        return if (numToString[digitToCheck].toString() !== "0") {
            "$tenThousands.${numToString[digitToCheck]}K"
        } else {
            "${tenThousands}K"
        }
    }

    fun roundDoubleToTwoDecimal(amountToRound: Double): Double {
        val splittedText = amountToRound.toString().split(".")
        return if (splittedText.isNotEmpty()) {
            val afterPoint = if (splittedText[1].length > 3) {
                splittedText[1].substring(0..1)
            } else {
                splittedText[1]
            }
            if (afterPoint.contains("E")) {
                "${splittedText[0]}.00".toDouble()
            } else {
                "${splittedText[0]}.$afterPoint".toDouble()
            }
        } else {
            amountToRound
        }
    }
}