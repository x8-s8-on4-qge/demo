package com.example.demo.utils;

import java.sql.Date;
import java.sql.Timestamp;
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

	public static Timestamp convertXMLGregorianCalendarToTimestamp(XMLGregorianCalendar xmlGregorianCalendar) {

		if (xmlGregorianCalendar == null) {
			return null;
		}

        java.util.Date date = xmlGregorianCalendar.toGregorianCalendar().getTime();
        return new Timestamp(date.getTime());
	}

}
