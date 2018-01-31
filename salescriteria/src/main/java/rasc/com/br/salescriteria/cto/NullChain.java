package rasc.com.br.salescriteria.cto;


public class NullChain implements SmartSqlChain {

    private SmartSqlChain smartSqlChain;

    @Override
    public String createSmartSql(String smartSql, String soup, String attributeName, Object value, Type type, Check check) {
        StringBuilder builder = new StringBuilder(smartSql);
        if (check.equals(Check.LIKE)
                || check.equals(Check.NOT_LIKE)) {
            builder
                    .append("{")
                    .append(soup)
                    .append(":")
                    .append(attributeName)
                    .append("} ")
                    .append(check.getSymbol());
            return builder.toString();
        }
        return smartSqlChain.createSmartSql(smartSql, soup, attributeName, value, type, check);
    }

    @Override
    public void setNextChain(SmartSqlChain smartSqlChain) {
        this.smartSqlChain = smartSqlChain;
    }
}
