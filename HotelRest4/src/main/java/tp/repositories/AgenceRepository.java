package tp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tp.models.Agence;

@Repository
public interface AgenceRepository extends JpaRepository<Agence, Integer>{

}
