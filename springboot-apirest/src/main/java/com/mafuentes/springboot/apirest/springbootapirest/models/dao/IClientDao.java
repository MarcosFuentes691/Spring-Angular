package com.mafuentes.springboot.apirest.springbootapirest.models.dao;

import org.springframework.data.repository.CrudRepository;

import com.mafuentes.springboot.apirest.springbootapirest.models.entity.Client;

public interface IClientDao extends CrudRepository<Client, Long> {



}
