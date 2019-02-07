# FinancialUtils
This library helps to calculate loan repayments

Let's say we have loan with following parameters:
  - Calculation type: Annuity
  - Amount: 1 000 000
  - Starting date: Today
  - Number of periods: 18
  - Number of grace periods: 3
  - Period type: MONTH. This means grace period is 3 months and loan period is 18 months.
  - Period type amount: 1. This means that each repayment period is 1 month
  - Annual interest rate: 24% annually

You can find this code in Test.kt file

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
    
Result:

                Date    Principal amount     Interest amount    Repayment amount    Remaining amount
          2019-02-07                0.00            20000.00            20000.00          1000000.00
          2019-03-07                0.00            20000.00            20000.00          1000000.00
          2019-04-07                0.00            20000.00            20000.00          1000000.00
          2019-05-07            57825.47            20000.00            77825.47           942174.53
          2019-06-07            58981.98            18843.49            77825.47           883192.55
          2019-07-07            60161.62            17663.85            77825.47           823030.92
          2019-08-07            61364.85            16460.62            77825.47           761666.07
          2019-09-07            62592.15            15233.32            77825.47           699073.92
          2019-10-07            63843.99            13981.48            77825.47           635229.93
          2019-11-07            65120.87            12704.60            77825.47           570109.05
          2019-12-07            66423.29            11402.18            77825.47           503685.76
          2020-01-07            67751.76            10073.72            77825.47           435934.00
          2020-02-07            69106.79             8718.68            77825.47           366827.21
          2020-03-07            70488.93             7336.54            77825.47           296338.28
          2020-04-07            71898.71             5926.77            77825.47           224439.58
          2020-05-07            73336.68             4488.79            77825.47           151102.90
          2020-06-07            74803.41             3022.06            77825.47            76299.48
          2020-07-07            76299.48             1525.99            77825.47                0.00
