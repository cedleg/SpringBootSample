package app;


import app.domainCustomer.Customer;
import app.domainCustomer.CustomerRepository;
import app.domainRepository.Domain;
import app.domainRepository.DomainRepository;
import app.domainUser.User;
import app.domainUser.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.domain.Example;

import java.util.List;
import java.util.Optional;


@SpringBootApplication
public class Application implements CommandLineRunner {

    private static final Logger log = LoggerFactory.getLogger(Application.class);

    @Autowired
    private CustomerRepository repository;

    @Autowired
    private DomainRepository repoDomain;

    @Autowired
    private UserRepository repoUser;

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        repository.deleteAll();
        repoDomain.deleteAll();
        repoUser.deleteAll();

        // save a couple of customers
        repository.save(new Customer("Alice", "Smith"));
        repository.save(new Customer("Bob", "Smith"));

        // fetch all customers
        System.out.println("Customers found with findAll():");
        System.out.println("-------------------------------");
        for (Customer customer : repository.findAll()) {
            System.out.println(customer);
        }
        System.out.println();

        // fetch an individual customer
        System.out.println("app.domainCustomer.Customer found with findByFirstName('Alice'):");
        System.out.println("--------------------------------");
        System.out.println(repository.findByFirstName("Alice"));

        System.out.println("Customers found with findByLastName('Smith'):");
        System.out.println("--------------------------------");
        for (Customer customer : repository.findByLastName("Smith")) {
            System.out.println(customer);
        }

        System.out.println("--------------------------------");
        System.out.println("app.domainRepository.Domain found with findFirstByDomain('www.toto.fr'):");

        repoDomain.save(new Domain("www.toto.fr", false));
        Optional<Domain> obj = repoDomain.findOne(Example.of(new Domain("www.toto.fr", false)));
        System.out.println(obj.isPresent() ? obj.get().getDomain() : "Object empty");

        Domain obj2 = repoDomain.findByDomain("www.toto.fr");
        System.out.println(obj2.getDomain());
        System.out.println("--------------------------------");

        repoUser.save(new User("Pere", "KIKI", "LosAngels", 50));
        repoUser.save(new User("Fils", "KIKI", "LosAngels", 30));
        repoUser.save(new User("Soeur", "KIKI", "LosAngels", 30));
        repoUser.save(new User("Frere", "KIKI", "LosAngels", 20));
        repoUser.save(new User("Michael", "Jackson", "LosAngels", 55));
        User user = repoUser.findByFirstName("Frere");
        List<User> users = repoUser.findByLastName("KIKI");
        List<User> userAll = repoUser.findAll();
        User userAge = repoUser.findByAge(20);
        userAge.setAge(35);
        repoUser.save(userAge);
        for (User u : users) {
            if(u.getLastName().equals("KIKI"))
                u.setAge(u.getAge()+10);
        }
        repoUser.saveAll(users);
        System.out.println(user.toString());
        System.out.println(userAll.toString());
        System.out.println(userAge.toString());
        System.out.println(users.toString());
        //repoUser.findByRegEx("KIKI", "Soeur").forEach(usr -> log.info(usr.toString()));
        System.out.println("--------------------------------");
        repoUser.findAll().forEach(userItem -> log.info(userItem.toString()));

        System.out.println("--------------------------------");

    }

}
