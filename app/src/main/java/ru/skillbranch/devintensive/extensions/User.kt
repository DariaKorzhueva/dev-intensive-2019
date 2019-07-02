package ru.skillbranch.devintensive.extensions

import ru.skillbranch.devintensive.models.User
import ru.skillbranch.devintensive.models.UserView
import ru.skillbranch.devintensive.utils.Utils
import java.util.*
import java.util.concurrent.TimeUnit
import kotlin.math.abs

fun User.toUserView(): UserView {

    val nickname = Utils.transliteration("$firstName $lastName")
    val initials = Utils.toInitials(firstName, lastName)
    val status =
        if (lastVisit == null) "Ещё ни разу не был" else if (isOnline) "online" else "Последний раз был ${lastVisit.humanizeDiff()}"

    return UserView(
        id,
        fullName = "$firstName $lastName",
        nickname = nickname,
        initials = initials,
        avatar = avatar,
        status = status
    )
}

/* Склонение числительных */
fun generatePlurals(number: String, plural: TimeUnits): String {

    val lastSymbol: String = (number[number.length - 1]).toString()

    when (plural) {
        TimeUnits.MINUTE -> {
            if (number != "10" && number != "11" && number != "12" && number != "13" && number != "14" && number != "15"
                && number != "16" && number != "17" && number != "18" && number != "19"
            ) {
                when (lastSymbol) {
                    "1" -> return "минуту"
                    "2", "3", "4" -> return "минуты"
                    "5", "6", "7", "8", "9", "0" -> return "минут"
                    else -> return "минут"
                }
            } else
                return "минут"
        }
        TimeUnits.HOUR -> {
            if (number != "10" && number != "11" && number != "12" && number != "13" && number != "14" && number != "15"
                && number != "16" && number != "17" && number != "18" && number != "19"
            ) {
                when (lastSymbol) {
                    "1" -> return "час"
                    "2", "3", "4" -> return "часа"
                    "5", "6", "7", "8", "9", "0" -> return "часов"
                    else -> return "часов"
                }
            } else
                return "часов"
        }
        TimeUnits.DAY -> {
            if (number != "10" && number != "11" && number != "12" && number != "13" && number != "14" && number != "15"
                && number != "16" && number != "17" && number != "18" && number != "19"
            ) {
                when (lastSymbol) {
                    "1" -> return "день"
                    "2", "3", "4" -> return "дня"
                    "5", "6", "7", "8", "9", "0" -> return "дней"
                    else -> return "дней"
                }
            } else
                return "дней"
        }
    }

    return ""
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
            return "$minutes ${generatePlurals(minutes.toString(), TimeUnits.MINUTE)} назад"

        if (minutes in 45..75)
            return "час назад"

        if (minutes > 75 && hours < 22)
            return "$hours ${generatePlurals(hours.toString(), TimeUnits.HOUR)} назад"

        if (hours in 22..26)
            return "день назад"

        if (hours > 26 && days < 360)
            return "$days ${generatePlurals(days.toString(), TimeUnits.DAY)} назад"

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
            return "через $minutes ${generatePlurals(minutes.toString(), TimeUnits.MINUTE)}"

        if (minutes in 45..75)
            return "через час"

        if (minutes > 75 && hours < 22)
            return "через $hours ${generatePlurals(hours.toString(), TimeUnits.HOUR)}"

        if (hours in 22..26)
            return "через день"

        if (hours > 26 && days < 360)
            return "через $days ${generatePlurals(days.toString(), TimeUnits.DAY)}"

        if (days > 360)
            return "более чем через год"
    }


    return ""
}
