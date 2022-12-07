package com.i2i.ibus.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.i2i.ibus.model.PickupPoint;

public interface PickupPointRepository extends JpaRepository<PickupPoint, Integer> {

    @Query(value = "Select * from pickup_point where bus_id = :id and isDeleted = false", nativeQuery = true)
    public List<PickupPoint> findAllByBusId(int busId);

    @Query(value = "Select * from pickup_point where bus_id = :id and city = :city and isDeleted = false", nativeQuery = true)
    public List<PickupPoint> findAllByBusIdAndCity(int busId, String city);

}
