package com.revature.nabnak.util.web;


import com.revature.nabnak.member.dto.requests.NewRegistrationRequest;
import com.revature.nabnak.member.dto.response.MemberResponse;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/test")
public class TestController {

    // Methods in a Controler for Spring MVC are just methods....with a particular annotation
    @GetMapping
    public @ResponseBody String test(){
        return "Welcome to the wonderful world of Spring MVC";
    }

    @GetMapping("/1")
    public @ResponseBody String test1(){
        return "Hey look another get mapping that ez";
    }

    @GetMapping("/{email}")
    public @ResponseBody String test2(@PathVariable String email){
        return "You're requesting the following email: " + email;
    }

    @GetMapping("/2")
    public @ResponseBody String test3(){
        return "Haha test 3 returns from the /test/2 URI";
    }

    @PostMapping
    // Jackson is being leveraged under the hood to handle our Response & Request
    // @ResponseBody is required BEFORE the return Data type for the method signature
    // @RequestBody is required BEFORE any parameter that will be provided in the body of an HTTP
    // Request and assigned to that type for Jackson to convert from JSON to JAva Object
    public @ResponseBody NewRegistrationRequest postRegistration(@RequestBody NewRegistrationRequest newRegistrationRequest){
        return newRegistrationRequest;
    }


    @GetMapping("/rex")
    public void runTimeEx(){
        throw new RuntimeException();
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public @ResponseBody String exception(Exception ex){
        return "Error has occured in our program, please check logs " + ex.getClass().getName();
    }

}
