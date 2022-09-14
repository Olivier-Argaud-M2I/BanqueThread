package fr.m2i.banquethread.thread;

import fr.m2i.banquethread.entities.Compte;
import fr.m2i.banquethread.entities.Transaction;
import fr.m2i.banquethread.repositories.CompteRepository;
import fr.m2i.banquethread.repositories.TransactionRepository;

import java.util.List;

public class CreditThread implements Runnable {


    TransactionRepository transactionRepository;
    CompteRepository compteRepository;
    List<Compte> comptes;

    public CreditThread(TransactionRepository transactionRepository, CompteRepository compteRepository, List<Compte> comptes){
        this.transactionRepository = transactionRepository;
        this.compteRepository = compteRepository;
        this.comptes = comptes;
    }


    @Override
    public void run() {

        while (true){

//            System.out.println("on arrive au traitement des depenses");

            for (Compte compte:comptes) {
                List<Transaction> depense = transactionRepository.findCredit(compte.getId());

                if(depense.size()>0){
//                    System.out.println("actions sur le compte "+compte.getName() +" : ");
                }
                else{
//                    System.out.println("pas d'actions a éffectuer sur le compte "+compte.getName());
                }

                for (Transaction transaction:depense) {
                    Thread thread = new Thread(new TransactionThread( transactionRepository,  compteRepository, compte, transaction));
                    thread.start();
                }

            }

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }


        }

    }
}
