package fr.m2i.banquethread;

import fr.m2i.banquethread.entities.Compte;
import fr.m2i.banquethread.repositories.CompteRepository;
import fr.m2i.banquethread.repositories.TransactionRepository;
import fr.m2i.banquethread.thread.DepenseThread;
import fr.m2i.banquethread.thread.SalaireThread;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
public class BanqueThreadApplication implements CommandLineRunner {

    @Autowired
    CompteRepository compteRepository;

    @Autowired
    TransactionRepository transactionRepository;

    public static void main(String[] args) {
        SpringApplication.run(BanqueThreadApplication.class, args);
    }

    @Override
    public void run(String... args){


        List<Compte> comptes = compteRepository.findAll();

        Thread salaire = new Thread(new SalaireThread(transactionRepository,compteRepository,comptes));
        Thread depense = new Thread(new DepenseThread(transactionRepository,compteRepository,comptes));

        salaire.start();
        depense.start();

    }
}
