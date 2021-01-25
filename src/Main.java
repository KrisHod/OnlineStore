import entities.Gender;
import utils.FileUtils;
import validation.CustomerValidation;
import validation.ItemValidation;

import java.nio.file.Paths;

public class Main {
    public static void main(String[] args) {
//        Customer chara = new Customer("Chara Pastrana", LocalDate.of(1954, 4, 26), "7592 College Dr.Fishers, IN 46037",
//                Gender.FEMALE, "(815) 203-5480", new int[]{2, 3, 12}, LocalDate.of(2017, 6, 1));
//        Customer lauralee = new Customer("Lauralee Spadaro", LocalDate.of(1954, 4, 26), "9674 Primrose Dr. Crofton, MD 21114",
//                Gender.FEMALE, "(781) 939-5956", new int[]{1, 3}, LocalDate.of(2017, 6, 2));
//        Customer bill = new Customer("Bill Connor", LocalDate.of(1963, 7, 7), "353 Lilac Road Malden, MA 02148",
//                Gender.MALE, "(554) 478-8654", new int[]{5, 6, 10}, LocalDate.of(2017, 6, 8));
//        Customer nikki = new Customer("Nikki Sturges", LocalDate.of(1964, 8, 11), "457 St Paul Street Utica, NY 13501",
//                Gender.FEMALE, "(284) 755-7619", new int[]{11, 12}, LocalDate.of(2017, 6, 9));
//        Customer shaunta = new Customer("Shaunta Mcgeorge", LocalDate.of(1966, 9, 10), "7710 Glenwood Lane Centreville, VA 20120",
//                Gender.FEMALE, new int[]{12, 14}, LocalDate.of(2017, 6, 10));
//
//        Item cauliflower = new Item(1, "cauliflower", 14, "Smokey Bones", LocalDateTime.of(2017, 6, 10, 22, 50));
//        Item applePieSpice = new Item(2, "apple pie spice", 1, "Scores", LocalDateTime.of(2017, 6, 11, 18, 9));
//        Item lettuce = new Item(3, "lettuce", 11, "Houlihan's", LocalDateTime.of(2017, 6, 6, 12, 54));
//        Item greenBeans = new Item(4, "green beans", 71, "Sizzler", LocalDateTime.of(2017, 7, 25, 21, 28));
//        Item mapleSyrup = new Item(12, "maple syrup", 82, "Lone Star Steakhouse & Saloon", LocalDateTime.of(2017, 7, 21, 9, 31));


//        CustomerService customerService = new CustomerService();
//        ItemService itemService = new ItemService();
//
//        System.out.println("The most popular goods among women is " + itemService.getTitleOfGoodsById(itemService.getIdOfTheMostPopularGoods(customerService.getIdListOfPurchasesByGender(Gender.FEMALE))));
//        System.out.println("The most popular goods during a particular weekend is " + itemService.getTitleOfGoodsById
//                (itemService.getIdOfTheMostPopularGoods(itemService.getPurchasesBetween
//                        (LocalDate.of(2017, 6, 1), LocalDate.of(2017, 6, 2)))));

    //    FileUtils.readFromFile(Paths.get("C:\\Users\\Zver\\IdeaProjects\\OnlineStore\\src\\utils\\items.csv"));
        ItemValidation itemValidation = new ItemValidation();
        System.out.println(itemValidation.isValidId("2"));
     //   System.out.println(itemValidation.isValidDateFormat("25.07.2017 21:28"));
     //   System.out.println(itemValidation.isValidDateFormat("25.07.2017 21:28"));

        CustomerValidation customerValidation = new CustomerValidation();
    //    System.out.println(customerValidation.isValidGender(Gender.SOMETHING_ELSE));
    //    System.out.println(customerValidation.isValidPhoneNumber("(284) 755-7619"));
    //    System.out.println(customerValidation.isValidDOB("4 May 1987"));
    }
}
