package services;

import entities.Item;
import entities.Order;
import fileReader.ItemFileReader;
import repository.ItemRepository;

import java.time.LocalDate;
import java.util.*;


public class ItemService {
    private ItemFileReader itemFileReader;
    private ItemRepository itemRepository;
    private OrderedItemsService orderedItemsService;

    public ItemService() {
        init();
    }

    private void init() {
        this.itemFileReader = new ItemFileReader();
        this.itemRepository = new ItemRepository();
        this.orderedItemsService = new OrderedItemsService();
    }

    public void save(List<Item> items) {
        for (Item it : items) {
            itemRepository.add(it);
        }
    }

    public List<Item> getAll() {
        return itemRepository.getAll();
    }

    public Item getByIdFromDB(int id) {
        return itemRepository.getById(id);
    }

    // TODO verification if already update
    public void updatePrimaryItem(Item item) {
        itemRepository.updatePrimaryItem(item);
    }

    public void updateCandidateToRemove(Item item) {
        itemRepository.updateCandidateToRemove(item);
    }

    public List<Item> getPrimaryItems() {
        return itemRepository.getPrimaryItems();
    }

    public List<Item> getCandidatesToRemove() {
        return itemRepository.getCandidatesToRemove();
    }

    public Item getByIdFromFile(int id, String path) {
        for (Item item : itemFileReader.getAll(path)) {
            if (item.getId() == id) {
                return item;
            }
        }
        return null;
    }

    //     get list of items sold in particular period
    public List<Item> getPurchasesBetween(LocalDate start, LocalDate end) {
        List<Item> items = new ArrayList<>();
        for (Order or : orderedItemsService.getAll()) {
            if (or.getDateOrder().equals(start) || or.getDateOrder().equals(end) ||
                    (or.getDateOrder().isAfter(start) && or.getDateOrder().isBefore(start))) {
                items.addAll(orderedItemsService.getById(or.getId()));
            }
        }
        return items;
    }

    //    sort items by number of sales
    public List<Item> getSortedListByPopularity(List<Item> items) {
        Map<Item, Integer> salesCount = getCountOfOccurrences(items);
        Map<Item, Integer> sortedMapByValue = sortByValues(salesCount);
        return new ArrayList<>(sortedMapByValue.keySet());
    }

    private Map<Item, Integer> sortByValues(Map<Item, Integer> salesCount) {
        List<Item> mapKeys = new ArrayList<>(salesCount.keySet());
        List<Integer> mapValues = new ArrayList<>(salesCount.values());
        Collections.sort(mapValues);

        LinkedHashMap<Item, Integer> sortedMap =
                new LinkedHashMap<>();

        for (int val : mapValues) {
            Iterator<Item> keyIt = mapKeys.iterator();

            while (keyIt.hasNext()) {
                Item key = keyIt.next();
                int comp1 = salesCount.get(key);

                if (comp1 == val) {
                    keyIt.remove();
                    sortedMap.put(key, val);
                    break;
                }
            }
        }
        return sortedMap;
    }

    //    count occurrences of items
    public Map<Item, Integer> getCountOfOccurrences(List<Item> items) {
        Map<Item, Integer> salesCount = new HashMap<>();
        for (Item it : items) {
            salesCount.put(it, Collections.frequency(items, it));
        }
        return salesCount;
    }

}
