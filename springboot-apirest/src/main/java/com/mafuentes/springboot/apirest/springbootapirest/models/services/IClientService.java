package com.mafuentes.springboot.apirest.springbootapirest.models.services;

import java.util.List;

import com.mafuentes.springboot.apirest.springbootapirest.models.entity.Client;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IClientService {
    
    public List<Client> findAll();

    public Page<Client> findAll(Pageable pageable);

    public Client findById(Long id);
    
    public Client save(Client client);
    
    public void delete(Long id);

}
