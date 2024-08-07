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
import enums.PurchaseType;

public class SurplusFoodDAO {

    public boolean addSurplusFood(SurplusFoodDTO surplusFood) {
        String sql = "INSERT INTO surplus_food (surplus_id, food_id, retailer_id, listing_type, listing_date) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, surplusFood.getSurplusId());
            pstmt.setString(2, surplusFood.getFoodId());
            pstmt.setString(3, surplusFood.getRetailerId());
            pstmt.setString(4, surplusFood.getPurchaseType().name());
            pstmt.setDate(5, surplusFood.getListingDate());
            pstmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public SurplusFoodDTO getSurplusFoodById(String surplusId) {
        String sql = "SELECT * FROM surplus_food WHERE surplus_id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, surplusId);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                SurplusFoodDTO surplusFood = new SurplusFoodDTO();
                surplusFood.setSurplusId(rs.getString("surplus_id"));
                surplusFood.setFoodId(rs.getString("food_id"));
                surplusFood.setRetailerId(rs.getString("retailer_id"));
                surplusFood.setPurchaseType(PurchaseType.valueOf(rs.getString("listing_type")));
                surplusFood.setListingDate(rs.getDate("listing_date"));
                return surplusFood;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean updateSurplusFood(SurplusFoodDTO surplusFood) {
        String sql = "UPDATE surplus_food SET food_id = ?, retailer_id = ?, listing_type = ?, listing_date = ? WHERE surplus_id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, surplusFood.getFoodId());
            pstmt.setString(2, surplusFood.getRetailerId());
            pstmt.setString(3, surplusFood.getPurchaseType().name());
            pstmt.setDate(4, surplusFood.getListingDate());
            pstmt.setString(5, surplusFood.getSurplusId());
            pstmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean deleteSurplusFood(String surplusId) {
        String sql = "DELETE FROM surplus_food WHERE surplus_id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, surplusId);
            pstmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public List<SurplusFoodDTO> getAllSurplusFood() {
        List<SurplusFoodDTO> surplusFoods = new ArrayList<>();
        String sql = "SELECT * FROM surplus_food";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                SurplusFoodDTO surplusFood = new SurplusFoodDTO();
                surplusFood.setSurplusId(rs.getString("surplus_id"));
                surplusFood.setFoodId(rs.getString("food_id"));
                surplusFood.setRetailerId(rs.getString("retailer_id"));
                surplusFood.setPurchaseType(PurchaseType.valueOf(rs.getString("listing_type")));
                surplusFood.setListingDate(rs.getDate("listing_date"));
                surplusFoods.add(surplusFood);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return surplusFoods;
    }
}
