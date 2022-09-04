package Spring_security.spring_security.Controllers;

import Spring_security.spring_security.security.PersonDetails;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/hello")
public class Hellocontroller {

@GetMapping("")
    public String hello(){
    return "hello";
}

@GetMapping("/showUserInfo")
    public String showUserInfo(){
    //аутентивуфруемя и перейдам по этому адресу
    //из сесси достанем пользователя он будет помещен в поток (getAuthentication)
    //задаункастим объект принципал persondetails ,которым в AuthProvider мы положили в токен
    //и на это клкссе persondetails уже есть метод getPerson
    Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
   PersonDetails personDetails=(PersonDetails) authentication.getPrincipal() ;
   System.out.println(personDetails.getPerson());
   return "hello";
}
}
