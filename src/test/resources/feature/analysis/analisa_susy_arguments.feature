Feature: Receive arguments from command line.
It is expected the arguments to be -f and a filename containing paths to C source codes.

@Runme
Scenario: All arguments are correct.
Given The -f and a filename as input for Analisa-SuSy
When SuSy calls Analisa-SuSy
Then Open the file given as input

@Runme
Scenario: Without any argument.
Given 0 arguments as input for Analisa-SuSy
When SuSy calls Analisa-SuSy using 0 arguments
Then Throw a message saying the arguments are missing

@Runme
Scenario: Without file path.
Given The list of parameters: -f
When SuSy calls Analisa-SuSy using just the -f argument
Then Analisa-SuSy will return an error message asking for a filename

@Runme
Scenario: Without -f option.
Given The list of parameters: path
When SuSy calls Analisa-SuSy passing only a filename as argument
Then Throw a message saying the -f option is missing