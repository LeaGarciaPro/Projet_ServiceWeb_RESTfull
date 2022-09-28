package tp.repositories;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tp.models.Offre;

@Repository
public interface OffreRepository extends JpaRepository<Offre, Integer>{}
