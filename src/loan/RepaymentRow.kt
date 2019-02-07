package loan

import java.math.BigDecimal
import java.util.*

data class RepaymentRow(
        var repaymentDate: Date? = null,
        var principalAmount: BigDecimal? = null,
        var interestAmount: BigDecimal? = null,
        var repaymentAmount: BigDecimal? = null,
        var remainingAmount: BigDecimal? = null
)