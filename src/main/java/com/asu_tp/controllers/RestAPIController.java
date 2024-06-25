package com.asu_tp.controllers;

import com.asu_tp.service.CalculateService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/rest/")
@AllArgsConstructor
public class RestAPIController {

    @Autowired
    CalculateService calculateService;

    @PostMapping (produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> calcCharFreq(@RequestBody String str){
        String json = calculateService.getCharFreqJSON(str);
        if (json != null) return new ResponseEntity<>(json, HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
}
