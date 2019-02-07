package utils

import java.time.temporal.TemporalAmount
import java.util.*

class DateUtils {

    fun changeDate(date: Date, periodType: Int, periodAmount: Int): Date {
        val calendar = Calendar.getInstance()
        calendar.time = date
        calendar.add(periodType, periodAmount)
        return calendar.time
    }

}