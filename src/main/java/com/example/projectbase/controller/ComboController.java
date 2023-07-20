package com.example.projectbase.controller;

import com.example.projectbase.domain.dto.request.ComboCreateDTO;
import com.example.projectbase.domain.dto.request.ProductCreateDTO;
import com.example.projectbase.service.ComboService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/combo")
public class ComboController {
    @Autowired
    private ComboService comboService;
    @PostMapping("/create")
    public ResponseEntity<?> create(@Valid @ModelAttribute ComboCreateDTO comboCreateDTO, BindingResult bindingResult){
        return comboService.createCombo(comboCreateDTO,bindingResult);
    }
    @GetMapping("/findAll")
    public ResponseEntity<?> findAll(){
        return comboService.findAll();
    }

}
