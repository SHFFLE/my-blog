package com.codeup;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.List;

@Controller
public class FormattingController {
    @GetMapping("/formatting")
    public String formatting(Model model){
        List<Object> objectList = new ArrayList<>();
        objectList.add(new TestObject("PS4", 249.99, "Amazon"));
        objectList.add(new TestObject("PC", 849.99, "Newegg"));
        objectList.add(new TestObject("XBone", 299.99, "Amazon"));
        objectList.add(new TestObject("WiiU", 229.99, "Nowhere :("));
        objectList.add(new TestObject("Vita", 169.99, "Why??"));
        objectList.add(new TestObject("3DS", 159.99, "Amazon"));
        objectList.add(new TestObject("2DS", 139.99, "EW"));
        model.addAttribute("objectList", objectList);
        return "formatting";
    }

}

class TestObject {
    public String description;
    public double price;
    public String available;

    public TestObject(String description, double price, String available){
        this.description = description;
        this.price = price;
        this.available = available;
    }
}