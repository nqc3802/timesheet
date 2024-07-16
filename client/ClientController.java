package com.example.timesheet.client;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
public class ClientController {
    @Autowired
    private ClientService clientService;

    @GetMapping("/clients")
    public List<Client> getClients() {
        return clientService.getClients();
    }

    @PostMapping("/client/add")
    public Client addNewClient(@RequestBody Client client) {
        return clientService.addNewClient(client);
    }

    @PutMapping("/client/{id}")
    public Client editClient(@PathVariable int id, @RequestBody Client client) {
        return clientService.editClient(client, id);
    }

    @GetMapping("/client/search/{keyword}")
    public List<Client> search(@PathVariable String keyword) {
        return clientService.search(keyword);
    }

    @DeleteMapping("/client/{id}")
    public Client deleteClient(@PathVariable int id) {
        return clientService.deleteClient(id);
    }

}
