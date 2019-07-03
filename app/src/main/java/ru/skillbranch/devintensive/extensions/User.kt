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

/* Усечение строки */
fun String.truncate(number: Int = 16): String {

    /* Если длина строки меньше количества убираемых символов */
    if (this.length - number < 0) {
        /* Если последний символ - пробел, он удаляется */
        if (this[this.length - 1].toString() == " ")
            return this.trim()
        else
            return this
    }

    var truncString = this.dropLast(this.length - number)

    var i = 3

    /* Если последний символ - пробел, он удаляется*/
    if (this[number - 1].toString() == " ") {
        truncString = truncString.trim()
        i--
    }

    truncString = truncString.padEnd(number + i, '.')

    return truncString
}

/* Очищение строки от html-разметки */
fun String.stripHtml(): String {

    return ""
}