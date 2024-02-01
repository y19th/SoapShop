package com.example.soapshop.util.extension

import android.telephony.PhoneNumberUtils
import androidx.compose.ui.unit.Dp

fun String.isPhone(): Boolean {
    return PhoneNumberUtils.isGlobalPhoneNumber(this) && this.length == 10
}

fun String.withUnit(unit: String): String {
    return "$this $unit"
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