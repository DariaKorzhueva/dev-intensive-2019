package ru.skillbranch.devintensive.extensions

/* Усечение строки */
fun String.truncate(number: Int = 16): String {

    /* Удаление лишних пробелов в конце строки*/
    var truncString = this.trim(' ')

    /* Если длина строки без пробелов меньше количества убираемых символов */
    if (truncString.length < number) {
        /* Если последний символ - пробел, он удаляется */
        if (this[this.length - 1].toString() == " ")
            return this.trim(' ')
        else
            return this
    }

    /* Удаление последних символов, не являющихся пробелами */
    truncString = this.dropLast(this.length - number)

    /* Если последний символ после усечения - пробел, то он удаляется*/
    if (this[number - 1].toString() == " ") {
        truncString = truncString.trim(' ')
    }

    truncString = truncString.padEnd(truncString.length + 3, '.')

    return truncString
}

/* Очищение строки от html-разметки */
fun String.stripHtml(): String {

    var stringLength = this.length
    var newString: String = ""
    var i = 0

    /* Цикл пока не просмотрена вся строка */
    while (stringLength > i) {
        /* Если встреченный символ - < */
        if (this[i] == '<') {
            /* Цикл пока не найдена закрывающая >*/
            while (this[i] != '>' && stringLength > i) {
                i++
            }
            i++
        } else {
            newString += this[i]
            i++
        }
    }

    newString = newString.replace("\\s+".toRegex(), " ").replace("[&<>'/\"]".toRegex(), " ")

    return newString
}