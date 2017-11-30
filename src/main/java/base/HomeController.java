package base;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import mail.CodeController;
import mail.Mail;
import mail.MailMail;
@Controller // so framework can recognize this as a controller class
@RequestMapping("/")
public class HomeController {

    @GetMapping
    public String index() { return "TestPrakt.html"; }
    
//    @PutMapping
//    public String test(@RequestBody String a) {
//    	System.out.println(a);
//    	return a;
//    }

}