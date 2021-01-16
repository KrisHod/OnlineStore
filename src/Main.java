import entities.Customer;
import entities.Gender;
import entities.Item;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Main {
    public static void main(String[] args) {
        Customer chara = new Customer("Chara Pastrana", LocalDate.of(1954,4,26), "7592 College Dr.Fishers, IN 46037", Gender.FEMALE, "(815) 203-5480", new int[] {2,4,12}, LocalDate.of(2017,6, 1));
        Customer lauralee = new Customer("Lauralee Spadaro", LocalDate.of(1954,4,26), "9674 Primrose Dr. Crofton, MD 21114", Gender.FEMALE, "(781) 939-5956", new int[] {1,3}, LocalDate.of(2017,6, 2));
        Customer bill = new Customer("Bill Connor", LocalDate.of(1963,7,7), "353 Lilac Road Malden, MA 02148", Gender.MALE, "(554) 478-8654", new int[] {5,6,10}, LocalDate.of(2017,6, 8));
        Customer nikki = new Customer("Nikki Sturges", LocalDate.of(1964,8,11), "457 St Paul Street Utica, NY 13501", Gender.FEMALE, "(284) 755-7619", new int[] {11}, LocalDate.of(2017,6, 9));
        Customer shaunta = new Customer("Shaunta Mcgeorge", LocalDate.of(1966,9,10), "7710 Glenwood Lane Centreville, VA 20120", Gender.FEMALE, new int[] {12,14}, LocalDate.of(2017,6, 10));

        Item cauliflower = new Item(1, "cauliflower", 14,"Smokey Bones", LocalDateTime.of(2017, 6, 10, 22,50));
        Item applePieSpice = new Item(2, "apple pie spice", 1,"Scores", LocalDateTime.of(2017, 6, 11, 18,9));
        Item lettuce = new Item(3, "lettuce", 11,"Houlihan's", LocalDateTime.of(2017, 6, 6,12 ,54));
        Item greenBeans = new Item(4, "green beans", 71,"Sizzler", LocalDateTime.of(2017, 7, 25, 21,28));
        Item mapleSyrup = new Item(5, "maple syrup", 82,"Lone Star Steakhouse & Saloon", LocalDateTime.of(2017, 7, 21, 9,31));
    }
}
