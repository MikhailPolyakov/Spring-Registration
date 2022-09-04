package Spring_security.spring_security.util;


import Spring_security.spring_security.PersonServices.PersonDetailsServices;
import Spring_security.spring_security.models.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
@Component
public class PersonValidate implements Validator {

private final PersonDetailsServices personDetailsServices;
@Autowired
    public PersonValidate(PersonDetailsServices personDetailsServices) {
        this.personDetailsServices = personDetailsServices;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return Person.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
     Person person=(Person )target;
     try {
         personDetailsServices.loadUserByUsername(person.getUsername());
     }catch (UsernameNotFoundException ignored){
         return;
     }
     errors.rejectValue("username","такое имя пользователя уже существует");
    }
}
