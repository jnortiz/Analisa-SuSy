# Assumptions:
# Input file name "carregados.c"
# There is only one analysis tool names CCSM
# If the compilation is successfull, then the uploaded files contain at least the main function.
# If there is at least one non-empty file, then the analysis tool will be incited.
# The analysis tool has as input all uploaded files at once
Feature: Run the CCSM component and produce and analysis output
To perform the metrics collection, the component must call the analysis tool passing as input all filenames contained in the input file.

Scenario: The input file exists and is not empty, and the analysis output is not empty
Given A file containing a list of filenames
When Analisa-SuSy was called by SuSy
Then Generate a string containing the concatenation of all files to be analysed
And Run the analysis tool passing all filenames as a single input
And Parse the output of the analysis tool
And Generate a file containing the analysis result

Scenario: The input file exists and is not empty, but the parser fails
Given A file containing a list of filenames
When The parser outputs an empty file
Then The Analisa-SuSy output should send an error flag to the pos-compiling caller
