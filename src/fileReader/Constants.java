package fileReader;

import java.time.format.DateTimeFormatter;
import java.util.Locale;

public final class Constants {
    private static final Locale locale = new Locale("en");
    public static final DateTimeFormatter CUSTOMER_DOB = DateTimeFormatter.ofPattern("d MMMM yyyy", locale);
    public static final DateTimeFormatter DATE_OF_LAST_PURCHASE = DateTimeFormatter.ofPattern("M/dd/yyyy", locale);
    public static final DateTimeFormatter DATE_OF_LAST_PURCHASE2 = DateTimeFormatter.ofPattern("MM.dd.yyyy", locale);
    public static final DateTimeFormatter DATE_OF_LAST_UPDATE = DateTimeFormatter.ofPattern("dd.MM.yyyy H:mm");
    public static final String ITEMS_PATH = "C:\\Users\\Zver\\Desktop\\Kris\\Go2It\\OnlineStore\\src\\utils\\items.csv";
    public static final String CUSTOMERS_PATH = "C:\\Users\\Zver\\Desktop\\Kris\\Go2It\\OnlineStore\\src\\utils\\Customers.csv";

    private Constants(){
        //this prevents even the native class from
        //calling this constructor as well :
        throw new AssertionError();
    }
}
