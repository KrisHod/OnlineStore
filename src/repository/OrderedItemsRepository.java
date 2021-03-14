package repository;

import entities.Item;
import entities.Order;
import utils.DBUtil;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class OrderedItemsRepository {
    private ItemRepository itemRepository;
    private OrderRepository orderRepository;

    public OrderedItemsRepository() {
        init();
    }

    private void init() {
        this.itemRepository = new ItemRepository();
        this.orderRepository = new OrderRepository();
    }

    public void add(Order order) {
        String sql = "INSERT INTO orderedItems (date, orderId, itemId) values(?, ?, ?)";

        try (Connection con = DBUtil.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {
                for (Item item : order.getItemList()) {

                stmt.setDate(1, Date.valueOf(order.getDateOrder()));
                stmt.setInt(2, getByDate(order.getDateOrder()).getId());
                stmt.setInt(3, item.getId());
                stmt.addBatch();
            }
                stmt.executeBatch();

        } catch (SQLException ex) {
            System.out.println("Error " + ex.getMessage());
        }
    }

    public Order getByDate(LocalDate localDate) {
        Order order = null;
        String sql = "SELECT * FROM `order` WHERE date=?";
        try (Connection con = DBUtil.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setDate(1, Date.valueOf(localDate));
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                order = orderRepository.getOrder(rs);
            }
        } catch (SQLException ex) {
            System.out.println("Error " + ex.getMessage());
        }
        return order;
    }

    public List<Item> getById(int orderId) {
        List<Item> items = new ArrayList<>();
        String sql = "SELECT * FROM orderedItems WHERE orderId=" + orderId;

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

    private Order getOrder(ResultSet rs) throws SQLException {
        LocalDate dateOrder = rs.getDate("date").toLocalDate();
        int orderId = rs.getInt("orderId");
        List<Item> items = getById(orderId);

        return new Order(orderId, items, dateOrder);
    }

    public List<Order> getAll() {
        List<Order> orders = new ArrayList<>();
        String sql = "SELECT * FROM orderedItems ";

        try (Connection con = DBUtil.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                orders.add(getOrder(rs));
            }
        } catch (SQLException ex) {
            System.out.println("Error " + ex.getMessage());
        }
        return orders;
    }
}
