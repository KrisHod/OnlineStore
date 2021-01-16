package services;

import entities.Item;

import java.util.Comparator;
import java.util.List;

public class ItemService {

    public int getIdOfTheMostPopularGoods(CustomerService customerService) {
        List<Integer> idListOfFemalePurchases = customerService.getIdListOfFemalePurchases();
        if (idListOfFemalePurchases == null) {
            return 0;
        }
        idListOfFemalePurchases.sort(Comparator.naturalOrder());

        int previous = idListOfFemalePurchases.get(0);
        int popular = idListOfFemalePurchases.get(0);
        int count = 1;
        int maxCount = 1;

        for (int i = 1; i < idListOfFemalePurchases.size(); i++) {
            if (idListOfFemalePurchases.get(i) == previous)
                count++;
            else {
                if (count > maxCount) {
                    popular = idListOfFemalePurchases.get(i - 1);
                    maxCount = count;
                }
                previous = idListOfFemalePurchases.get(i);
                count = 1;
            }
        }

        return count > maxCount ? idListOfFemalePurchases.get(idListOfFemalePurchases.size() - 1) : popular;
    }

    public String getTitleOfGoodsById(CustomerService customerService) {
        int idOfTheMostPopularGoods = getIdOfTheMostPopularGoods(customerService);
        for (Item item : Item.getItemList()) {
            if (item.getId() == idOfTheMostPopularGoods) {
                return item.getTitle();
            }
        }
        return null;
    }
}
