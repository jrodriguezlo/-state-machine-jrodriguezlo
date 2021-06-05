package com.imatia.statemachine.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.imatia.statemachine.model.Order;
import com.imatia.statemachine.statemachine.StateMachine;

@RestController
public class GreetingController {

    private static final String template = "Hello, %s!";

    @GetMapping("/api/greeting")
    public String greeting(@RequestParam(name = "name", required = false, defaultValue = "Stranger") String name) {
        return String.format(template, name);
    }
    private StateMachine stateMachine;
    @PostMapping("/order/tracking")
    public String tracking(@RequestBody Order order) {
    	stateMachine.listener(order);
		return null;
    	
    }
}
