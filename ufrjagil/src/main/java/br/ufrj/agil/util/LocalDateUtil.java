package br.ufrj.agil.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class LocalDateUtil {

	private static final String DATE_FORMAT = "dd/MM/yyyy";
	private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern(DATE_FORMAT);

	public static Boolean isValid(String date) {
		try {
			DATE_FORMATTER.parse(date);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public static LocalDate convert(String date) {
		if (isValid(date)) {
			return LocalDate.parse(date, DATE_FORMATTER);
		}
		return null;
	}
	public static String convert(LocalDate date) {
		if (date == null) {
			return "";
		}
		return DATE_FORMATTER.format(date);
	}
}
