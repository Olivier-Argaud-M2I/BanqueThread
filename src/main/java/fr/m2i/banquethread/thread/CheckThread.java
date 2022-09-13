package fr.m2i.banquethread.thread;

import fr.m2i.banquethread.entities.Transaction;
import fr.m2i.banquethread.repositories.CompteRepository;
import fr.m2i.banquethread.repositories.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.SQLOutput;
import java.util.List;

public class CheckThread implements Runnable {


    TransactionRepository transactionRepository;
    CompteRepository compteRepository;

    public CheckThread(TransactionRepository transactionRepository, CompteRepository compteRepository){
        this.transactionRepository = transactionRepository;
        this.compteRepository = compteRepository;
    }

    @Override
    public void run() {

        while (true){

//            System.out.println("coucou");

            List<Transaction> transactions = transactionRepository.findAll();
            if(transactions.size()<1){
                System.out.println("pas de transaction en attente de traitement");
            }

            for (Transaction transaction:transactions) {
                System.out.println("on traite une nouvelle transaction");
                new Thread(new TransactionThread(transactionRepository,compteRepository,transaction)).start();
            }

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
