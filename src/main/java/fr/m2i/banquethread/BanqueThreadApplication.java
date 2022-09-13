package fr.m2i.banquethread;

import fr.m2i.banquethread.repositories.CompteRepository;
import fr.m2i.banquethread.repositories.TransactionRepository;
import fr.m2i.banquethread.thread.CheckThread;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class BanqueThreadApplication implements CommandLineRunner {

    @Autowired
    private ApplicationContext applicationContext;

    @Autowired
    CompteRepository compteRepository;

    @Autowired
    TransactionRepository transactionRepository;

    public static void main(String[] args) {
        SpringApplication.run(BanqueThreadApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        Thread checkThread = new Thread(new CheckThread(transactionRepository,compteRepository));

        applicationContext.getAutowireCapableBeanFactory().autowireBean(checkThread);

        checkThread.start();
    }
}
