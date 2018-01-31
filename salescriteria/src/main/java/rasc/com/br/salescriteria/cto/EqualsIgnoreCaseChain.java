package rasc.com.br.salescriteria.cto;

public class EqualsIgnoreCaseChain implements SmartSqlChain {

    private SmartSqlChain smartSqlChain;

    @Override
    public String createSmartSql(String smartSql, String soup, String attributeName, Object value, Type type, Check check) {
        StringBuilder builder = new StringBuilder(smartSql);
        if (check.equals(Check.EQUALS_IGNORE_CASE)
                || check.equals(Check.NOT_EQUALS_IGNORE_CASE)) {
            builder
                    .append("lower({")
                    .append(soup)
                    .append(":")
                    .append(attributeName)
                    .append("}) ")
                    .append(check.getSymbol())
                    .append("'")
                    .append(value.toString().toLowerCase())
                    .append("'");
            return builder.toString();
        }
        return smartSqlChain.createSmartSql(smartSql, soup, attributeName, value, type, check);
    }

    @Override
    public void setNextChain(SmartSqlChain smartSqlChain) {
        this.smartSqlChain = smartSqlChain;
    }
}
