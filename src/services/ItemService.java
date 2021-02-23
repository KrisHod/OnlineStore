package services;

import entities.Item;
import exceptions.FailedValidationException;
import validation.Validation;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import static utils.FileUtils.readFromFile;

public class ItemService {

    private final DateTimeFormatter DATE_OF_LAST_UPDATE = DateTimeFormatter.ofPattern("dd.MM.yyyy H:mm");

    public List<Item> getAll(String path) {
        List<Item> items = new ArrayList<>();
        List<String> dataList = readFromFile(path);
        int id = 0;
        String title = null;
        int code = 0;
        String producer = null;
        LocalDateTime dateOfLastUpdate = null;

        try {
            for (int i = 1; i < dataList.size(); i++) {
                String[] array = dataList.get(i).split(";");

                //id validation
                if (Validation.isValidNumericDataInString(array[0])) {
                    id = Integer.parseInt(array[0]);
                }

                //title validation
                if (Validation.isValidStringLength(array[1], 50)) {
                    title = array[1];
                }

                //code validation
                if (Validation.isValidNumericDataInString(array[2])) {
                    code = Integer.parseInt(array[2]);
                }

                //producer validation
                if (Validation.isValidStringLength(array[3], 50)) {
                    producer = array[3];
                }


                //date and time format validation
                if (Validation.isValidDateTimeFormat(array[4], DATE_OF_LAST_UPDATE)) {
                    dateOfLastUpdate = LocalDateTime.parse(array[4], DATE_OF_LAST_UPDATE);
                }
                items.add(new Item(id, title, code, producer, dateOfLastUpdate));
            }
        } catch (FailedValidationException e) {
            e.printStackTrace();
        }
        return items;
    }

    public int getIdOfTheMostPopularGoods(List<Integer> idListOfPurchases) {
        if (idListOfPurchases == null) {
            return 0;
        }
        idListOfPurchases.sort(Comparator.naturalOrder());

        int previous = idListOfPurchases.get(0);
        int popular = idListOfPurchases.get(0);
        int count = 1;
        int maxCount = 1;

        for (int i = 1; i < idListOfPurchases.size(); i++) {
            if (idListOfPurchases.get(i) == previous)
                count++;
            else {
                if (count > maxCount) {
                    popular = idListOfPurchases.get(i - 1);
                    maxCount = count;
                }
                previous = idListOfPurchases.get(i);
                count = 1;
            }
        }

        return count > maxCount ? idListOfPurchases.get(idListOfPurchases.size() - 1) : popular;
    }

    public Item getById(int id, String path) {
        for (Item item : getAll(path)) {
            if (item.getId() == id) {
                return item;
            }
        }
        return null;
    }

    //  the most popular goods during a particular weekend (passed in as a param)
//    public List<Integer> getPurchasesBetween(LocalDate start, LocalDate end) {
//        List<Integer> idListOfPurchases = new ArrayList<>();
//        for (Customer cus : getCustomerList()) {
//            if (cus.getDateOfLastPurchase().equals(start) || cus.getDateOfLastPurchase().equals(end) ||
//                    (cus.getDateOfLastPurchase().isAfter(start) && cus.getDateOfLastPurchase().isBefore(start))) {
//                for (int id : cus.getLastPurchases()) {
//                    idListOfPurchases.add(id);
//                }
//            }
//        }
//        return idListOfPurchases;
//    }


}
