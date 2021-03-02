package repository;

import entities.Item;
import utils.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;

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
}
