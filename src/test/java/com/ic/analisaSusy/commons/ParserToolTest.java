package com.ic.analisaSusy.commons;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.util.LinkedHashMap;

import org.junit.Test;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;
import com.ic.analisaSusy.analysis.Metric;
import com.ic.analisaSusy.analysis.Tool;

/**
 * @author dmarinho
 *
 */
public class ParserToolTest {

    private static final String CODE_FILE = "123";
    private static final String FILE_OUTPUT = "carregar.txt";
    private static final String FILE_1 = "/bash/conf.xml";

    @Test
    public void testParseConfigFile() {

        final Multimap<Tool, Metric> parseConfigFile = ParserTool.parseConfigFile(FILE_1);
        assertNull(parseConfigFile);
    }

    @Test
    public void testParseError1() {

        final Multimap<ApplicationError, String> errors = ArrayListMultimap.create();
        errors.put(ApplicationError.NF_INPUT, FILE_OUTPUT);
        final String msgError = ParserTool.parseErrors(errors);

        assertEquals("O arquivo de entrada carregar.txt não foi encontrado.", msgError.trim());
    }

    @Test
    public void testParseError2() {

        final Multimap<ApplicationError, String> errors = ArrayListMultimap.create();
        errors.put(ApplicationError.NF_CODE, CODE_FILE);
        final String msgError = ParserTool.parseErrors(errors);

        assertEquals("O arquivo de código 123 não foi encontrado.", msgError.trim());
    }

    @Test
    public void testParseError3() {

        final Multimap<ApplicationError, String> errors = ArrayListMultimap.create();
        errors.put(ApplicationError.NF_CCSM, null);
        final String msgError = ParserTool.parseErrors(errors);

        assertEquals("A ferramenta CCSM não foi encontrada no PATH do ambiente de execução.", msgError.trim());
    }

    @Test
    public void testParseError4() {

        final Multimap<ApplicationError, String> errors = ArrayListMultimap.create();
        errors.put(ApplicationError.NF_FILE_ARG, null);
        final String msgError = ParserTool.parseErrors(errors);

        assertEquals("O argumento -f (arquivo de entrada) não foi informado.", msgError.trim());
    }

    @Test
    public void testParseError5() {

        final Multimap<ApplicationError, String> errors = ArrayListMultimap.create();
        errors.put(ApplicationError.INVALID_EXT, CODE_FILE);
        final String msgError = ParserTool.parseErrors(errors);

        assertEquals("O arquivo de código 123 não pertence a linguagem C.", msgError.trim());
    }

    @Test
    public void testParseError6() {

        final Multimap<ApplicationError, String> errors = ArrayListMultimap.create();
        errors.put(ApplicationError.NO_VALID_FILES, null);
        final String msgError = ParserTool.parseErrors(errors);

        assertEquals("Não foram informados arquivos da linguagem C", msgError.trim());
    }

    @Test
    public void testParseError7() {

        final Multimap<ApplicationError, String> errors = ArrayListMultimap.create();
        errors.put(ApplicationError.NOT_FOUND_FILES, null);
        final String msgError = ParserTool.parseErrors(errors);

        assertEquals("Não foram encontrados arquivos de código", msgError.trim());
    }

    @Test
    public void testGenerateOutput() {

        final LinkedHashMap<String, LinkedHashMap<Metric, String>> structuredOutput = new LinkedHashMap<>();

        // Function 1
        final LinkedHashMap<Metric, String> metricFunction1 = new LinkedHashMap<>();
        metricFunction1.put(Metric.BREAK_C, "10");
        metricFunction1.put(Metric.CASE_C, "5");
        //metricFunction1.put(Metric.CMNT_DENS, "2");
        structuredOutput.put("1", metricFunction1);

        // Function 2
        final LinkedHashMap<Metric, String> metricFunction2 = new LinkedHashMap<>();
        metricFunction2.put(Metric.VAR_FILE_LOC_C, "32");
        metricFunction2.put(Metric.PARAM_PER_FUNC, "0");
        metricFunction2.put(Metric.NESTING_FUNC_C, "1");
        structuredOutput.put("2", metricFunction2);

        final String output = ParserTool.generateOutput(structuredOutput);

        assertEquals(createMetrics(), output);
    }

    private String createMetrics() {
        final StringBuffer metrics = new StringBuffer();

        // Function 1
        metrics.append("Função 1\n");
        metrics.append("\tDensidade de comentários (> 0,2): 2\n");

        // Function 2
        metrics.append("Função 2\n");
        metrics.append("\tNúmero de variáveis globais: 32\n");
        metrics.append("\tNúmero de parâmetros da função (0-5): 0\n");
        metrics.append("\tNível de aninhamento da função (0-4): 1\n");

        // Legenda
        metrics.append("\n");
        metrics.append(
                "Para mais informações, acesse a <a href=\"https://github.com/jnortiz/Analisa-SuSy/wiki/Métricas\" target=\"_blank\" >página</a> que descreve as métricas acima.\n");

        return metrics.toString();
    }

}
