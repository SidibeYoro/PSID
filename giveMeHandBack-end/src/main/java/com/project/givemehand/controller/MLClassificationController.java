package com.project.givemehand.controller;

import com.project.givemehand.services.MLClasfficiationService;
import com.project.givemehand.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@CrossOrigin
@RequestMapping(value = "/api/ML")
public class MLClassificationController {
    @Autowired
    private MLClasfficiationService serviceML;

    @RequestMapping(path = "/detectNegativeClassForText/{text}", method = RequestMethod.GET)
    public boolean detectNegativeClassForText(@PathVariable String text) throws IOException {


        return serviceML.detectNegativeClassForText(text);


    }

}
