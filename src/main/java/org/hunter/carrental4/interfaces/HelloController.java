package org.hunter.carrental4.interfaces;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/")
public class HelloController {
    @GetMapping(value = "/checkHealth")
    public String checkHealth() {

        return "hello hunter";
    }
}
