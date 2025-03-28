package org.jboss.as.quickstarts.kitchensink.controller;

import java.io.InputStream;

import org.jboss.as.quickstarts.kitchensink.data.MemberRepository;
import org.jboss.as.quickstarts.kitchensink.model.Member;
import org.jboss.as.quickstarts.kitchensink.service.MemberRegistration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import jakarta.validation.Valid;
import java.util.List;
import jakarta.annotation.PostConstruct;

//@Controller
@SpringBootApplication
@RequestMapping("/members")
@RestController
@ComponentScan(basePackages = "org.jboss.as.quickstarts.kitchensink")
@EnableJpaRepositories(basePackages = "org.jboss.as.quickstarts.kitchensink.data")
public class MemberController extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(MemberController.class, args);
    }

     @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private MemberRegistration memberRegistration;

    @ModelAttribute("newMember")
    public Member newMember() {
        return new Member();
    }

    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        return "register";
    }

    @GetMapping("/allMembers")
    public List<Member> getAllMembers() {
        System.out.println("I'm here cazzo");
        return memberRepository.findAllByOrderByNameAsc();
    }

    @PostConstruct
    public void initNewMember() {
        newMember();
    }

    @PostMapping("/register")
    public String register( @Valid @RequestBody Member newMember, RedirectAttributes redirectAttributes) {
        System.out.println("newMember is  " + newMember);
        try {
            memberRegistration.register(newMember);
            redirectAttributes.addFlashAttribute("message", "Registered! Registration successful");
            return "redirect:/register";
        } catch (Exception e) {
            String errorMessage = getRootErrorMessage(e);
            redirectAttributes.addFlashAttribute("errorMessage", errorMessage);
            System.out.println(errorMessage);
            return "redirect:/failed";
        }
    }

    private String getRootErrorMessage(Exception e) {
        // Default to general error message that registration failed.
        String errorMessage = "Registration failed. See server log for more information";
        if (e == null) {
            // This shouldn't happen, but return the default messages
            return errorMessage;
        }
        // Start with the exception and recurse to find the root cause
        Throwable t = e;
        while (t != null) {
            // Get the message from the Throwable class instance
            errorMessage = t.getLocalizedMessage();
            t = t.getCause();
        }
        // This is the root cause message
        return errorMessage;
    }


}