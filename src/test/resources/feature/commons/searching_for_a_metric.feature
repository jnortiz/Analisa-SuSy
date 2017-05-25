Feature: Searching for a metric in the analysis output file when it is not empty

Scenario: The metric being searched exists in the analysis result file
	Given A file containing the analysis result
	When The metric matches 
	Then Filter its associated result	
	And Look for the next metric in this same input file

Scenario: The metric does not exist in the result file
	Given A file containing the analysis result
	When The searched metric is not found 
	Then Do not included it in the output of our component
	And Look for the next metric in this same input file
	