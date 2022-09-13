package fr.m2i.banquethread.repositories;



import fr.m2i.banquethread.entities.Compte;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompteRepository extends JpaRepository<Compte,Long> {
}
