package rasc.com.br.salescriteria.cto;

public enum Check {
    EQUALS(" = "),
    EQUALS_IGNORE_CASE(" = "),
    GREATER_THAN(" > "),
    LESSER_THAN(" < "),
    GREATER_THAN_EQUAL(" >= "),
    LESSER_THAN_EQUAL(" <= "),
    NOT_EQUALS(" != "),
    NOT_EQUALS_IGNORE_CASE(" != "),
    LIKE(" LIKE "),
    NOT_LIKE(" NOT LIKE "),
    IS_NULL(" IS NULL "),
    IS_NOT_NULL(" IS NOT NULL ");

    private String symbol;

    Check(String symbol) {
        this.symbol = symbol;
    }

    public String getSymbol() {
        return symbol;
    }
}
