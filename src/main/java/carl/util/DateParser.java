package carl.util;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;


/**
 * Utility class for parsing and formatting dates.
 */
public class DateParser {

    /**
     * Parses a string into a LocalDateTime object.
     *
     * @param strDate The date string to parse, expected in "yyyy-MM-dd HHmm" format.
     * @return The parsed LocalDateTime object.
     * @throws DateTimeParseException If the string cannot be parsed into a valid date and time.
     */
    public static LocalDateTime dateParser(String strDate) throws DateTimeParseException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
        return LocalDateTime.parse(strDate, formatter);

    }

    /**
     * Parses a string into a LocalDate object without time.
     *
     * @param strDate The date string to parse, expected in "yyyy-MM-dd" format.
     * @return The parsed LocalDate object.
     * @throws DateTimeParseException If the string cannot be parsed into a valid date.
     */
    public static LocalDate dateParserWithoutTime(String strDate) throws DateTimeParseException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return LocalDate.parse(strDate, formatter);

    }

    /**
     * Returns an error message for invalid date and time format.
     *
     * @return The error message string.
     */
    public static String printDateError() {
        return "Invalid date inputted! Correct format: \"yyyy-MM-dd HHmm\", e.g., \"2026-12-24 1800\"";
    }

    /**
     * Returns an error message for invalid date format (without time).
     *
     * @return The error message string.
     */
    public static String printDateErrorWithoutTime() {
        return "Invalid date inputted! Correct format: \"yyyy-MM-dd\", e.g., \"2026-12-24\"";
    }

    /**
     * Provides a formatter for displaying dates to the user.
     *
     * @return A DateTimeFormatter with the pattern "MMM dd yyyy, h:mm a".
     */
    public static DateTimeFormatter dateFormatter() {
        return DateTimeFormatter.ofPattern("MMM dd yyyy, h:mm a");
    }

    /**
     * Provides a formatter for saving dates to storage.
     *
     * @return A DateTimeFormatter with the pattern "yyyy-MM-dd HHmm".
     */
    public static DateTimeFormatter dateFormatterSave() {
        return DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
    }
}
