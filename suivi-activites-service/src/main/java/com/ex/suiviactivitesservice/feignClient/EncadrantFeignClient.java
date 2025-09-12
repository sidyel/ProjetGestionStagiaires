package com.ex.suiviactivitesservice.feignClient;

import com.ex.suiviactivitesservice.objet.Encadrant;
import com.ex.suiviactivitesservice.objet.Stagiaire;
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
