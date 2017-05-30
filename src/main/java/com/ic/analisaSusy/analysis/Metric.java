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
    FUNC_C(Tool.CCSM, "FUNC_CNT"),
    CASE_C(Tool.CCSM, "KW_CASE_CNT"),
    BREAK_C(Tool.CCSM, "KW_BREAK_CNT"),
    GOTO_C(Tool.CCSM, "KW_GOTO_CNT"),
    CONTINUE_C(Tool.CCSM, "KW_CONTINUE_CNT"),
    VAR_FILE_LOC_C(Tool.CCSM, "VAR_FILE_LOC_CNT"),
    RETURN_POINT_C(Tool.CCSM, "RETURN_POINT_CNT"),
    CMNT_DENS(Tool.CCSM, "COMMENT_HIS_COMF"),
    CC(Tool.CCSM, "MCCABE"),
    UNQ_FUNC_CALL(Tool.CCSM, "OP_FN_CALL_UNIQUE_CNT"),
    PARAM_PER_FUNC(Tool.CCSM, "STMT_HIS_PARAM"),
    NESTING_FUNC_C(Tool.CCSM, "FUNC_NESTING"),
    HAL_VOC(Tool.CCSM, "HALSTEAD_VOCABULARY"),
    HAL_LEN(Tool.CCSM, "HALSTEAD_LENGTH"),
    HAL_CALC_LEN(Tool.CCSM, "HALSTEAD_CALC_LENGTH"),
    HAL_VOL(Tool.CCSM, "HALSTEAD_VOLUME"),
    HAL_D(Tool.CCSM, "HALSTEAD_DIFFICULTY");

    private Tool tool;
    private final String toolCode;

    private Metric(final Tool aTool, final String aToolCode) {
        this.tool = aTool;
        this.toolCode = aToolCode;
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

}
