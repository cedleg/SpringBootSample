package app.domainUser;

import app.domainRepository.Domain;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface UserRepository extends MongoRepository<User, String> {

    User findByFirstName(String firstName);
    List<User> findByLastName(String lastName);
    User findByAge(int age);
    List<User> findListByAge(int age);



    @Query("{lastName: { $regex: ?0 }, firstName: { $regex: ?1 } }")
    List<Domain> findByRegEx(String lastName, String firstName);
}
