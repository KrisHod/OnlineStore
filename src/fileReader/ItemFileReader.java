package fileReader;

import entities.Item;
import exceptions.FailedValidationException;
import validation.Validator;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static utils.FileUtils.readFromFile;

public class ItemFileReader {
    private List<Item> items;

    public Map<String, List<Item>> itemsCache;

    public ItemFileReader() {
        this.itemsCache = new HashMap<>();
        init();
    }

    private void init() {
        getAll(Constants.ITEMS_PATH);
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public List<Item> getAll(String path) {
        if (itemsCache.get(path) != null) {
            return itemsCache.get(path);
        }

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
                if (Validator.isValidNumericDataInString(array[0])) {
                    id = Integer.parseInt(array[0]);
                }

                //title validation
                if (Validator.isValidStringLength(array[1], 50)) {
                    title = array[1];
                }

                //code validation
                if (Validator.isValidNumericDataInString(array[2])) {
                    code = Integer.parseInt(array[2]);
                }

                //producer validation
                if (Validator.isValidStringLength(array[3], 50)) {
                    producer = array[3];
                }

                //date and time format validation
                if (Validator.isValidDateTimeFormat(array[4], Constants.DATE_OF_LAST_UPDATE)) {
                    dateOfLastUpdate = LocalDateTime.parse(array[4], Constants.DATE_OF_LAST_UPDATE);
                }
                items.add(new Item(id, title, code, producer, dateOfLastUpdate));
            }
        } catch (FailedValidationException e) {
            e.printStackTrace();
        }
        itemsCache.put(path, items);
        return items;
    }
}
