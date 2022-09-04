package Spring_security.spring_security.PersonServices;


import Spring_security.spring_security.models.Person;
import Spring_security.spring_security.repository.PeopleRepository;
import Spring_security.spring_security.security.PersonDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;


//чтобы Spring security знал,что сервси загружает пользвоателя нужно елаизвоать интерфейс
@Service
public class PersonDetailsServices implements UserDetailsService {


    private final PeopleRepository peopleRepository;

@Autowired
    public PersonDetailsServices(PeopleRepository peopleRepository) {
        this.peopleRepository = peopleRepository;
    }


    //таким образом даем понять springy securiyty,что этот сервис загружает пользователя по имени пользвоаетля
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Person> person=peopleRepository.findByUsername(username);
if(!person.isPresent()){
    throw new UsernameNotFoundException("User not found");
}

return new PersonDetails(person.get());
}
}
