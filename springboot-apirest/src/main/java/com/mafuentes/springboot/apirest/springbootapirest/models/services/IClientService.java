package com.mafuentes.springboot.apirest.springbootapirest.models.services;

import java.util.List;

import com.mafuentes.springboot.apirest.springbootapirest.models.entity.Client;

public interface IClientService {
    
    public List<Client> findAll();
    
    public Client findById(Long id);
    
    public Client save(Client client);
    
    public void delete(Long id);

}
