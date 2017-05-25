# Assumptions:
# The output of the CCSM output is known

Feature: Processing the CCSM output
	The CCSM output should consists in a file containing informations about all metrics.

Scenario:
	Given The expected file is empty
	When The CCSM module returns from calling
	Then An error occured when executing the CCSM module
	And The output of the parser should be empty as well
	
Scenario:
	Given A non-empty file containing an analysis result
	When The analysis tool return from calling and produces an output in the stdout
	Then Parse the file containing the analysis result
	And Produces a filtered and more legible analysis result in the stdout