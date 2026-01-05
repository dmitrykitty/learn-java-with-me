package com.dnikitin;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/software-engineers")
public class SoftwareEngineerController {

    private final SoftwareEngineerService softwareEngineerService;

    public SoftwareEngineerController(SoftwareEngineerService softwareEngineerService) {
        this.softwareEngineerService = softwareEngineerService;
    }

    @GetMapping
    public List<SoftwareEngineer> getSoftwareEngineers(){
        return softwareEngineerService.getSoftwareEngineers();

    }

    @GetMapping("{id}")
    public SoftwareEngineer getSoftwareEngineerById(@PathVariable Integer id){
        return softwareEngineerService.getSoftwareEngineersById(id);
    }

    @PostMapping
    public void addSoftwareEngineer(@RequestBody SoftwareEngineer softwareEngineer){
        softwareEngineerService.insertSoftwareEngineer(softwareEngineer);
    }
}
