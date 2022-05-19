package com.example.prodanog.controllers;

import com.example.prodanog.entity.Igra;
import com.example.prodanog.entity.IgraMapper;
import com.example.prodanog.repositories.IgraRepositore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainConttroller {
    @Autowired
    private IgraRepositore igraRepositore;
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @RequestMapping("/")
    public String home(){
        System.out.println(jdbcTemplate.query("select * from \"Igra\" where \"id\"=?",new IgraMapper(),5).toString());
        return "lol";
    }
}
