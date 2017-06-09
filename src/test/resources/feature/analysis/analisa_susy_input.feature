Feature: Testing the input provided by SuSy.
In this story, the arguments received by Analisa-SuSy contains the -f option and
a file name called carregados.txt.

Scenario: The carregados.txt file does not exist.

Given A name of a file that does not exist
When Analisa-SuSy is called
Then Throw a message saying the file does not exist.

Scenario: The files containing source codes may not have the C extensions.

Given The carregados.txt file does exist
When Analisa-SuSy is called
Then Filter those file names which extension is .c and .h.

