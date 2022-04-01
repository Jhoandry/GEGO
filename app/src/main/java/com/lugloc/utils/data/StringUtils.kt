package com.lugloc.utils.data

object StringUtils {
    const val EMPTY_STRING: String = ""
    private const val EMAIL_REGEX = "(?:[a-z0-9!#\$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#\$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])"
    private const val UPPER_CASE_REGEX = ".*[A-Z].*"
    private const val DIGIT_REGEX = ".*\\d.*"

    fun isValidEmail(email: String): Boolean = !email.isNullOrEmpty() && email.matches(EMAIL_REGEX.toRegex())

    fun isValidPassword(password: String): Boolean =
        !password.isNullOrEmpty() &&
        password.count() >= 6 &&
        DIGIT_REGEX.toRegex().containsMatchIn(password) &&
        UPPER_CASE_REGEX.toRegex().containsMatchIn(password)

    fun isValidPhoneCode(code: String): Boolean = !code.isNullOrEmpty() && code.length == 6

    fun isValidName(text: String): Boolean = !text.isNullOrEmpty() && text.length > 3 && text.length <= 30

    fun concat(arguments: List<String>, separator: String): String = arguments.joinToString(separator)

    fun creditCardNumber(lastDigits: String): String = java.lang.String.format("•••• •••• •••• %s", lastDigits)
}
