package fr.m2i.banquethread.thread;

import fr.m2i.banquethread.entities.Compte;
import fr.m2i.banquethread.entities.Transaction;
import fr.m2i.banquethread.repositories.CompteRepository;
import fr.m2i.banquethread.repositories.TransactionRepository;

import java.util.List;

public class SalaireThread implements Runnable {


    TransactionRepository transactionRepository;
    CompteRepository compteRepository;
    List<Compte> comptes;

    public SalaireThread(TransactionRepository transactionRepository, CompteRepository compteRepository, List<Compte> comptes){
        this.transactionRepository = transactionRepository;
        this.compteRepository = compteRepository;
        this.comptes = comptes;
    }


    @Override
    public void run() {

        while (true){
//            Compte compte = transaction.getCompte();
//            System.out.println("on arrive au traitement des salaires");

            for (Compte compte:comptes) {

                List<Transaction> salaires = transactionRepository.findAllByCompteAndTitle(compte,"salaire");

                if(salaires.size()>0){
//                    System.out.println("actions sur le compte "+compte.getName() +" : ");
                }
                else{
//                    System.out.println("pas d'actions a éffectuer sur le compte "+compte.getName());
                }


                for (Transaction transaction:salaires) {
                    Thread thread = new Thread(new TransactionThread( transactionRepository,  compteRepository, compte, transaction));
                    thread.start();
                }

            }

            try {
                Thread.sleep(15000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }


        }

    }
}
