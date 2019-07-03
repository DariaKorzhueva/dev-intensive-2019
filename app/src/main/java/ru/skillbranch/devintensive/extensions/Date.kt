package ru.skillbranch.devintensive.extensions

import java.lang.IllegalStateException
import java.text.SimpleDateFormat
import java.util.*

const val SECOND = 1000L
const val MINUTE = 60 * SECOND
const val HOUR = 60 * MINUTE
const val DAY = 24 * HOUR

/* Форматирование даты в формате час:минута:секунда день.месяц.год */
fun Date.format(pattern: String = "HH:mm:ss dd.MM.yy"): String {
    val dateFormat = SimpleDateFormat(pattern, Locale("ru"))
    return dateFormat.format(this)
}

/* Добавление сдвига по времени */
fun Date.add(value: Int, units: TimeUnits): Date {
    var time = this.time

    time += when (units) {
        TimeUnits.SECOND -> value * SECOND
        TimeUnits.MINUTE -> value * MINUTE
        TimeUnits.HOUR -> value * HOUR
        TimeUnits.DAY -> value * DAY
    }

    this.time = time

    return this
}

enum class TimeUnits {
    SECOND,
    MINUTE,
    HOUR,
    DAY;
    fun plural(number: Int): String {

        val strNumber = number.toString()
        val lastSymbol: String = (strNumber[strNumber.length - 1]).toString()

        when (this) {
            TimeUnits.SECOND -> {
                if (strNumber != "10" && strNumber != "11" && strNumber != "12" && strNumber != "13" && strNumber != "14" && strNumber != "15"
                    && strNumber != "16" && strNumber != "17" && strNumber != "18" && strNumber != "19"
                ) {
                    when (lastSymbol) {
                        "1" -> return "$strNumber секунду"
                        "2", "3", "4" -> return "$strNumber секунды"
                        "5", "6", "7", "8", "9", "0" -> return "$strNumber секунд"
                        else -> return "$strNumber секунд"
                    }
                } else
                    return "$strNumber минут"
            }
            TimeUnits.MINUTE -> {
                if (strNumber != "10" && strNumber != "11" && strNumber != "12" && strNumber != "13" && strNumber != "14" && strNumber != "15"
                    && strNumber != "16" && strNumber != "17" && strNumber != "18" && strNumber != "19"
                ) {
                    when (lastSymbol) {
                        "1" -> return "$strNumber минуту"
                        "2", "3", "4" -> return "$strNumber минуты"
                        "5", "6", "7", "8", "9", "0" -> return "$strNumber минут"
                        else -> return "$strNumber минут"
                    }
                } else
                    return "$strNumber минут"
            }
            TimeUnits.HOUR -> {
                if (strNumber != "10" && strNumber != "11" && strNumber != "12" && strNumber != "13" && strNumber != "14" && strNumber != "15"
                    && strNumber != "16" && strNumber != "17" && strNumber != "18" && strNumber != "19"
                ) {
                    when (lastSymbol) {
                        "1" -> return "$strNumber час"
                        "2", "3", "4" -> return "$strNumber часа"
                        "5", "6", "7", "8", "9", "0" -> return "$strNumber часов"
                        else -> return "$strNumber часов"
                    }
                } else
                    return "$strNumber часов"
            }
            TimeUnits.DAY -> {
                if (strNumber != "10" && strNumber != "11" && strNumber != "12" && strNumber != "13" && strNumber != "14" && strNumber != "15"
                    && strNumber != "16" && strNumber != "17" && strNumber != "18" && strNumber != "19"
                ) {
                    when (lastSymbol) {
                        "1" -> return "$strNumber день"
                        "2", "3", "4" -> return "$strNumber дня"
                        "5", "6", "7", "8", "9", "0" -> return "$strNumber дней"
                        else -> return "$strNumber дней"
                    }
                } else
                    return "$strNumber дней"
            }
        }

        return ""
    }
}

/* форматирование вывода разницы между датами в человекообразном формате */
fun Date.humanizeDiff(date: Date = Date()): String {
    var delta = this.time - date.time
    delta = delta / 1000 * 1000

    var past = false
    if (delta < 0) {
        past = true
        delta = -delta
    }

    if (past) {
        val seconds = delta / 1000
        val minutes = delta / (60 * 1000)
        val hours = delta / (3600 * 1000)
        val days = delta / (24 * 3600 * 1000)

        if (seconds < 1)
            return "только что"

        if (seconds in 1..45)
            return "несколько секунд назад"

        if (seconds in 45..75)
            return "минуту назад"

        if (seconds > 75 && minutes < 45)
            return "${TimeUnits.MINUTE.plural(minutes.toInt())} назад"

        if (minutes in 45..75)
            return "час назад"

        if (minutes > 75 && hours < 22)
            return "${TimeUnits.HOUR.plural(hours.toInt())} назад"

        if (hours in 22..26)
            return "день назад"

        if (hours > 26 && days < 360)
            return "${TimeUnits.DAY.plural(days.toInt())} назад"

        if (days > 360)
            return "более года назад"
    } else {
        val seconds = delta / 1000
        val minutes = delta / (60 * 1000)
        val hours = delta / (3600 * 1000)
        val days = delta / (24 * 3600 * 1000)

        if (seconds < 1)
            return "только что"

        if (seconds in 1..45)
            return "через несколько секунд"

        if (seconds in 45..75)
            return "через минуту"

        if (seconds > 75 && minutes < 45)
            return "через ${TimeUnits.MINUTE.plural(minutes.toInt())}"

        if (minutes in 45..75)
            return "через час"

        if (minutes > 75 && hours < 22)
            return "через ${TimeUnits.HOUR.plural(hours.toInt())}"

        if (hours in 22..26)
            return "через день"

        if (hours > 26 && days < 360)
            return "через ${TimeUnits.DAY.plural(days.toInt())}"

        if (days > 360)
            return "более чем через год"
    }


    return ""
}
