package com.example.soapshop.util.extension

import android.telephony.PhoneNumberUtils

fun String.isPhone(): Boolean {
    return PhoneNumberUtils.isGlobalPhoneNumber(this) && this.length == 10
}