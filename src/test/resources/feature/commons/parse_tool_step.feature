# Assumptions:
# The output of the CCSM output is known

Feature: Parsing the CCSM output file

Scenario:
	Given A file containing the analysis result
	When CCSM produces a non-empty output 
	Then For each metric in selected metrics set, search for it in the analysis result
	
