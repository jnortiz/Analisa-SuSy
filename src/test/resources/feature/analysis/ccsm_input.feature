Feature: Processing the Analisa-SuSy input file carregados.txt.
In this story, the carregados.txt exists and we need to 
check if it contains paths to C source codes.

@Runme
Scenario: The file carregados.txt is empty.
Given The carregados.txt file exists and was opened properly 1
When It has no paths at all
Then Throw a message saying there is no C files to be evaluated

@Runme
Scenario: The file carregados.txt point to files that do not exist.
Given The carregados.txt file exists and was opened properly 2
When The paths point to non-existent files
Then Throw a message saying files doesn't exist

@Runme
Scenario: The file carregados.txt contains paths to valid files but it they have no C extension (.c or .h).
Given The carregados.txt file exists and was opened properly 3
When There is no paths to files with C extension
Then Throw a message saying no C files were found

@Runme
Scenario: During the metrics collection, the source code is compiled again and it may not be successful. When it does, the CCSM is empty. Perhaps, this scenario can be ignored. It is just for test coverage.
Given The carregados.txt file exists and was opened properly 4
When The path points to a C file but it don't pass in compilation phase
Then Throw a message saying the file is not a valid C source code