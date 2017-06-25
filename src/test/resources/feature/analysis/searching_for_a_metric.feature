Feature: Testing Analisa-SuSy output

@Runme
Scenario: The metric 'number of functions' exists
Given The correct arguments and carregados.txt
When Analisa-SuSy analyze all files
Then Output contains Número de funções

@Runme
Scenario: The metric 'number of case' exists
Given The correct arguments and carregados.txt
When Analisa-SuSy analyze all files
Then Output contains Número de ocorrências de 'case'

@Runme
Scenario: The metric 'number of breaks' exists
Given The correct arguments and carregados.txt
When Analisa-SuSy analyze all files
Then Output contains Número de ocorrências de 'break'

@Runme
Scenario: The metric 'number of goto' exists
Given The correct arguments and carregados.txt
When Analisa-SuSy analyze all files
Then Output contains Número de ocorrências de 'goto'

@Runme
Scenario: The metric 'number of continue' exists
Given The correct arguments and carregados.txt
When Analisa-SuSy analyze all files
Then Output contains Número de ocorrências de 'continue'

@Runme
Scenario: The metric 'number of global variable' exists
Given The correct arguments and carregados.txt
When Analisa-SuSy analyze all files
Then Output contains Número de variáveis globais

@Runme
Scenario: The metric 'number of return' exists
Given The correct arguments and carregados.txt
When Analisa-SuSy analyze all files
Then Output contains Número de ocorrências de 'return'

@Runme
Scenario: The metric 'cyclomatic complexity' exists
Given The correct arguments and carregados.txt
When Analisa-SuSy analyze all files
Then Output contains Complexidade em termos de caminhos independentes

@Runme
Scenario: The metric 'number of calls to function' exists
Given The correct arguments and carregados.txt
When Analisa-SuSy analyze all files
Then Output contains Número de chamadas para esta função

@Runme
Scenario: The metric 'number of parameters' exists
Given The correct arguments and carregados.txt
When Analisa-SuSy analyze all files
Then Output contains Número de parâmetros da função

@Runme
Scenario: The metric 'nesting number' exists
Given The correct arguments and carregados.txt
When Analisa-SuSy analyze all files
Then Output contains Nível de aninhamento da função

@Runme
Scenario: The metric 'volume of Halstead' exists
Given The correct arguments and carregados.txt
When Analisa-SuSy analyze all files
Then Output contains Tamanho da implementação

@Runme
Scenario: The metric 'difficulty of Halstead' exists
Given The correct arguments and carregados.txt
When Analisa-SuSy analyze all files
Then Output contains Propensão a erros