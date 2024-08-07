//Class: CST8288//
//Section: 033//
//Professor: Islam Gomaa//
//Assignment: Final Project//
//Date: 23/7/2024//
//Authors: Jake Elliott, Navjot Kaur, Heeseok Yang, Scott Valair//

package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import enums.UserType;

public class UserDAO {

    private static final Logger LOGGER = Logger.getLogger(UserDAO.class.getName());

    public boolean authenticate(String email, String password) {
        String sql = "SELECT * FROM user WHERE user_email = ? AND user_password = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, email);
            pstmt.setString(2, password);
            ResultSet rs = pstmt.executeQuery();
            boolean authenticated = rs.next();
            LOGGER.info("User authenticated: " + authenticated);
            return authenticated;
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Authentication failed", e);
        }
        return false;
    }

    public String getUserType(String email) {
        String sql = "SELECT user_type FROM user WHERE user_email = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, email);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                String userType = rs.getString("user_type");
                LOGGER.info("User type retrieved: " + userType);
                return userType;
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Failed to get user type", e);
        }
        return null;
    }

    public boolean addUser(UserDTO user) {
        String sql = "INSERT INTO user (user_id, first_name, last_name, user_password, user_email, join_date, user_type) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, user.getUserId());
            pstmt.setString(2, user.getFirstName());
            pstmt.setString(3, user.getLastName());
            pstmt.setString(4, user.getUserPassword());
            pstmt.setString(5, user.getUserEmail());
            pstmt.setDate(6, user.getJoinDate());
            pstmt.setString(7, user.getUserType().name());

            LOGGER.info("Executing query: " + pstmt.toString());
            int result = pstmt.executeUpdate();
            LOGGER.info("Insert result: " + result);
            return result > 0;
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Failed to add user", e);
        }
        return false;
    }

    public UserDTO getUserById(String userId) {
        String sql = "SELECT * FROM user WHERE user_id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, userId);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                UserDTO user = new UserDTO();
                user.setUserId(rs.getString("user_id"));
                user.setFirstName(rs.getString("first_name"));
                user.setLastName(rs.getString("last_name"));
                user.setUserPassword(rs.getString("user_password"));
                user.setUserEmail(rs.getString("user_email"));
                user.setJoinDate(rs.getDate("join_date"));
                user.setUserType(UserType.valueOf(rs.getString("user_type").toUpperCase()));
                LOGGER.info("User retrieved: " + user);
                return user;
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Failed to get user by ID", e);
        }
        return null;
    }

    public boolean updateUser(UserDTO user) {
        String sql = "UPDATE user SET first_name = ?, last_name = ?, user_password = ?, user_email = ?, join_date = ?, user_type = ? WHERE user_id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, user.getFirstName());
            pstmt.setString(2, user.getLastName());
            pstmt.setString(3, user.getUserPassword());
            pstmt.setString(4, user.getUserEmail());
            pstmt.setDate(5, user.getJoinDate());
            pstmt.setString(6, user.getUserType().name());
            pstmt.setString(7, user.getUserId());
            int result = pstmt.executeUpdate();
            LOGGER.info("Update result: " + result);
            return result > 0;
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Failed to update user", e);
        }
        return false;
    }

    public boolean deleteUser(String userId) {
        String sql = "DELETE FROM user WHERE user_id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, userId);
            int result = pstmt.executeUpdate();
            LOGGER.info("Delete result: " + result);
            return result > 0;
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Failed to delete user", e);
        }
        return false;
    }

    public List<UserDTO> getAllUsers() {
        List<UserDTO> users = new ArrayList<>();
        String sql = "SELECT * FROM user";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                UserDTO user = new UserDTO();
                user.setUserId(rs.getString("user_id"));
                user.setFirstName(rs.getString("first_name"));
                user.setLastName(rs.getString("last_name"));
                user.setUserPassword(rs.getString("user_password"));
                user.setUserEmail(rs.getString("user_email"));
                user.setJoinDate(rs.getDate("join_date"));
                user.setUserType(UserType.valueOf(rs.getString("user_type").toUpperCase()));
                users.add(user);
            }
            LOGGER.info("Total users retrieved: " + users.size());
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Failed to get all users", e);
        }
        return users;
    }
}
