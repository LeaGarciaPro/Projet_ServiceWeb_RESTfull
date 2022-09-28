package tp.repositories;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import tp.models.Partenaire;

@Repository
public interface PartenaireRepository extends JpaRepository<Partenaire, Integer>{
	
	@Query(value = "SELECT * FROM Partenaire WHERE idAgence = :id", nativeQuery = true)	
	ArrayList<Partenaire> findByIdAgence(@Param(value="id") int id);

}
