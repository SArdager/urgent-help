package kz.kdlolymp.gynecology.repositories;

import kz.kdlolymp.gynecology.entity.Visit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Component
@Repository
public interface VisitRepository extends JpaRepository<Visit, Long> {

    Optional<Visit> findById(Long id);

    List<Visit> findAllByUserId(Long userId);

    List<Visit> findAllByRegionId(int regionId);

}
