package fr.m2i.banquethread.thread;

import fr.m2i.banquethread.entities.Compte;
import fr.m2i.banquethread.entities.Transaction;
import fr.m2i.banquethread.repositories.CompteRepository;
import fr.m2i.banquethread.repositories.TransactionRepository;

public class TransactionThread implements Runnable {


    TransactionRepository transactionRepository;
    CompteRepository compteRepository;
    Transaction transaction;

    public TransactionThread(TransactionRepository transactionRepository, CompteRepository compteRepository,Transaction transaction){
        this.transactionRepository = transactionRepository;
        this.compteRepository = compteRepository;
        this.transaction = transaction;
    }


    @Override
    public void run() {

        Compte compte = transaction.getCompte();
        System.out.println("on arrive au traitement de la transaction");

        synchronized (compte){

            compte.setSolde(compte.getSolde()+transaction.getMontant());
            compte = compteRepository.save(compte);
            System.out.println("le compte "+compte.getName()+" passe désormais à "+compte.getSolde());
            transactionRepository.delete(transaction);
        }

    }
}
