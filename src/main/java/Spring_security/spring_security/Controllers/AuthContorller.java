package Spring_security.spring_security.Controllers;


import Spring_security.spring_security.PersonServices.RegistrationService;
import Spring_security.spring_security.models.Person;
import Spring_security.spring_security.util.PersonValidate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping("/auth")
public class AuthContorller {
private final PersonValidate personValidate;
private final RegistrationService registrationService;

@Autowired
    public AuthContorller(PersonValidate personValidate, RegistrationService registrationService) {
        this.personValidate = personValidate;
    this.registrationService = registrationService;
}


    @GetMapping("/login")
    public String loginpage(){
return "auth/login";
    }

@GetMapping("/registration")
    public String registrationPage(Model model, @ModelAttribute("person")Person person){
    return "auth/registration";
    }


    @PostMapping("/registration")
    public String Performregistr(@ModelAttribute("person") @Valid Person person,
                                 BindingResult bindingResult){
personValidate.validate(person,bindingResult);
if(bindingResult.hasErrors()) return"/auth/registration";
registrationService.registr(person);
return"redirect:/auth/login";
}



}
