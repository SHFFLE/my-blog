package com.codeup;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class DiceController {

    @GetMapping("/dice-roll")
    public String prerollRedirect(){
        return "dice-roll";
    }

    @GetMapping("/dice-roll/{roll}")
    public String roll(@PathVariable int roll, Model model){
        model.addAttribute("roll", roll);
        model.addAttribute("random", (int) Math.ceil(Math.random() * 6));
        return "dice-roll-result";
    }

    @GetMapping("/index")
    public String index(){
        return "index";
    }

}
