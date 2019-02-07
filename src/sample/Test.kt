package sample

import loan.Loan
import loan.LoanType
import java.math.BigDecimal
import java.text.SimpleDateFormat
import loan.LoanCalculator
import java.util.*
import loan.RepaymentRow

fun main(args: Array<String>) {

    testAnnuityRepaymentCalculation()

    println()

    testDeferredRepaymentCalculation()

}

private fun testAnnuityRepaymentCalculation() {

    val loan = Loan(
            type = LoanType.LOAN_TYPE_ANNUITY,
            amount = BigDecimal(1000000),
            startingDate = Date(),
            numberOfPeriods = 18,
            numberOfGracePeriods = 3,
            periodType = Calendar.MONTH,
            periodTypeAmountInOnePeriod = 1,
            annualInterestRateInPercent = BigDecimal(24)
    )

    val rows = LoanCalculator().calculateRepayments(loan)
    printRows(rows)

}

private fun testDeferredRepaymentCalculation() {

    val loan = Loan(
            type = LoanType.LOAN_TYPE_DEFERRED,
            amount = BigDecimal(1000000),
            startingDate = Date(),
            numberOfPeriods = 18,
            numberOfGracePeriods = 3,
            periodType = Calendar.MONTH,
            periodTypeAmountInOnePeriod = 1,
            annualInterestRateInPercent = BigDecimal(24)
    )

    val rows = LoanCalculator().calculateRepayments(loan)
    printRows(rows)

}

private fun printRows(rows: MutableList<RepaymentRow>) {
    var tableFormat = "%20s%20s%20s%20s%20s"
    println(String.format(tableFormat, "Date", "Principal amount", "Interest amount", "Repayment amount", "Remaining amount"))
    for (row in rows) {
        println(String.format(tableFormat, SimpleDateFormat("yyyy-MM-dd").format(row.repaymentDate?.time), row.principalAmount, row.interestAmount, row.repaymentAmount, row.remainingAmount))
    }
}

