package com.mygradle.commons.controller;

import com.mygradle.commons.service.InMemoryProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;
import java.util.Map;

@Controller
public class UIController {
    private InMemoryProductService inMemoryProductService;

    @Autowired
    public void setInMemoryProductService(InMemoryProductService inMemoryProductService){
        this.inMemoryProductService = inMemoryProductService;
    }

    @RequestMapping("/th")
    public String templatePage(Model model){
        Map<String, Object> pageMap = new HashMap<>();
        pageMap.put("color", "red");
        pageMap.put("name", "han");
        pageMap.put("price", 3000);
        model.addAttribute("product", pageMap);
        return "th2";
    }

    @RequestMapping("/th2")
    public String templatePage2(Model model){
        model.addAttribute("products", inMemoryProductService.getProductList());
        return "th3";
    }
}
