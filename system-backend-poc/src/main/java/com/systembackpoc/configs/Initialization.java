package com.systembackpoc.configs;

import com.systembackpoc.entities.*;
import com.systembackpoc.enums.PayStatus;
import com.systembackpoc.enums.PayType;
import com.systembackpoc.enums.SendStatusBalance;
import com.systembackpoc.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

@Configuration
@Profile("test")
public class Initialization implements CommandLineRunner {

    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");

    @Autowired
    private ClientsRepository clientsRepository;
    @Autowired
    private SegmentsRepository segmentsRepository;
    @Autowired
    private TextsRepository textsRepository;
    @Autowired
    private RecordsRepository recordsRepository;
    @Autowired
    private PaymentsRepository paymentsRepository;
    @Autowired
    private UsersRepository usersRepository;

    @Override
    public void run(String... args) throws Exception {
         Clients cli1 = new Clients(
                null,"Osmar Telão",
                "osmartelao@gmail.com",
                "909989899",
                true,
                0.0,
                PayType.C,
                SendStatusBalance.N);
        Clients cli2 = new Clients(
                null, "Onofre Apótolos",
                "apotolosonofre@hotmail.com",
                "9978988987",
                true,
                10.0,
                PayType.M,
                SendStatusBalance.S);
        Clients cli3 = new Clients(
                null, "Carlos Amasteu",
                "amasteu.carlos@gmail.com",
                "9978233427",
                false,
                0.0,
                PayType.M,
                SendStatusBalance.N);
        Clients cli4 = new Clients(
                null, "Andira Amasteu",
                "andira@outlook.com",
                "9978233427",
                false,
                0.0,
                PayType.M,
                SendStatusBalance.N);
        clientsRepository.saveAll(Arrays.asList(cli1, cli2, cli3, cli4));

        Users user1 = new Users(cli1, cli1.getName().toUpperCase(),"telao123");
        Users user2 = new Users(cli2, cli2.getName().toUpperCase(),"onofre99");
        Users user3 = new Users(cli3, cli3.getName().toUpperCase(), "123amasteu");
        Users user4 = new Users(cli4, cli4.getName().toUpperCase(), "andirinha99");
        usersRepository.saveAll(Arrays.asList(user1, user2, user3, user4));

        Segments seg1 = new Segments(null, "mercado", 30.90);
        Segments seg2 = new Segments(null, "loja", 30.90);
        Segments seg3 = new Segments(null, "associação", 30.90);
        Segments seg4 = new Segments(null, "lancheria", 30.90);
        Segments seg5 = new Segments(null, "empresa", 30.90);
        segmentsRepository.saveAll(Arrays.asList(seg1, seg2, seg3, seg4, seg5));

        Texts text1 = new Texts(null, "CENTENARIO", "Super Centenário", seg1.getPrice(), "c://centenario.txt", cli1, seg1);
        Texts text2 = new Texts(null, "BRUVINI", "Mercado Bruvini", seg1.getPrice(), "", cli2, seg1);
        Texts text3 = new Texts(null, "GTC", "GTC Internet", seg5.getPrice(), "", cli1, seg5);
        Texts text4 = new Texts(null, "JBS", "Indústria JBS", seg5.getPrice(), "", cli4, seg5);
        Texts text5 = new Texts(null, "GLORIA", "Mercado Glória", seg1.getPrice(), "", cli3, seg1);
        textsRepository.saveAll(Arrays.asList(text1, text2, text3, text4, text5));

        Date date = new Date();
        Records rec1 = new Records(null, date, "c:/...", .0, PayStatus.U, text1);
        Records rec2 = new Records(null, date, "d:/test/...",.0, PayStatus.U, text2);
        Records rec3 = new Records(null, date, "x:/texts",10.0, PayStatus.U, text3);
        Records rec4 = new Records(null, date, "y:/...",.0, PayStatus.U, text4);
        Records rec5 = new Records(null, date, "y:/...",10.0, PayStatus.O, text2);
        Records rec6 = new Records(null, date, "y:/rest/...",.0, PayStatus.U, text1);
        Records rec7 = new Records(null, date, "y:/rest/...",.0, PayStatus.P, text5);
        recordsRepository.save(rec1);
        // Thread.sleep(5000);
        recordsRepository.save(rec2);
        // Thread.sleep(5000);
        recordsRepository.save(rec3);
        // Thread.sleep(5000);
        recordsRepository.save(rec4);
        // Thread.sleep(5000);
        recordsRepository.save(rec5);
        // Thread.sleep(5000);
        recordsRepository.save(rec6);
        // Thread.sleep(5000);
        recordsRepository.save(rec7);

      //  Payments pay1 = new Payments(null, date, 85.00, cli1);
      //  paymentsRepository.saveAll(Arrays.asList(pay1));
    }
}
