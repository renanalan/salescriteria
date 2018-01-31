package rasc.com.br.salescriteria.model;

import android.util.Log;

import com.salesforce.androidsdk.smartstore.store.QuerySpec;
import com.salesforce.androidsdk.smartstore.store.SmartStore;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

import rasc.com.br.salescriteria.cto.Check;
import rasc.com.br.salescriteria.cto.SmartSqlChainProcessor;
import rasc.com.br.salescriteria.cto.Type;
import rasc.com.br.salescriteria.util.JsonHelper;

public class SalesCriteriaImpl<T> implements SalesCriteria<T> {

    private static final int LIMIT = 50000;
    private SmartStore smartStore;
    private String soup;
    private Class<T> clazz;

    private String where;
    private String orderBy;
    private String groupBy;
    private String limit;

    public SalesCriteriaImpl(final SmartStore smartStore, String soup, final Class<T> clazz) {
        this.smartStore = smartStore;
        this.soup = soup;
        this.clazz = clazz;
        where = "";
        orderBy = "";
        groupBy = "";
        limit = "";
    }

    @Override
    public List<T> getResultList() {
        if (!smartStore.hasSoup(soup))
            return new ArrayList<>();
        try {
            String smartSql = generateSmartSQL();
            QuerySpec querySpec = QuerySpec.buildSmartQuerySpec(smartSql, LIMIT);
            JSONArray jsonArray = smartStore.query(querySpec, 0);
            List<T> result = JsonHelper.fromGsonPayload(jsonArray, clazz);
            return result;
        } catch (JSONException e) {
            Log.e(SalesCriteriaImpl.class.getSimpleName(), e.getMessage(), e);
            return new ArrayList<>();
        }
    }

    @Override
    public T getSingleResult() {
        try {
            String smartSql = generateSmartSQL();
            QuerySpec querySpec = QuerySpec.buildSmartQuerySpec(smartSql, 1);
            JSONArray jsonArray = smartStore.query(querySpec, 0);
            List<T> result = JsonHelper.fromGsonPayload(jsonArray, clazz);
            return result.size() > 0 ? result.get(0) : null;
        } catch (JSONException e) {
            Log.e(SalesCriteriaImpl.class.getSimpleName(), e.getMessage(), e);
            return null;
        }
    }

    @Override
    public SalesCriteria<T> andEquals(final boolean toLowerCase, final String attributeName, final Object value) {
        mergeConditions(attributeName, value, Type.AND, Check.EQUALS);
        return this;
    }

    @Override
    public SalesCriteria<T> andEquals(final String attributeName, final Object value) {
        return this.andEquals(false, attributeName, value);
    }

    @Override
    public SalesCriteria<T> orEquals(boolean toLowerCase, String attributeName, Object value) {
        mergeConditions(attributeName, value, Type.OR, Check.EQUALS);
        return this;
    }

    @Override
    public SalesCriteria<T> orEquals(String attributeName, Object value) {
        return this.orEquals(false, attributeName, value);
    }

    private void mergeConditions(String attributeName, Object value, Type type, Check check) {
        SmartSqlChainProcessor processor = new SmartSqlChainProcessor();
        String smartSql = processor.process("", soup, attributeName, value, type, check);
        if (!where.equals(""))
            where += " " + type.name() + " ";
        where += smartSql;
        Log.d(SalesCriteriaImpl.class.getSimpleName(), smartSql);
    }

    private String generateSmartSQL() {
        StringBuilder builder = new StringBuilder();
        builder.append("SELECT {" + soup + ":_soup} FROM {" + soup + "} ");
        if (!where.equals(""))
            builder.append("WHERE ").append(where).append(" ");
        if (!orderBy.equals(""))
            builder.append("ORDER BY ").append(orderBy).append(" ");
        if (!limit.equals(""))
            builder.append("LIMIT ").append(limit).append(" ");
        return builder.toString();
    }
}
