package com.example.util.extension

import android.telephony.PhoneNumberUtils
import androidx.compose.ui.unit.Dp

fun String.isPhone(): Boolean {
    return PhoneNumberUtils.isGlobalPhoneNumber(this) && this.length == 10
}

fun String.withUnit(unit: String): String {
    return "$this $unit"
}

fun String.withPhoneMask(): String {
    val regex = """(\d)(\d{3})(\d{3})(\d{2})(\d{2})""".toRegex()
    return regex.replace(this,"+$1 $2 $3-$4-$5")
}

/*
* Нужно чтобы симулировать подсчет переполнения текста
* так как я не нашел способа сделать это иначе
* */
fun String.isOverflowed(
    fontSize: Dp
): Boolean {
    return (this.length * fontSize.value.toInt()) > 125 * fontSize.value
}