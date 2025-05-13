package com.iamnazmul.tmdbmovie.domain.base

import android.text.TextUtils
import android.util.Patterns
import org.json.JSONArray
import java.util.regex.Matcher
import java.util.regex.Pattern
import javax.inject.Inject


class IoValidation @Inject constructor() {


    private fun String.isPhoneNumberValid(): Boolean {
        val pattern: Pattern =
            Pattern.compile("((0|01|\\+88|\\+88\\s*\\(0\\)|\\+88\\s*0)\\s*)?1(\\s*[0-9]){9}")
        val matcher: Matcher = pattern.matcher(this)
        return matcher.matches()
    }

    private fun String.isPasswordValid(): Boolean {
        return this.isNotEmpty() && this.length >= 4
    }

    private fun String.isNameValid(): Boolean {
        return this.isNotEmpty() && this.length >= 5
    }

    private fun String.isEmailValid(): Boolean {
        return !TextUtils.isEmpty(this) && Patterns.EMAIL_ADDRESS.matcher(this).matches()
    }

    private fun String.isInvalidJson(): Boolean {
        return try {
            JSONArray(this).length() == 0
        } catch (ex: Exception) {
            return true
        }
    }

    fun String.convertToInt(): Int {
        return try {
            this.toInt()
        } catch (ex: Exception) {
            0
        }
    }

    private fun String.convertToDouble(): Double {
        return try {
            this.toDouble()
        } catch (ex: Exception) {
            0.0
        }
    }
}