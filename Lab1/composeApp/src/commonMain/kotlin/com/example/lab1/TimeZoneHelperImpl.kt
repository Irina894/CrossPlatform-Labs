package com.example.lab1

import co.touchlab.kermit.Logger
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toInstant
import kotlinx.datetime.toLocalDateTime
import kotlin.math.abs
import kotlin.math.max
import kotlin.math.min

class TimeZoneHelperImpl : TimeZoneHelper {

    override fun getTimeZoneStrings(): List<String> {
        return TimeZone.availableZoneIds.sorted()
    }

    override fun currentTime(): String {
        // Використовуємо повний шлях, щоб уникнути конфлікту з kotlin.time.Clock
        val currentMoment = kotlinx.datetime.Clock.System.now()
        val dateTime: LocalDateTime = currentMoment.toLocalDateTime(TimeZone.currentSystemDefault())
        return formatDateTime(dateTime)
    }

    override fun currentTimeZone(): String {
        return TimeZone.currentSystemDefault().toString()
    }

    override fun hoursFromTimeZone(otherTimeZoneId: String): Double {
        val currentTimeZone = TimeZone.currentSystemDefault()
        val now = kotlinx.datetime.Clock.System.now()
        val otherTimeZone = TimeZone.of(otherTimeZoneId)

        val currentDateTime = now.toLocalDateTime(currentTimeZone)
        val currentOtherDateTime = now.toLocalDateTime(otherTimeZone)

        return abs((currentDateTime.hour - currentOtherDateTime.hour).toDouble())
    }

    override fun getTime(timezoneId: String): String {
        val timezone = TimeZone.of(timezoneId)
        val now = kotlinx.datetime.Clock.System.now()
        val dateTime = now.toLocalDateTime(timezone)
        return formatDateTime(dateTime)
    }

    override fun getDate(timezoneId: String): String {
        val timezone = TimeZone.of(timezoneId)
        val now = kotlinx.datetime.Clock.System.now()
        val dateTime = now.toLocalDateTime(timezone)

        val dayName = dateTime.dayOfWeek.name.lowercase().replaceFirstChar { it.uppercase() }
        val monthName = dateTime.month.name.lowercase().replaceFirstChar { it.uppercase() }

        return "$dayName, $monthName ${dateTime.dayOfMonth}"
    }

    override fun search(
        startHour: Int,
        endHour: Int,
        timezoneStrings: List<String>
    ): List<Int> {
        val goodHours = mutableListOf<Int>()
        val timeRange = IntRange(max(0, startHour), min(23, endHour))
        val currentTimeZone = TimeZone.currentSystemDefault()

        for (hour in timeRange) {
            var isGoodHour = false
            for (zone in timezoneStrings) {
                val timezone = TimeZone.of(zone)
                if (timezone == currentTimeZone) continue

                if (!isValid(timeRange, hour, currentTimeZone, timezone)) {
                    isGoodHour = false
                    break
                } else {
                    isGoodHour = true
                }
            }
            if (isGoodHour) {
                goodHours.add(hour)
            }
        }
        return goodHours
    }

    private fun formatDateTime(dateTime: LocalDateTime): String {
        val minute = dateTime.minute.toString().padStart(2, '0')
        var hour = dateTime.hour % 12
        if (hour == 0) hour = 12
        val amPm = if (dateTime.hour < 12) " am" else " pm"
        return "$hour:$minute$amPm"
    }

    private fun isValid(
        timeRange: IntRange,
        hour: Int,
        currentTimeZone: TimeZone,
        otherTimeZone: TimeZone
    ): Boolean {
        val now = kotlinx.datetime.Clock.System.now()
        val currentOtherDateTime = now.toLocalDateTime(otherTimeZone)

        val otherDateTimeWithHour = LocalDateTime(
            currentOtherDateTime.year,
            currentOtherDateTime.monthNumber,
            currentOtherDateTime.dayOfMonth,
            hour, 0, 0, 0
        )

        val localInstant = otherDateTimeWithHour.toInstant(currentTimeZone)
        val convertedTime = localInstant.toLocalDateTime(otherTimeZone)

        return convertedTime.hour in timeRange
    }
}