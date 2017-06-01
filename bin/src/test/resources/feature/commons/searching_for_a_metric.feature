Feature: Searching for a metric in the analysis output file when it is not empty

Scenario: The metric being searched exists in the analysis result file
	Given The analysis result
	When The metric matches
	Then It is correct