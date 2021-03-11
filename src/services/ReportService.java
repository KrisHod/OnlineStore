package services;

import entities.Gender;
import entities.Item;
import utils.FileUtils;

import java.time.LocalDate;
import java.util.List;

public class ReportService {
    private ItemService itemService;
    private OrderedItemsService orderedItemsService;
    private FileUtils fileUtils;

    public ReportService() {
        init();
    }

    private void init() {
        this.itemService = new ItemService();
        this.orderedItemsService = new OrderedItemsService();
        this.fileUtils = new FileUtils();
    }

    /**
     * get a certain number of the most popular items
     */
    public List<Item> getPopularItems(List<Item> items, int n) {
        List<Item> popItems = itemService.getSortedListByPopularity(items).subList(0, n);
        popItems.forEach(i -> itemService.updatePrimaryItem(i));
        return popItems;
    }

    /**
     * get a certain number of the least popular items
     */
    public List<Item> getCandidatesToRemove(List<Item> items, int n) {
        List<Item> sortedByPopularity = itemService.getSortedListByPopularity(items);
        List<Item> candidatesToRemove = sortedByPopularity.subList(sortedByPopularity.size() - n, sortedByPopularity.size());
        candidatesToRemove.forEach(i -> itemService.updateCandidateToRemove(i));
        return candidatesToRemove;
    }

    /**
     * show 3 the most popular items among women
     */
    public void showWomensPopularItem() {
        System.out.println("Three the most popular goods among women are " +
                getPopularItems(orderedItemsService.getByGender(Gender.FEMALE), 3));
    }

    /**
     * show 3 the most popular goods during a particular weekend
     */
    public void showPopularItemByPeriod(LocalDate start, LocalDate end) {
        System.out.println("Tree the most popular goods in this period are " +
                getPopularItems(itemService.getPurchasesBetween(start, end), 3));
    }

    /**
     * show 3 items with poor sales performance (candidate to remove)
     */
    public void showCandidateToRemove() {
        System.out.println("Candidates to remove are " +
                getCandidatesToRemove(itemService.getAll(), 3));
    }

    public void writeToFilePrimaryItems() {
        fileUtils.writeItems(itemService.getPrimaryItems(), "primaryItems.csv");
    }

    public void writeToFileCandidatesToRemove() {
        fileUtils.writeItems(itemService.getCandidatesToRemove(), "candidateToRemove.csv");
    }
}
