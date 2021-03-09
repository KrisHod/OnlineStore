package services;

import entities.Gender;
import services.ItemService;
import services.OrderedItemsService;

import java.time.LocalDate;

public class ReportService {
    private ItemService itemService;
    private OrderedItemsService orderedItemsService;

    public ReportService() {
        init();
    }

    private void init() {
        this.itemService = new ItemService();
        this.orderedItemsService = new OrderedItemsService();
    }

    /**
     * show what goods are the most popular among women
     */
    public void showWomensPopularItem() {
        System.out.println("Three the most popular goods among women are " +
                itemService.getThreePopular(orderedItemsService.getByGender(Gender.FEMALE)));
    }

    /**
     * show the most popular goods during a particular weekend
     */
    public void showPopularItemByPeriod(LocalDate start, LocalDate end) {
        System.out.println("Tree the most popular goods in this period are " +
                itemService.getThreePopular(itemService.getPurchasesBetween(start, end)));
    }

    /**
     * show items with poor sales performance (candidate to remove)
     */
    public void showCandidateToRemove() {
        System.out.println("Candidates to remove are " +
                itemService.getCandidatesToRemove(itemService.getAll()));
    }
}
