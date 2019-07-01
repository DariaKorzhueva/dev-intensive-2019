package ru.skillbranch.devintensive.utils

import ru.skillbranch.devintensive.models.User
import java.util.*

object Utils {
    fun parseFullName(fullName: String?): Pair<String?, String?> {
        if(fullName == null || fullName == "" || fullName == " ") {
            return null to null
        }

        val parts: List<String>? = fullName?.split(" ")
        val firstName = parts?.getOrNull(0)
        val lastName = parts?.getOrNull(1)

        return firstName to lastName
    }

    /* Преобразование в латиницу */
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
            "я" to "ya",
            "А" to "A",
            "Б" to "B",
            "В" to "V",
            "Г" to "G",
            "Д" to "D",
            "Е" to "E",
            "Ё" to "E",
            "Ж" to "ZH",
            "З" to "Z",
            "И" to "I",
            "Й" to "I",
            "К" to "K",
            "Л" to "L",
            "М" to "M",
            "Н" to "N",
            "О" to "O",
            "П" to "P",
            "Р" to "R",
            "С" to "S",
            "Т" to "T",
            "У" to "U",
            "Ф" to "F",
            "Х" to "H",
            "Ц" to "C",
            "Ч" to "CH",
            "Ш" to "SH",
            "Щ" to "SH'",
            "Ъ" to "",
            "Ы" to "I",
            "Ь" to "",
            "Э" to "E",
            "Ю" to "Yu",
            "Я" to "YA",
            " " to divider
        )

        var result: String = ""
        var elem: String?

        /* Перебор элементов payload
        * При отсутсвии рассматриваемого символа в слове
        * Он остаётся без изменений*/
        for (char in payload) {
            elem = dictionary.get(char.toString())
            if (elem != null) {
                result += elem
            } else {
                result += char.toString()
            }
        }

        return result
    }

    /* Получение инициалов */
    fun toInitials(firstName: String?, lastName: String?): String? {
        var firstLetter: String? = null
        var secondLetter: String? = null

        /* Получение первых прописных первых символов */
        if (firstName != null && firstName != "" && firstName != " ")
            firstLetter = (firstName?.get(0).toUpperCase()).toString()

        if (lastName != null && lastName != "" && lastName != " ")
            secondLetter = (lastName?.get(0).toUpperCase()).toString()

        /* Проверка полученных инициалов */
        if (firstLetter != null && secondLetter == null)
            return firstLetter

        if (secondLetter != null && firstLetter == null)
            return secondLetter

        if (firstLetter == null && secondLetter == null) {
            return null
        }

        return "$firstLetter$secondLetter"
    }
}