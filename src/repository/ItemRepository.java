package repository;

import entities.Item;
import utils.DBUtil;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ItemRepository {
    public void add(Item item) {
        String sql = "INSERT INTO item (id, title, code, producer, dateOfLastUpdate) values(?, ?, ?, ?, ?)";

        try (Connection con = DBUtil.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setInt(1, item.getId());
            stmt.setString(2, item.getTitle());
            stmt.setInt(3, item.getCode());
            stmt.setString(4, item.getProducer());
            stmt.setTimestamp(5, Timestamp.valueOf(item.getDateOfLastUpdate()));

            stmt.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("Error " + ex.getMessage());
        }
    }

    private Item getItem(ResultSet rs) throws SQLException {
        int id = rs.getInt("id");
        String title = rs.getString("title");
        int code = rs.getInt("code");
        String producer = rs.getString("producer");
        LocalDateTime dateOfLastUpdate = rs.getTimestamp("dateOfLastUpdate").toLocalDateTime();

        return new Item(id, title, code, producer, dateOfLastUpdate);
    }

    public Item getById(int id) {
        Item item = null;
        String sql = "SELECT * FROM item WHERE id=?";

        try (Connection con = DBUtil.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                item = getItem(rs);
            }
        } catch (SQLException ex) {
            System.out.println("Error " + ex.getMessage());
        }
        return item;
    }

    public List<Item> getAll() {
        List<Item> items = new ArrayList<>();
        String sql = "SELECT * FROM item ";

        try (Connection con = DBUtil.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                items.add(getItem(rs));
            }
        } catch (SQLException ex) {
            System.out.println("Error " + ex.getMessage());
        }
        return items;
    }

    public void updatePrimaryItem(Item item) {
        String sql = "UPDATE item SET primaryItem = ? WHERE id =" + item.getId();

        try (Connection con = DBUtil.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setBoolean(1, true);
            stmt.executeUpdate();

        } catch (SQLException ex) {
            System.out.println("Error " + ex.getMessage());
        }
    }

    public void updateCandidateToRemove(Item item) {
        String sql = "UPDATE item SET candidateToRemove = ? WHERE id =" + item.getId();

        try (Connection con = DBUtil.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setBoolean(1, true);
            stmt.executeUpdate();

        } catch (SQLException ex) {
            System.out.println("Error " + ex.getMessage());
        }
    }

    public List<Item> getItemsByPopularity(String sql) {
        List<Item> items = new ArrayList<>();

        try (Connection con = DBUtil.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setBoolean(1, true);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                items.add(getItem(rs));
            }
        } catch (SQLException ex) {
            System.out.println("Error " + ex.getMessage());
        }
        return items;
    }

    public List<Item> getPrimaryItems() {
        String sql = "SELECT * FROM item WHERE primaryItem = ?";
        return getItemsByPopularity(sql);
    }

    public List<Item> getCandidatesToRemove() {
        String sql = "SELECT * FROM item WHERE candidateToRemove = ?";
        return getItemsByPopularity(sql);
    }
}
