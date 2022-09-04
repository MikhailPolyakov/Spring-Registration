package Spring_security.spring_security.security;


import Spring_security.spring_security.models.Person;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

//класс обертка над нашей сущностью
public class PersonDetails implements UserDetails {

    private final Person person;

    public PersonDetails(Person person) {
        this.person = person;
    }

//метод для авторизации полльзователя
    //тут будем получать роли,котоыре есть у пользователя и возвращать коллекцию тех прав
    //которая есть у пользователя
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }



    @Override
    public String getPassword() {
        return this.person.getPassword();
    }

    @Override
    public String getUsername() {
        return this.person.getUsername();
    }

    //просрочен иили нет
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    //заблокирован или нет
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }


    //пароль не просрочен
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }


    //аккаунт включен или выклыченю
    @Override
    public boolean isEnabled() {
        return true;
    }

    //для получения данных аутентифицированного пользвоателя
    public Person getPerson(){
        return this.person;
    }
}
