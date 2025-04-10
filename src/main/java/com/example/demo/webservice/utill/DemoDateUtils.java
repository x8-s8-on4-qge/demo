package com.example.demo.webservice.utill;

import java.sql.Date;
import java.util.GregorianCalendar;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

public class DemoDateUtils {

	public static XMLGregorianCalendar convertDateToXMLGregorianCalendar(Date date)
			throws DatatypeConfigurationException {

		if (date == null) {
			return null;
		}

		GregorianCalendar gc = new GregorianCalendar();
		gc.setTime(date);

		return DatatypeFactory.newInstance().newXMLGregorianCalendar(gc);
	}
}
