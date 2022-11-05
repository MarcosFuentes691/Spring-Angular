package com.mafuentes.springboot.apirest.springbootapirest.models.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mafuentes.springboot.apirest.springbootapirest.models.entity.Client;

public interface IClientDao extends JpaRepository<Client, Long> {



}
