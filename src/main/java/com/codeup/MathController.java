package com.codeup;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
class MathController {

    @GetMapping("/math/{x}/{operator}/{y}")
    @ResponseBody
    public String math(@PathVariable int x, @PathVariable String operator, @PathVariable int y) {
        switch (operator){
            case "plus":
                return ("Result: " + (x + y));
            case "minus":
                return ("Result: " + (x - y));
            case "times":
                return ("Result: " + (x * y));
            case "divided":
                return ("Result: " + (x / y));
            default:
                return ("Valid operators are plus, minus, times, and divided.");
        }
    }
}