package ru.skillbranch.devintensive.utils

import android.content.Context
import android.util.TypedValue
import ru.skillbranch.devintensive.R

object Utils {
    fun parseFullName(fullName: String?): Pair<String?, String?> {
        if (fullName == null || fullName == "" || fullName == " ") {
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
            "Ж" to "Zh",
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
            "Ч" to "Ch",
            "Ш" to "Sh",
            "Щ" to "Sh'",
            "Ъ" to "",
            "Ы" to "I",
            "Ь" to "",
            "Э" to "E",
            "Ю" to "Yu",
            "Я" to "Ya",
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

    /* Валидация пути репозитория */
    fun isValidRepositoryPath(path: String): Boolean {
        if(path == "")
            return true

        val exceptions = listOf(
            "enterprise",
            "features",
            "topics",
            "collections",
            "trending",
            "events",
            "marketplace",
            "pricing",
            "nonprofit",
            "customer-stories",
            "security",
            "login",
            "join"
        )

        val links = listOf(
            "http://github.com/",
            "https://github.com/",
            "https://www.github.com/",
            "www.github.com/",
            "github.com/"
        )

        /* Обработка первых типов ссылок */
        if (path.startsWith(links[0]) || path.startsWith(links[1]) || path.startsWith(links[2])) {
            val subStr = path.split('/')

            if(subStr[3] == "" || subStr.size != 4)
                return false

            exceptions.forEach{
                if(subStr[3] == it)
                    return false
            }

            return true
        }

        /* Обработка вторых типов ссылок */
        if (path.startsWith(links[3]) || path.startsWith(links[4])) {
            val subStr = path.split('/')

            if(subStr[1] == "" || subStr.size != 2)
                return false

            exceptions.forEach{
                if(subStr[1] == it)
                    return false
            }

            return true
        }

        return false;
    }

    /* Получение цвета по атрибуту */
    fun getColorByAttr(context: Context, attr: Int): Int {
        val value = TypedValue()
        context.theme.resolveAttribute(attr, value, true)
        return value.data
    }
}