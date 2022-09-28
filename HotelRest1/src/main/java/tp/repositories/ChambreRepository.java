package tp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tp.models.Chambre;

@Repository
public interface ChambreRepository extends JpaRepository<Chambre, Integer>{

}
