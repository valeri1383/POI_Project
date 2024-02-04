package com.poi.valeri_poi;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author dboce
 */
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PlaceDAO {
    
    private DBConnection dbConnection;

    public PlaceDAO() {
        this.dbConnection = new DBConnection();
    }

    public List<Place> getAllPlaces() {
        List<Place> places = new ArrayList<>();
        String sql = "SELECT * FROM PointsOfInterest";

        try (PreparedStatement statement = dbConnection.getConnection().prepareStatement(sql);
             ResultSet rs = statement.executeQuery()) {

            while (rs.next()) {
                int id = rs.getInt("point_of_interest_id");
                String location = rs.getString("location");
                TypeOfPlace type = TypeOfPlace.valueOf(rs.getString("type"));
                int likes = rs.getInt("likes");
                
                Place place = new Place(id, location, type, likes);
                places.add(place);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return places;
    }

    public Place getPlaceById(int id) {
        Place place = null;
        String sql = "SELECT * FROM PointsOfInterest WHERE point_of_interest_id = ?";

        try (PreparedStatement statement = dbConnection.getConnection().prepareStatement(sql)) {
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();

            if (rs.next()) {
                String location = rs.getString("location");
                TypeOfPlace type = TypeOfPlace.valueOf(rs.getString("type"));
                int likes = rs.getInt("likes");

                place = new Place(id, location, type, likes);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return place;
    }


}
