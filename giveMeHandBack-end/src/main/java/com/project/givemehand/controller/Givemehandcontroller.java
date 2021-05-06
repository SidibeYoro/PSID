package com.project.givemehand.controller;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Givemehandcontroller
{
    //Récupérer la liste des produits
    @RequestMapping(value = "/Hand", method = RequestMethod.GET)
    public String listeProduits()
    {
        return "Hello We are Give ME hand";
    }
}
