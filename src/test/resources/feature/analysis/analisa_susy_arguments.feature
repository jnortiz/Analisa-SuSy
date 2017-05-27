Feature: Receive arguments from comand line

Scenario: All arguments are correct
Given The correct arguments as input for Analisa-SuSy
When SuSy calls Analisa-SuSy
Then Analisa-SuSy won't return an error message

Scenario: Without any argument
Given 0 arguments as input for Analisa-SuSy
When SuSy calls Analisa-SuSy using 0 arguments
Then Analisa-SuSy will return an error message

Scenario: Without file path
Given f option as argument
When SuSy calls Analisa-SuSy using just 1 argument
Then Analisa-SuSy will return an error message