package tp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tp.models.Hotel;

@Repository
public interface HotelRepository extends JpaRepository<Hotel, Integer>{

}
