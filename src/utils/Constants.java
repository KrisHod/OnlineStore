package utils;

import java.time.format.DateTimeFormatter;
import java.util.Locale;

public final class Constants {
    private static final Locale locale = new Locale("en");
    public static final DateTimeFormatter CUSTOMER_DOB_FORMAT = DateTimeFormatter.ofPattern("d MMMM yyyy", locale);
    public static final DateTimeFormatter DATE_OF_LAST_PURCHASE_FORMAT = DateTimeFormatter.ofPattern("M/dd/yyyy", locale);
    public static final DateTimeFormatter DATE_OF_LAST_PURCHASE2_FORMAT = DateTimeFormatter.ofPattern("MM.dd.yyyy", locale);
    public static final DateTimeFormatter DATE_OF_LAST_UPDATE_FORMAT = DateTimeFormatter.ofPattern("dd.MM.yyyy H:mm");
    public static final String ITEMS_PATH = "C:\\Users\\Zver\\Desktop\\Kris\\Go2It\\OnlineStore\\items.csv";
    public static final String CUSTOMERS_PATH = "C:\\Users\\Zver\\Desktop\\Kris\\Go2It\\OnlineStore\\Customers.csv";

    private Constants() {
    }
}
