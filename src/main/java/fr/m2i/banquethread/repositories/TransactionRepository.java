package fr.m2i.banquethread.repositories;


import fr.m2i.banquethread.entities.Compte;
import fr.m2i.banquethread.entities.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction,Long> {

    public List<Transaction> findAllByCompte(Compte compte);

    public List<Transaction> findAllByCompteAndTitle(Compte compte,String title);

    public List<Transaction> findAllByCompteAndTitleNotLike(Compte compte,String title);

    @Query(value = "SELECT transaction FROM Transaction transaction " +
            "WHERE transaction.compte.id = :id " +
            "AND NOT transaction.title LIKE 'salaire'" +
            "AND transaction.montant >= 0 ")
    public List<Transaction> findCredit(Long id);

    @Query(value = "SELECT transaction FROM Transaction transaction " +
            "WHERE transaction.compte.id = :id " +
            "AND NOT transaction.title LIKE 'salaire'" +
            "AND transaction.montant < 0 ")
    public List<Transaction> findDepense(Long id);

}
