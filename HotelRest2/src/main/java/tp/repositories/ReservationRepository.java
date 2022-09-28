package tp.repositories;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import tp.models.Reservation;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Integer>{
	
	@Query(value = "SELECT * FROM reservation WHERE chambre = :id", nativeQuery = true)	
	ArrayList<Reservation> findReservationDeChambre(@Param(value="id") int id);

}
