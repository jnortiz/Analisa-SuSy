Feature: Processing the CCSM output.

Scenario: The command-line used to invocate CCSM was generated properly but the CCSM output is empty.
It is just a coverage scenario. It is expected the source code is valid (SuSy guarantee)
and is in fact a C code (test covered in other scenario).

Given The CCSM input is correct
When The CCSM output is empty
Then Throw a message saying the input file is not a C code or the source code did
not pass successfully in the compilation phase.

