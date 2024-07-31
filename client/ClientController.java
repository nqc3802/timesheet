package com.example.timesheet.client;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RequestMapping("api/v1/client")
@PreAuthorize("hasRole('ROLE_Admin')")
@RestController
public class ClientController {
    @Autowired
    private ClientService clientService;

    @GetMapping
    public List<Client> getClients() {
        return clientService.getClients();
    }

    @PostMapping
    public Client addNewClient(@RequestBody Client client) {
        return clientService.addNewClient(client);
    }

    @PutMapping("/{id}")
    public Client editClient(@PathVariable int id, @RequestBody Client client) {
        return clientService.editClient(client, id);
    }

    @GetMapping("/search/{keyword}")
    public List<Client> search(@PathVariable String keyword) {
        return clientService.search(keyword);
    }

    @DeleteMapping("/{id}")
    public void deleteClient(@PathVariable int id) {
        clientService.deleteClient(id);
    }

}
