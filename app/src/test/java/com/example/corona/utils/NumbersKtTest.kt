package com.example.corona.utils

import junit.framework.TestCase
import org.junit.Test
import java.text.SimpleDateFormat
import java.util.*

@Suppress("DEPRECATION")
class NumbersKtTest : TestCase() {

    @Test
    fun testFormatNumber() {
        assertEquals("1", formatNumber(1))
        assertEquals("1 K", formatNumber(1000))
        assertEquals("1 M", formatNumber(1000000))
    }

    @Test
    fun testDistanceBetweenDates() {
        val format = SimpleDateFormat("MM/dd/yy")
        assertEquals(0f, distanceBetweenDates(format.format(Date())))
    }
}