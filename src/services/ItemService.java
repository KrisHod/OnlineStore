package services;

import entities.Customer;
import entities.Item;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import static entities.Customer.getCustomerList;
import static entities.Item.getItemList;

public class ItemService {

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

    public String getTitleOfGoodsById(int idOfTheMostPopularGoods) {
        for (Item item : getItemList()) {
            if (item.getId() == idOfTheMostPopularGoods) {
                return item.getTitle();
            }
        }
        return null;
    }

    //  the most popular goods during a particular weekend (passed in as a param)
    public List<Integer> getIdListOfPurchasesSoldOnParticularPeriod(LocalDate startDate, LocalDate endDate) {
        List<Integer> idListOfPurchases = new ArrayList<>();
        for (Customer cus : getCustomerList()) {
            if (cus.getDateOfLastPurchase().equals(startDate) || cus.getDateOfLastPurchase().equals(endDate) ||
                    (cus.getDateOfLastPurchase().isAfter(startDate) && cus.getDateOfLastPurchase().isBefore(startDate))) {
                for (int id : cus.getLastPurchases()) {
                    idListOfPurchases.add(id);
                }
            }
        }
        return idListOfPurchases;
    }


}
