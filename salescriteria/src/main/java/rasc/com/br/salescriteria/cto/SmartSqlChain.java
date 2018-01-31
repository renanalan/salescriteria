package rasc.com.br.salescriteria.cto;

/**
 * Created by rasc on 26/01/2018.
 */

public interface SmartSqlChain {
    String createSmartSql(String smartSql, String soup, String attributeName, Object value, Type type, Check check);
    void setNextChain(SmartSqlChain smartSqlChain);
}
