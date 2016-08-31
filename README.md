# PersonalCapital

This project solves the coding challenge given by Personal Capital.
It performs a Monte Carlo simulation of 10,000 portfolios with two different expected returns and risks.
One portfolio type is aggressive, and the other is very conservative.
It then shows the inflation-adjusted median portfolio value, the top 10% portfolio value, and the bottom 10% portfolio value.
An additional test is performed, with a portfolio whose return tracks the inflation exactly. It should show that the inflation-adjusted value is the same as the initial value.

An example output is as follows:

Aggressive: Expected annual return = 0.094324, Expected annual risk = 0.15675: Inflation Adjusted Median value = 248044.42, Inflation Adjusted Best 10% value = 566734.43, Inflation Adjusted Worst 10% value = 105257.77
Very conservative: Expected annual return = 0.06189, Expected annual risk = 0.063438: Inflation Adjusted Median value = 161481.59, Inflation Adjusted Best 10% value = 227619.71, Inflation Adjusted Worst 10% value = 114617.61
Inflation tracking: Expected annual return = 0.035, Expected annual risk = 0.0: Inflation Adjusted Median value = 99999.99, Inflation Adjusted Best 10% value = 99999.99, Inflation Adjusted Worst 10% value = 99999.99
