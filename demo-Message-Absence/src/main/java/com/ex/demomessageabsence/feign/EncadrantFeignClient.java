package com.ex.demomessageabsence.feign;


import com.ex.demomessageabsence.entite.Encadrant;
import com.ex.demomessageabsence.entite.Stagiaire;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "CLIENT-SERVICE")
public interface EncadrantFeignClient {


    @GetMapping(path = "/encadrants/{id}")
    public Encadrant getEncadrant(@PathVariable Integer id);

    @GetMapping(path = "/encadrants")
    public List<Encadrant> getEncadrants();

    @GetMapping(path = "/stagiaires/{id}")
    public Stagiaire getStagiaire(@PathVariable Integer id);

    @GetMapping(path = "/stagiaires")
    public List<Stagiaire> getStagiaires();



}
