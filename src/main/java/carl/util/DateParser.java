package carl.util;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class DateParser {

    public static LocalDateTime dateParser(String strDate) throws DateTimeParseException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
        return LocalDateTime.parse(strDate, formatter);

    }

    public static LocalDate dateParserWithoutTime(String strDate) throws DateTimeParseException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return LocalDate.parse(strDate, formatter);

    }

    public static String printDateError() {
        return "Invalid date inputted! Correct format: \"yyyy-MM-dd HHmm\", e.g., \"2026-12-24 1800\"";
    }

    public static String printDateErrorWithoutTime() {
        return "Invalid date inputted! Correct format: \"yyyy-MM-dd\", e.g., \"2026-12-24\"";
    }


    public static DateTimeFormatter dateFormatter() {
        return DateTimeFormatter.ofPattern("MMM dd yyyy, h:mm a");
    }

    public static DateTimeFormatter dateFormatterSave() {
        return DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
    }
}
