package services;

import entities.Item;
import entities.Order;
import fileReader.ItemFileReader;
import repository.ItemRepository;
import utils.FileUtils;

import java.time.LocalDate;
import java.util.*;


public class ItemService {
    private ItemFileReader itemFileReader;
    private ItemRepository itemRepository;
    private OrderedItemsService orderedItemsService;
    private FileUtils fileUtils;

    public ItemService() {
        init();
    }

    private void init() {
        this.itemFileReader = new ItemFileReader();
        this.itemRepository = new ItemRepository();
        this.orderedItemsService = new OrderedItemsService();
        this.fileUtils = new FileUtils();
    }

    public void addAllToDB(List<Item> items) {
        for (Item it : items) {
            if (!isInDB(it)) {
                itemRepository.add(it);
            }
        }
    }

    public boolean isInDB(Item item) {
        return item.equals(itemRepository.getById(item.getId()));
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
    public static List<Item> getSortedListByPopularity(List<Item> items) {
        Map<Integer, Item> salesCount = getCountOfOccurrences(items);
        Map<Integer, Item> sortedMapByKey = new TreeMap<>(Collections.reverseOrder());
        sortedMapByKey.putAll(salesCount);
        return new ArrayList<>(sortedMapByKey.values());
    }

    //    count occurrences of items
    public static Map<Integer, Item> getCountOfOccurrences(List<Item> items) {
        Map<Integer, Item> salesCount = new LinkedHashMap<>();
        for (Item it : items) {
            salesCount.put(Collections.frequency(items, it), it);
        }
        return salesCount;
    }

    //    get three the most popular goods
    public List<Item> getThreePopular(List<Item> items) {
        List<Item> popItems = getSortedListByPopularity(items).subList(0, 3);
        popItems.forEach(i -> updatePrimaryItem(i));
        return popItems;
    }

    //    get three the least popular goods
    public List<Item> getCandidatesToRemove(List<Item> items) {
        List<Item> sortedByPopularity = getSortedListByPopularity(items);
        List<Item> candidatesToRemove = sortedByPopularity.subList(sortedByPopularity.size() - 3, sortedByPopularity.size());
        candidatesToRemove.forEach(i -> updateCandidateToRemove(i));
        return candidatesToRemove;
    }

    public void writeToFilePrimaryItems() {
        fileUtils.writeItems(getPrimaryItems(), "primaryItems.csv");
    }

    public void writeToFileCandidatesToRemove() {
        fileUtils.writeItems(getCandidatesToRemove(), "candidateToRemove.csv");
    }
}
