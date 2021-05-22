package org.dieschnittstelle.ess.jrs;

import java.util.List;

import org.apache.logging.log4j.Logger;
import org.dieschnittstelle.ess.entities.GenericCRUDExecutor;
import org.dieschnittstelle.ess.entities.erp.AbstractProduct;
import org.dieschnittstelle.ess.entities.erp.IndividualisedProductItem;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.Context;

/*
 * TODO JRS2: implementieren Sie hier die im Interface deklarierten Methoden
 */

public class ProductCRUDServiceImpl implements IProductCRUDService {
    //Unter Vewendung der instanz GenericCRUDExecutor
    private GenericCRUDExecutor<AbstractProduct> productCRUD;
    protected static Logger logger = org.apache.logging.log4j.LogManager.getLogger(ProductCRUDServiceImpl.class);

    /**
     * this accessor will be provided by the ServletContext, to which it is written by the TouchpointServletContextListener
     */
    public ProductCRUDServiceImpl(@Context ServletContext servletContext, @Context HttpServletRequest request) {
        logger.info(" ProductCRUDServiceImpl <constructor> : " + servletContext + "/" + request);
        // read out the dataAccessor
        this.productCRUD = (GenericCRUDExecutor<AbstractProduct>) servletContext.getAttribute("productCRUD");
        logger.debug("read out the productCRUD from the servlet context: " + this.productCRUD);
    }

    private GenericCRUDExecutor<AbstractProduct> getExecutor() {
        return (GenericCRUDExecutor<AbstractProduct>) this.productCRUD;

//        return (GenericCRUDExecutor<IndividualisedProductItem><IndividualisedProductItem>) this.servlet.getAttribute("productCRUD");
    }

    @Override
    public IndividualisedProductItem createProduct(
            IndividualisedProductItem prod) {

        // TODO Auto-generated method stub
        return (IndividualisedProductItem) this.getExecutor().createObject(prod);
    }

    @Override
    public List<IndividualisedProductItem> readAllProducts() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public IndividualisedProductItem updateProduct(long id,
                                                   IndividualisedProductItem update) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public boolean deleteProduct(long id) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public IndividualisedProductItem readProduct(long id) {
        // TODO Auto-generated method stub
        return null;
    }

}
