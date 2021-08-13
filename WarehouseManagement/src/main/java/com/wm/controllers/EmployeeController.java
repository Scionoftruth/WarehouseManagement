package com.wm.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.NoArgsConstructor;

@RestController
@RequestMapping(value="/employee")
@NoArgsConstructor
//@AllArgConstructor(onConstructor=@__(@Autowired))
public class EmployeeController {

}
