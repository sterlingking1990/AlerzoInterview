package com.project.alerzointerview.util

import com.google.common.truth.Truth.assertThat
import org.junit.Test

class ValidationTest {
    val validation=Validation()

    @Test
    fun testEmptyFirstNameOrLastNameReturnsFalse(){

        val resultWhenFirstOrLastNameEntered = validation.verifyNameIsValid("")

        assertThat(resultWhenFirstOrLastNameEntered).isFalse()
    }

    @Test
    fun testFirstNameOrLastNameProvidedReturnsTrue(){

        val resultWhenFirstOrLastNameValid = validation.verifyNameIsValid("kingsley")

        assertThat(resultWhenFirstOrLastNameValid).isTrue()
    }

    @Test
    fun testEmailWithoutDomainReturnsFalse() {
        val email="izundukingsley"
        val resultWhenEmailWithoutDomainProvided = validation.verifyEmailIsValid(email)

        assertThat(resultWhenEmailWithoutDomainProvided).isFalse()
    }


    @Test
    fun testEmailWithoutDotExtension() {
        val email="izundukingsley@gmail"
        val resultWhenEmailWithoutDomainProvided = validation.verifyEmailIsValid(email)

        assertThat(resultWhenEmailWithoutDomainProvided).isFalse()
        //asser(false) { Validation().verifyPasswordIsValid(password) }
    }

    @Test
    fun testValidEmailReturnsTrue() {
        val email="izundukingsley@gmail.com"
        val resultWhenEmailIsValid = validation.verifyEmailIsValid(email)

        assertThat(resultWhenEmailIsValid).isTrue()

    }

    @Test
    fun passwordIsLessThan6ReturnsFalse(){
        val password = "12sd"
        val resultWhenEmailIsInValid = validation.verifyPasswordIsValid(password)

        assertThat(resultWhenEmailIsInValid).isFalse()
    }

    @Test
    fun passwordIsGreaterThan6ValidReturnsTrue(){
        val password = "king123"
        val resultWhenEmailIsValid = validation.verifyPasswordIsValid(password)

        assertThat(resultWhenEmailIsValid).isTrue()
    }


}