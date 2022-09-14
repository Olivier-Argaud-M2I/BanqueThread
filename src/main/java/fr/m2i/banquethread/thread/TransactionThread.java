package fr.m2i.banquethread.thread;

import fr.m2i.banquethread.entities.Compte;
import fr.m2i.banquethread.entities.Transaction;
import fr.m2i.banquethread.repositories.CompteRepository;
import fr.m2i.banquethread.repositories.TransactionRepository;

public class TransactionThread implements Runnable {

    TransactionRepository transactionRepository;
    CompteRepository compteRepository;
    Compte compte;
    Transaction transaction;

    public TransactionThread(TransactionRepository transactionRepository, CompteRepository compteRepository,Compte compte,Transaction transaction){
        this.transactionRepository = transactionRepository;
        this.compteRepository = compteRepository;
        this.compte = compte;
        this.transaction = transaction;
    }

    @Override
    public void run() {

//        System.out.println("on arrive au traitement de la transaction");

        synchronized (compte){

            compte.setSolde(compte.getSolde()+transaction.getMontant());
            compte = compteRepository.save(compte);
            System.out.println("le compte "+compte.getName()+" passe désormais à "+compte.getSolde()+" suite a l'action de "+transaction.getTitle() +" de "+transaction.getMontant());
            if(!transaction.getReccuring()){
                transactionRepository.delete(transaction);
            }

        }

        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }
}
