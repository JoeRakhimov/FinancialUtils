package loan

import java.math.BigDecimal
import java.util.*


data class Loan(
        var type: LoanType,
        var amount: BigDecimal,
        var startingDate: Date,
        var numberOfPeriods: Int,
        var numberOfGracePeriods: Int,
        var periodTypeAmountInOnePeriod: Int,
        var periodType: Int,
        var annualInterestRateInPercent: BigDecimal
){

    fun getPeriodInterestRate(): BigDecimal {
        return when(periodType){
            Calendar.DAY_OF_YEAR -> annualInterestRateInPercent.divide(BigDecimal(365)).divide(BigDecimal(100))
            Calendar.MONTH -> annualInterestRateInPercent.divide(BigDecimal(12)).divide(BigDecimal(100))
            Calendar.YEAR -> annualInterestRateInPercent.divide(BigDecimal(100))
            else -> annualInterestRateInPercent.divide(BigDecimal(100))
        }
    }

}