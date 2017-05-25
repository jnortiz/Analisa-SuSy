Feature: Filtering a metric result found in the searching phase

Scenario: The metric and its associated result were found in the output of the analysis tool
	Given A string containing the result associated to a matched metric
	When There is an associated result
	Then Print in the standard output the result corresponding this metric
	And Include a brief and clear explanation about this metric