package com.example.demo.utils;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.GregorianCalendar;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

public class DemoDateUtils {

	public static XMLGregorianCalendar convertDateToXMLGregorianCalendar(Timestamp timestamp)
			throws DatatypeConfigurationException {

		if (timestamp == null) {
			return null;
		}

		GregorianCalendar gc = new GregorianCalendar();
		gc.setTime(timestamp);

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
