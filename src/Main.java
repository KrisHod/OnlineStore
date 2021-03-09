import fileReader.CustomerFileReader;
import fileReader.ItemFileReader;
import fileReader.OrderFileReader;
import services.*;

import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        CustomerService customerService = new CustomerService();
        ItemService itemService = new ItemService();
        OrderService orderService = new OrderService();
        OrderedItemsService orderedItemsService = new OrderedItemsService();

        CustomerFileReader customerFileReader = new CustomerFileReader();
        ItemFileReader itemFileReader = new ItemFileReader();
        OrderFileReader orderFileReader = new OrderFileReader();

        ReportService reportService = new ReportService();
        reportService.showWomensPopularItem();
        reportService.showPopularItemByPeriod(LocalDate.of(2017, 6, 1), LocalDate.of(2017, 7, 1));
        reportService.showCandidateToRemove();

          //  Persist all the data in DB
//        customerService.addAllToDB(customerFileReader.getAll(Constants.CUSTOMERS_PATH));
//        itemService.addAllToDB(itemFileReader.getAll(Constants.ITEMS_PATH));
//        orderService.addAllToDB(orderFileReader.getAll(Constants.CUSTOMERS_PATH, Constants.ITEMS_PATH));
//        orderedItemsService.addAllToDB(orderFileReader.getAll(Constants.CUSTOMERS_PATH, Constants.ITEMS_PATH));


        // Write primary items and Candidate to remove to file
        itemService.writeToFilePrimaryItems();
        itemService.writeToFileCandidatesToRemove();

    }
}