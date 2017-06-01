# Assumptions:
# The output of the CCSM output is known

Feature: Processing the CCSM output
	The CCSM output should consists in a file containing informations about all metrics.

Scenario:
	Given The expected file is empty
	When The CCSM module returns from calling
	Then The output should be empty as well