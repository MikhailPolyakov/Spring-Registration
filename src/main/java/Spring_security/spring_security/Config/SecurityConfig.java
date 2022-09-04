package Spring_security.spring_security.Config;


import Spring_security.spring_security.PersonServices.PersonDetailsServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import sun.reflect.annotation.ExceptionProxy;


@EnableWebSecurity
public class SecurityConfig   {

    private final PersonDetailsServices personDetailsServices;
@Autowired
    public SecurityConfig(PersonDetailsServices personDetailsServices) {
    this.personDetailsServices = personDetailsServices;
    }

    //настраивает аутентификацию И даем понять какой auth provider Использовать
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(personDetailsServices);
    }

    //для указания каким способос шифровать
    @Bean
    public PasswordEncoder getPasswordEncoder(){
    return NoOpPasswordEncoder.getInstance();
    }


    //настриваем форму для логина
    //какая статнцпчка за вход за ошибки
    //конфигурируем авторизацию
    //process_login" можем указать любой адрес куда отправляем  с формы
    ///auth/login?error параметр придет в наш контроллер и далее на предсталвение
    //antMatcher указывает,что доступа всем людям
    //permit all указывает,что всем доступна
    //anyRequest().authenticated(). указывает что все остальные странциы досутпны только
    //после аутентификации
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
//отключаем токены для запросов(защита от межсайтовой подлелки запросов)
    http.csrf().disable().
            authorizeRequests().antMatchers("/auth/login","/auth/registration","/error").permitAll().
            anyRequest().authenticated().and().
            formLogin().loginPage("/auth/login")
            .loginProcessingUrl("/process_login")
            .defaultSuccessUrl("/hello",true)
            .failureUrl("/auth/login?error");
    return http.build();
    }


}
