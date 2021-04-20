package org.dieschnittstelle.ess.basics;


import org.dieschnittstelle.ess.basics.annotations.AnnotatedStockItemBuilder;
import org.dieschnittstelle.ess.basics.annotations.StockItemProxyImpl;

import java.lang.reflect.Field;

import static org.dieschnittstelle.ess.utils.Utils.*;

public class ShowAnnotations {

	public static void main(String[] args) {
		// we initialise the collection
		StockItemCollection collection = new StockItemCollection(
				"stockitems_annotations.xml", new AnnotatedStockItemBuilder());
		// we load the contents into the collection
		collection.load();

		for (IStockItem consumable : collection.getStockItems()) {
			showAttributes(((StockItemProxyImpl)consumable).getProxiedObject());
		}

		// we initialise a consumer
		Consumer consumer = new Consumer();
		// ... and let them consume
		consumer.doShopping(collection.getStockItems());
	}

	/*
	 * TODO BAS2
	 */
	private static void showAttributes(Object consumable) {
		//TODO get the klass from the consumable instance
		Class klass = consumable.getClass();
		show("class is: " + consumable.getClass());
		// TODO BAS2: create a string representation of consumable
		String klassName =  consumable.getClass().getSimpleName();//  the simple name of the class
		show("The class as a string representation/classname is: " + consumable.getClass().getSimpleName());
		//TODO all Declared Fields as ( attributes ) as Field array
		Field[] fields = klass.getDeclaredFields();
		String chain= "";
		//  by iterating over the object's attributes / fields as provided by its class
		for (int i = 0; i < fields.length;i++){
			String fieldName = fields[i].getName();
			//TODO
		}
		//  and reading out the attribute values. The string representation
		//  will then be built from the field names and field values.
		//  Note that only read-access to fields via getters or direct access
		//  is required here.

		// TODO BAS3: if the new @DisplayAs annotation is present on a field,
		//  the string representation will not use the field's name, but the name
		//  specified in the the annotation. Regardless of @DisplayAs being present
		//  or not, the field's value will be included in the string representation.
	}

}
