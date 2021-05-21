package org.dieschnittstelle.ess.basics;


import org.dieschnittstelle.ess.basics.annotations.AnnotatedStockItemBuilder;
import org.dieschnittstelle.ess.basics.annotations.DisplayAs;
import org.dieschnittstelle.ess.basics.annotations.StockItemProxyImpl;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;

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
		try{
		//  by iterating over the object's attributes / fields as provided by its class
		for (int i = 0; i < fields.length;i++){
			String fieldName = fields[i].getName();
			//TODO convert fiedlname into getter methode
			String uppercaseLetter = getFirstLetterAsUppercase(fieldName);
			String getter = fieldNameToGetter(fieldName,uppercaseLetter);
			Method getterMethode = klass.getMethod(getter);
		}
		//  and reading out the attribute values. The string representation
		//  will then be built from the field names and field values.
		//  Note that only read-access to fields via getters or direct access
		//  is required here.

		// TODO BAS3: if the new @DisplayAs annotation is present on a field,
		//  the string representation will not use the field's name, but the name
		//  specified in the the annotation. Regardless of @DisplayAs being present
		//  or not, the field's value will be included in the string representation.
			for (Field field : fields) {
				show("Begin fields:" + Arrays.toString(fields));
				// TODO BAS3: if the new @DisplayAs annotation is present on a field,
				show("fieldName: " + field.getName());
				String fieldName = field.getName();
				//TODO getting the names of the declared field
				String firstLetterUppercase = getFirstLetterAsUppercase(fieldName);
				// TODO get the declared Getter Names
				String getterMethodName = fieldNameToGetter(fieldName, firstLetterUppercase);
				//TODO reconstruct the corresponding Getter Method provided by the class
				Method getterMethod = klass.getDeclaredMethod(getterMethodName);
				show("This is the Getter Method: %s", getterMethod);
				//TODO check return type of from the getter Method
				if (getterMethod.getReturnType() == (Integer.TYPE)) {
					//TODO Task BAS2 and BAS3 in getStringForIntegerType
					chain = getStringForIntegerType(consumable, klassName, chain, field, fieldName, getterMethod);
				} else if (getterMethod.getReturnType() == String.class) {
					//TODO Task BAS2 and BAS3 in getStringForStringType
					chain = getStringForStringType(consumable, chain, field, fieldName, getterMethod);
				} else {
					show("String fieldValueInt = Value: something went wrong!!");
				}
			}
			show("Aufgabe 1 BAS2 + BAS3: " + "{" + chain + "}");
		}
		catch(Exception e){
			e.getMessage();
			e.printStackTrace();

		}
	}
	private static String getStringForStringType(Object consumable, String chain, Field field, String fieldName, Method getterMethod) throws IllegalAccessException, InvocationTargetException {
		//TODO invoke the getter to get the String value from the field
		show("String getter= getterMethod.getReturnType(): %s", getterMethod.getReturnType());
		//String.valueOf();
		String fieldValueString = (String) getterMethod.invoke(consumable);
		show("String getter fieldname= " + fieldName);
		// TODO BAS3: if the new @DisplayAs annotation is present on a field,
		//  the string representation will not use the field's name, but the name
		//  specified in the the annotation.
		if (field.isAnnotationPresent(DisplayAs.class)) {
			fieldName = field.getAnnotation(DisplayAs.class).value();
			show("this is field.getAnnotation().value: " + field.getAnnotation(DisplayAs.class).value());
		}
		// TODO Regardless of @DisplayAs being present
		//  or not, the field's value will be included in the string representation.
		//TODO create the second Part <fieldname>:<fieldValueString> of th String representation chain
		show("String fieldValueString = Value: %s", fieldValueString);
		String brandName = fieldName + ":" + fieldValueString;
		String comma = ", ";
		String commaBrandName = comma + brandName;
		chain += commaBrandName;
		return chain;
	}
	private static String getStringForIntegerType(Object consumable, String className, String chain, Field field, String fieldName, Method getterMethod) throws IllegalAccessException, InvocationTargetException {
		show("This is the Getter getReturnType: %s", getterMethod.getReturnType());
		//TODO invoke the getter to get the Int value from the field
		Integer fieldValueInt = (Integer) getterMethod.invoke(consumable);
		show("INT fieldValueInt = Value: %s", fieldValueInt);
		// TODO BAS3: if the new @DisplayAs annotation is present on a field,
		//  the string representation will not use the field's name, but the name
		//  specified in the the annotation.
		if (field.isAnnotationPresent(DisplayAs.class)) {
			fieldName = field.getAnnotation(DisplayAs.class).value();
			show("this is field.getAnnotation().value: " + field.getAnnotation(DisplayAs.class).value());
		}
		// TODO Regardless of @DisplayAs being present
		//  or not, the field's value will be included in the string representation.
		//TODO create the second Part <fieldname>:<fieldValueString> of th String representation chain

		//TODO create the first Part <simple Klassenname> <fieldname>:<fieldValueInt> of th String representation chain
		String klassName = className + " " + fieldName + ":" + fieldValueInt;
		chain += klassName;
		return chain;
	}

	private static String fieldNameToGetter(String fieldName, String uppercaseLetter) {
		return  String.format("get%s%s",uppercaseLetter,fieldName.substring(1));
	}

	private static String getFirstLetterAsUppercase(String fieldName) {
		String firstLetter = fieldName.substring(0,1);
		return firstLetter.toUpperCase();
	}

}
