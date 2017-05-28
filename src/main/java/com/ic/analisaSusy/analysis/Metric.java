package com.ic.analisaSusy.analysis;

/**
 *
 * @author Marcelo C. Araújo
 */
public enum Metric {
    FOR_C(Tool.CCSM, "KW_FOR_CNT"), 
    IF_C(Tool.CCSM, "KW_IF_CNT"), 
    WHILE_C(Tool.CCSM, "KW_WHILE_CNT"), 
    RETURN_C(Tool.CCSM, "KW_RETURN_CNT"), 
    HALSTEAD(Tool.CCSM, "HALSTEAD_*");

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
