package ru.skillbranch.devintensive.utils

import ru.skillbranch.devintensive.models.User
import java.util.*

object Utils {
    fun parseFullName(fullName: String?): Pair<String?, String?> {
        val parts: List<String>? = fullName?.split(" ")
        val firstName = parts?.getOrNull(0)
        val lastName = parts?.getOrNull(1)

        return firstName to lastName
    }

    fun transliteration(payload: String, divider: String = " "): String {
        /* Определение словаря */
        val dictionary = hashMapOf(
            "а" to "a",
            "б" to "b",
            "в" to "v",
            "г" to "g",
            "д" to "d",
            "е" to "e",
            "ё" to "e",
            "ж" to "zh",
            "з" to "z",
            "и" to "i",
            "й" to "i",
            "к" to "k",
            "л" to "l",
            "м" to "m",
            "н" to "n",
            "о" to "o",
            "п" to "p",
            "р" to "r",
            "с" to "s",
            "т" to "t",
            "у" to "u",
            "ф" to "f",
            "х" to "h",
            "ц" to "c",
            "ч" to "ch",
            "ш" to "sh",
            "щ" to "sh'",
            "ъ" to "",
            "ы" to "i",
            "ь" to "",
            "э" to "e",
            "ю" to "yu",
            "я" to "ya"
        )

        return ""
    }

    /* Получение инициалов */
    fun toInitials(firstName: String?, lastName: String?): String? {
        var firstLetter:String? = null
        var secondLetter:String? = null

        /* Получение первых прописных первых символов */
        if(firstName != null && firstName != "" && firstName != " ")
            firstLetter = (firstName?.get(0).toUpperCase()).toString()

        if(lastName != null && lastName != "" && lastName != " ")
            secondLetter = (lastName?.get(0).toUpperCase()).toString()

        /* Проверка полученных инициалов */
        if(firstLetter != null && secondLetter == null)
            return firstLetter

        if(secondLetter != null && firstLetter == null)
            return secondLetter

        if(firstLetter == null && secondLetter == null) {
            return null
        }

        return "$firstLetter$secondLetter"
    }
}