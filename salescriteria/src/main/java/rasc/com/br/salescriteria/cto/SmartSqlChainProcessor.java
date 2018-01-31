package rasc.com.br.salescriteria.cto;

public class SmartSqlChainProcessor {
    private NullChain c1;

    public SmartSqlChainProcessor() {
        this.c1 = new NullChain();
        OtherChain c2 = new OtherChain();
        EqualsIgnoreCaseChain c3 = new EqualsIgnoreCaseChain();
        c1.setNextChain(c2);
        c2.setNextChain(c3);
    }

    public String process(String smartSql, String soup, String attributeName, Object value, Type type, Check check) {
        StringBuilder builder = new StringBuilder(smartSql);
        if (builder.length() != 0) {
            builder.append(" ").append(type.name()).append(" ");
        }
        return c1.createSmartSql(builder.toString(), soup, attributeName, value, type, check);
    }
}
