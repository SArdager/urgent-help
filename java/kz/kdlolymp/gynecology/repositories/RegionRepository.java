package kz.kdlolymp.gynecology.repositories;

import kz.kdlolymp.gynecology.entity.Region;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;

@Component
@Repository
public interface RegionRepository extends JpaRepository<Region, Integer> {

    Region findById(int id);

    Region findByRegionName(String regionName);

    List<Region> findAll();

}
