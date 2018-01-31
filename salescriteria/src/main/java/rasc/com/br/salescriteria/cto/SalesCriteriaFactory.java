package rasc.com.br.salescriteria.cto;

import com.salesforce.androidsdk.smartstore.store.SmartStore;

import rasc.com.br.salescriteria.model.SalesCriteria;
import rasc.com.br.salescriteria.model.SalesCriteriaImpl;

public class SalesCriteriaFactory {

    public static <T> SalesCriteria<T> createQueryCriteria(SmartStore smartStore, String soup, final Class<T> clazz) {
        return new SalesCriteriaImpl<>(smartStore, soup, clazz);
    }
}
