package com.example.util

import androidx.compose.runtime.Stable
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation


@Stable
class PhoneVisualTransformation(
    private val phoneMask: PhoneMask
): VisualTransformation {
    private val maxLength = phoneMask.mask.count { it == phoneMask.maskNum }

    override fun filter(text: AnnotatedString): TransformedText {
        val trimmed = if(text.length > maxLength) text.take(maxLength) else text

        val annotatedString = buildAnnotatedString {
            if(trimmed.isEmpty()) return@buildAnnotatedString

            var maskIndex = 0
            var textIndex = 0
            with(phoneMask) {
                while (textIndex < trimmed.length && maskIndex < mask.length) {

                    if(maskIndex == 0 && textIndex == 0) {
                        if(trimmed[0] == '7') {
                            append('+')
                        }
                    }


                    if (mask[maskIndex] != maskNum) {
                        val nextDigitIndex = mask.indexOf(maskNum, maskIndex)
                        append(mask.substring(maskIndex, nextDigitIndex))
                        maskIndex = nextDigitIndex
                    }
                    append(trimmed[textIndex++])
                    maskIndex++
                }
            }
        }
        return TransformedText(
            text = annotatedString,
            offsetMapping = PhoneOffsetMapper(
                phoneMask =
                if(annotatedString.contains('+')) PhoneMask(mask = "+0 000 000-00-00")
                else phoneMask
            )
        )


    }
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is PhoneVisualTransformation) return false
        if (phoneMask.mask != other.phoneMask.mask) return false
        return phoneMask.maskNum == other.phoneMask.maskNum
    }

    override fun hashCode(): Int {
        return phoneMask.hashCode()
    }

    @Stable
    private class PhoneOffsetMapper(val phoneMask: PhoneMask): OffsetMapping {
        override fun originalToTransformed(offset: Int): Int {
            var noneDigitCount = 0
            var i = 0
            while (i < offset + noneDigitCount) {
                if (phoneMask.mask[i++] != phoneMask.maskNum) noneDigitCount++
            }
            return offset + noneDigitCount        }
        override fun transformedToOriginal(offset: Int): Int {
            return offset - phoneMask.mask.take(offset).count { it != phoneMask.maskNum }
        }

    }

}

@Stable
data class PhoneMask(
    val mask: String = "0 000 000-00-00",
    val maskNum: Char = '0'
)
