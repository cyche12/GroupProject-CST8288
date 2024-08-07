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

public class FoodItemDAO {

	public boolean addFoodItem(FoodItemDTO foodItem) {
		String sql = "INSERT INTO food_item (food_id, food_name, food_quantity, food_cost, food_expiry, retailer_id) VALUES (?, ?, ?, ?, ?, ?)";
		try (Connection conn = DatabaseConnection.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setString(1, foodItem.getFoodId());
			pstmt.setString(2, foodItem.getFoodName());
			pstmt.setInt(3, foodItem.getFoodQuantity());
			pstmt.setDouble(4, foodItem.getFoodCost());
			pstmt.setDate(5, foodItem.getFoodExpiry());
			pstmt.setString(6, foodItem.getRetailerId());
			pstmt.executeUpdate();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public FoodItemDTO getFoodItemById(String foodId) {
		String sql = "SELECT * FROM foodItem WHERE food_id = ?";
		try (Connection conn = DatabaseConnection.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setString(1, foodId);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				FoodItemDTO foodItem = new FoodItemDTO();
				foodItem.setFoodId(rs.getString("food_id"));
				foodItem.setFoodName(rs.getString("food_name"));
				foodItem.setFoodQuantity(rs.getInt("food_quantity"));
				foodItem.setFoodCost(rs.getDouble("food_cost"));
				foodItem.setFoodExpiry(rs.getDate("food_expiry"));
				foodItem.setRetailerId(rs.getString("retailer_id"));
				return foodItem;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public boolean updateFoodItem(FoodItemDTO foodItem) {
		String sql = "UPDATE food_item SET food_name = ?, food_quantity = ?, food_cost = ?, food_expiry = ?, retailer_id = ? WHERE food_id = ?";
		try (Connection conn = DatabaseConnection.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setString(1, foodItem.getFoodName());
			pstmt.setInt(2, foodItem.getFoodQuantity());
			pstmt.setDouble(3, foodItem.getFoodCost());
			pstmt.setDate(4, foodItem.getFoodExpiry());
			pstmt.setString(5, foodItem.getRetailerId());
			pstmt.setString(6, foodItem.getFoodId());
			pstmt.executeUpdate();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public boolean deleteFoodItem(String foodId) {
		String sql = "DELETE FROM food_item WHERE food_id = ?";
		try (Connection conn = DatabaseConnection.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setString(1, foodId);
			pstmt.executeUpdate();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public List<FoodItemDTO> getAllFoodItems() {
		List<FoodItemDTO> foodItems = new ArrayList<>();
		String sql = "SELECT * FROM food_name";
		try (Connection conn = DatabaseConnection.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql);
				ResultSet rs = pstmt.executeQuery()) {
			while (rs.next()) {
				FoodItemDTO foodItem = new FoodItemDTO();
				foodItem.setFoodId(rs.getString("food_id"));
				foodItem.setFoodName(rs.getString("food_name"));
				foodItem.setFoodQuantity(rs.getInt("food_quantity"));
				foodItem.setFoodCost(rs.getDouble("food_cost"));
				foodItem.setFoodExpiry(rs.getDate("food_expiry"));
				foodItem.setRetailerId(rs.getString("retailer_id"));
				foodItems.add(foodItem);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return foodItems;
	}
}
