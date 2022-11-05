package com.mafuentes.springboot.apirest.springbootapirest.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;
import com.mafuentes.springboot.apirest.springbootapirest.models.entity.Client;
import com.mafuentes.springboot.apirest.springbootapirest.models.services.IClientService;

import javax.validation.Valid;

@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping("/api")
public class ClientController {
    
    @Autowired
    private IClientService clientService;

    @GetMapping("/clients")
    public List<Client> index() {
        return clientService.findAll();
    }

    @GetMapping("/clients/page/{page}")
    public Page<Client> index(@PathVariable Integer page) {
        Pageable pageable = PageRequest.of(page, 4);
        return clientService.findAll(pageable);
    }

    @GetMapping("/clients/{id}")
    public ResponseEntity<?> show (@PathVariable Long id) {
        Client client = null;
        Map<String, Object> response = new HashMap<>();
        try {
            client = clientService.findById(id);
        } catch (DataAccessException e) {
            response.put("message", "Error when querying the database");
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        if (client == null) {
            response.put("message", "The client ID: ".concat(id.toString().concat(" doesn't exist in the database")));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Client>(client, HttpStatus.OK);
    }

    @PostMapping("/clients")
    public ResponseEntity<?> create(@Valid @RequestBody Client client, BindingResult result) {
        Client clientNew = null;
        Map<String, Object> response = new HashMap<>();
        if (result.hasErrors()) {
            List<String> errors = result.getFieldErrors()
                .stream()
                .map(err -> "The field '" + err.getField() + "' " + err.getDefaultMessage())
                .collect(Collectors.toList());
            response.put("errors", errors);
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
        }
        try {
            clientNew = clientService.save(client);
        } catch (DataAccessException e) {
            response.put("message", "Error when inserting the client in the database");
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        response.put("message", "The client has been created successfully");
        response.put("client", clientNew);
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
    }

    @PutMapping("/clients/{id}")
    public ResponseEntity<?> update(@Valid @RequestBody Client client,BindingResult result, @PathVariable Long id) {
        Client clientActual = clientService.findById(id);
        Client clientUpdated = null;
        Map<String, Object> response = new HashMap<>();
        if (result.hasErrors()) {
            List<String> errors = result.getFieldErrors()
                .stream()
                .map(err -> "The field '" + err.getField() + "' " + err.getDefaultMessage())
                .collect(Collectors.toList());
            response.put("errors", errors);
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
        }
        if (clientActual == null) {
            response.put("message", "Error: the client ID: ".concat(id.toString().concat(" doesn't exist in the database")));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
        }
        try {
            clientActual.setName(client.getName());
            clientActual.setSurname(client.getSurname());
            clientActual.setEmail(client.getEmail());
            clientUpdated = clientService.save(clientActual);
        } catch (DataAccessException e) {
            response.put("message", "Error when updating the client in the database");
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        response.put("message", "The client has been updated successfully");
        response.put("client", clientUpdated);
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
    }

    @DeleteMapping("/clients/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        Map<String, Object> response = new HashMap<>();
        try {
            clientService.delete(id);
        } catch (DataAccessException e) {
            response.put("message", "Error when deleting the client in the database");
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        response.put("message", "The client has been deleted successfully");
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
    }
}