package org.dieschnittstelle.ess.mip.components.erp.api;

import org.dieschnittstelle.ess.entities.erp.IndividualisedProductItem;

import java.util.List;

/**
 * TODO MIP3/4/6:
 * - declare the web api for this interface using JAX-RS
 * - implement the interface as a CDI Bean
 * - in the Bean implementation, delegate method invocations to the corresponding methods of the StockSystem Bean
 * - let the StockSystemClient in the client project access the web api via this interface - see ShoppingCartClient for an example
 */
public interface StockSystemService {

	/**
	 * adds some units of a product to the stock of a point of sale
	 */
    void addToStock(long productId, long pointOfSaleId, int units);

	/**
	 * removes some units of a product from the stock of a point of sale
	 */
    void removeFromStock(long productId, long pointOfSaleId, int units);

	/**
	 * returns all products on stock of some pointOfSale
	 */
    List<IndividualisedProductItem> getProductsOnStock(long pointOfSaleId);

	/**
	 * returns all products on stock
	 */
    List<IndividualisedProductItem> getAllProductsOnStock();

	/**
	 * returns the units on stock for a product at some point of sale
	 */
    int getUnitsOnStock(long productId, long pointOfSaleId);

	/**
	 * returns the total number of units on stock for some product
	 */
    int getTotalUnitsOnStock(long productId);

	/**
	 * returns the points of sale where some product is available
	 */
    List<Long> getPointsOfSale(long productId);

}
