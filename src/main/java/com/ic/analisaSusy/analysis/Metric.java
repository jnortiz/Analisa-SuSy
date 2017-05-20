package com.ic.analisaSusy.analysis;

/**
 *
 * @author Marcelo C. Araújo
 */
public enum Metric {
    NUM_LINES(Tool.ANALIZO, "NML"), A(Tool.TOOL2, "AAA");

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

}
