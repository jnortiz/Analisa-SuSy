package com.ic.analisaSusy.analysis;

/**
 *
 * @author Marcelo C. Araújo
 */
public enum Metric {
//    FOR_C(Tool.CCSM, "KW_FOR_CNT"), 
//    IF_C(Tool.CCSM, "KW_IF_CNT"), 
//    WHILE_C(Tool.CCSM, "KW_WHILE_CNT"), 
//    RETURN_C(Tool.CCSM, "KW_RETURN_CNT");
    FUNC_C(Tool.CCSM, "FUNC_CNT", "Número de funções: %s"),
    CASE_C(Tool.CCSM, "KW_CASE_CNT", "Número de ocorrências de 'count': %s"),
    BREAK_C(Tool.CCSM, "KW_BREAK_CNT", "Número de ocorrências de 'break': %s"),
    GOTO_C(Tool.CCSM, "KW_GOTO_CNT", "Número de ocorrências de 'goto': %s"),
    CONTINUE_C(Tool.CCSM, "KW_CONTINUE_CNT", "Número de ocorrências de 'continue': %s"),
    VAR_FILE_LOC_C(Tool.CCSM, "VAR_FILE_LOC_CNT", "Número de variáveis locais: %s"),
    RETURN_POINT_C(Tool.CCSM, "RETURN_POINT_CNT", "Número de ocorrências de 'return': %s"),
    CMNT_DENS(Tool.CCSM, "COMMENT_HIS_COMF", "Densidade de comentários: %s"),
    CC(Tool.CCSM, "MCCABE", "Complexidade ciclomática: %s"),
    UNQ_FUNC_CALL(Tool.CCSM, "OP_FN_CALL_UNIQUE_CNT", "Número de chamadas únicas a funções: %s"),
    PARAM_PER_FUNC(Tool.CCSM, "STMT_HIS_PARAM", "Número de parâmetros da função: %s"),
    NESTING_FUNC_C(Tool.CCSM, "FUNC_NESTING", "Nível de aninhamento da função: %s"),
    HAL_VOC(Tool.CCSM, "HALSTEAD_VOCABULARY", "Vocabulário de Halstead: %s"),
    HAL_LEN(Tool.CCSM, "HALSTEAD_LENGTH", "Tamanho de Halstead: %s"),
    HAL_CALC_LEN(Tool.CCSM, "HALSTEAD_CALC_LENGTH", "Vocabulário de Halstead: %s"),
    HAL_VOL(Tool.CCSM, "HALSTEAD_VOLUME", "Volume de Halstead: %s"),
    HAL_D(Tool.CCSM, "HALSTEAD_DIFFICULTY", "Dificuldade de Halstead: %s");

    private Tool tool;
    private String toolCode;
    private String metricDescription;

    private Metric(final Tool aTool, final String aToolCode, String aMetricDescription) {
        this.tool = aTool;
        this.toolCode = aToolCode;
        this.metricDescription = aMetricDescription;
    }

    private Metric(final String aToolCode) {
        this.toolCode = aToolCode;
    }

    public Tool getTool() {
        return this.tool;
    }

    public String getToolCode() {
        return toolCode;
    }

    public String getMetricDescription() {
        return metricDescription;
    }

    public static Metric getEnum(String aValue) {
        for (Metric aMetric : Metric.values()) {
            if (aMetric.getToolCode().equals(aValue)) {
                return aMetric;
            }
        }
        throw new IllegalArgumentException();
    }

}
