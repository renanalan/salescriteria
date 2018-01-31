package rasc.com.br.salescriteria.model;

import java.util.List;

public interface SalesCriteria<T> {

    List<T> getResultList();

    T getSingleResult();

    SalesCriteria<T> andEquals(final boolean toLowerCase, final String attributeName, final Object value);

    SalesCriteria<T> andEquals(final String attributeName, final Object value);

    SalesCriteria<T> orEquals(final boolean toLowerCase, final String attributeName, final Object value);

    SalesCriteria<T> orEquals(final String attributeName, final Object value);

}
