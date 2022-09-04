package Spring_security.spring_security.PersonServices;

import Spring_security.spring_security.models.Person;
import Spring_security.spring_security.repository.PeopleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RegistrationService  {

private final PeopleRepository peopleRepository;
@Autowired
    public RegistrationService(PeopleRepository peopleRepository) {
        this.peopleRepository = peopleRepository;
    }

    @Transactional
    public void registr(Person person){
peopleRepository.save(person);
    }
}
