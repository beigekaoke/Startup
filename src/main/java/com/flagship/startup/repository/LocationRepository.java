package com.flagship.startup.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.flagship.startup.entity.Location;
import java.util.List;

@Repository
public interface LocationRepository extends JpaRepository<Location,Long> {
    List<Location> getLocationsByType(String type);
}