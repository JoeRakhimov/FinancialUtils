package loan

import java.math.BigDecimal
import utils.DateUtils
import java.math.MathContext
import java.math.RoundingMode

class LoanCalculator {

    val mDateUtils = DateUtils()

    fun calculateRepayments(loan: Loan): MutableList<RepaymentRow> {

        val rows = mutableListOf<RepaymentRow>()
        var remainingAmount = loan.amount
        var repaymentDate = loan.startingDate

        for (i in 0 until loan.numberOfPeriods) {

            var principalAmount = BigDecimal(0)
            var interestAmount = remainingAmount.multiply(loan.getPeriodInterestRate())
            var repaymentAmount = BigDecimal(0)

            when (loan.type) {
                LoanType.LOAN_TYPE_ANNUITY -> {
                    repaymentAmount = if (i < loan.numberOfGracePeriods) interestAmount else getRepaymentAmountForAnnuityType(loan)
                    principalAmount = repaymentAmount.minus(interestAmount)
                }
                LoanType.LOAN_TYPE_DEFERRED -> {
                    principalAmount = if (i < loan.numberOfGracePeriods) BigDecimal(0) else getPrincipalAmountForDeferredType(loan)
                    repaymentAmount = principalAmount.plus(interestAmount)
                }
            }

            remainingAmount = remainingAmount.minus(principalAmount)

            rows.add(
                    RepaymentRow(
                            repaymentDate = repaymentDate,
                            principalAmount = principalAmount.setScale(2, RoundingMode.HALF_UP),
                            interestAmount = interestAmount.setScale(2, RoundingMode.HALF_UP),
                            repaymentAmount = repaymentAmount.setScale(2, RoundingMode.HALF_UP),
                            remainingAmount = remainingAmount.setScale(2, RoundingMode.HALF_UP)
                    )
            )

            repaymentDate = mDateUtils.changeDate(repaymentDate, loan.periodType, loan.periodTypeAmountInOnePeriod)

        }

        return rows

    }


    private fun getRepaymentAmountForAnnuityType(loan: Loan): BigDecimal {

        /*
            Annuity payment formula source: http://financeformulas.net/Annuity_Payment_Formula.html

            P - payment or in this case repaymentAmount
            r - rate per numberOfPeriods
            pv - present value
            n - number of periods

            P = r * PV / (1 - (1 + r)^(-n))

            divider = (1 - (1 + r)^(-n))

         */

        val ratePerPeriod = loan.getPeriodInterestRate()
        val presentValue = loan.amount
        val numberOfPeriods = loan.numberOfPeriods - loan.numberOfGracePeriods
        val divider = BigDecimal(1).minus(BigDecimal(1).plus(ratePerPeriod).pow(-1 * numberOfPeriods, MathContext(10)))
        var repaymentAmount = ratePerPeriod.multiply(presentValue).divide(divider, 10, RoundingMode.HALF_UP)
        return repaymentAmount

    }

    private fun getPrincipalAmountForDeferredType(loan: Loan) = loan.amount.divide(BigDecimal(loan.numberOfPeriods - loan.numberOfGracePeriods), 10, RoundingMode.HALF_UP)

}