package com.i2i.ibus.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.i2i.ibus.model.PickupPoint;

@Repository
public interface PickupPointRepository extends JpaRepository<PickupPoint, Integer> {

    List<PickupPoint> findAllByBusId(int busId);

    List<PickupPoint> findAllByBusIdAndCity(int busId, String city);

    Optional<PickupPoint> findAllByBusIdAndCityAndStopName(int busId, String city, String stopName);
    
    Optional<PickupPoint> findByBusIdAndCityAndLandMarkAndStopName(int busId, String city, String landMark, String stopName);
    
    @Query(value = "Select * from pickup_point where bus_id = :busId and city = :city and land_mark = :landMark and stop_name = :stopName and is_deleted = false and id != :id", nativeQuery = true)
    Optional<PickupPoint> findByBusIdAndCityAndLandmarkAndStopName(int busId, String city, String landMark, String stopName,int id);
}
