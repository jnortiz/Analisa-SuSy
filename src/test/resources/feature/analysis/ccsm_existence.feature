Feature: Checking the existence of CCSM in the system.
When the CCSM module is executed in the /bin/bash, its output is empty and 
its input is correct, it can be assumed the bash could not execute CCSM.

Scenario: The CCSM component is not available in the path.

Given Analisa-SuSy is called by SuSy with the correct parameters
When CCSM output is empty
Then Throw a message saying the path to CCSM must be added to the PATH variable.
