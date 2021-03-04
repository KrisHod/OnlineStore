package repository;

import entities.Item;
import entities.Order;
import utils.DBUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrderedItemsRepository {
    ItemRepository itemRepository = new ItemRepository();

    public void add(Order order) {
        String sql = "INSERT INTO orderedItems (orderId, itemId) values(?, ?)";

        try (Connection con = DBUtil.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setInt(1, order.getId());

            for (Item i : order.getItemList()) {
                stmt.setInt(2, i.getId());
                stmt.addBatch();
            }
            stmt.executeBatch();
        } catch (SQLException ex) {
            System.out.println("Error " + ex.getMessage());
        }
    }

    public List<Item> getById(int orderId) {
        List<Item> items = new ArrayList<>();

        String sql = "SELECT * FROM orderedItems WHERE id=" + orderId;
        try (Connection con = DBUtil.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Item item = itemRepository.getById(rs.getInt("itemId"));
                items.add(item);
            }
        } catch (SQLException ex) {
            System.out.println("Error " + ex.getMessage());
        }
        return items;
    }
}
