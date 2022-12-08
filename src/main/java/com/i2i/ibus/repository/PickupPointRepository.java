package com.i2i.ibus.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.i2i.ibus.model.PickupPoint;

@Repository
public interface PickupPointRepository extends JpaRepository<PickupPoint, Integer> {

    @Query(value = "Select * from pickup_point where bus_id = :busId and is_deleted = false", nativeQuery = true)
    public List<PickupPoint> findAllByBusId(int busId);

    @Query(value = "Select * from pickup_point where bus_id = :busId and city = :city and is_deleted = false", nativeQuery = true)
    public List<PickupPoint> findAllByBusIdAndCity(int busId, String city);

    @Query(value = "Select * from pickup_point where bus_id = :busId and city = :city and stop_name = :stopName and is_deleted = false", nativeQuery = true)
    public Optional<PickupPoint> findAllByBusIdCityAndStopName(int busId, String city, String stopName);
    
    @Query(value = "Select * from pickup_point where bus_id = :busId and city = :city and land_mark = :landMark and stop_name = :stopName and is_deleted = false", nativeQuery = true)
    public Optional<PickupPoint> findByBusIdAndCityAndLandmarkAndStopName(int busId, String city, String landMark, String stopName);
    
    @Query(value = "Select * from pickup_point where bus_id = :busId and city = :city and land_mark = :landMark and stop_name = :stopName and is_deleted = false and id != :id", nativeQuery = true)
    public Optional<PickupPoint> findByBusIdAndCityAndLandmarkAndStopName(int busId, String city, String landMark, String stopName,int id);
}
