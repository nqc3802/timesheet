package com.example.timesheet.client;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.timesheet.project.ProjectRepository;

@Service
public class ClientService {
    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private ProjectRepository projectRepository;

    public List<Client> getClients() {
        return clientRepository.findAll();
    }

    public Client addNewClient(Client client) {
        return clientRepository.save(client);
    }

    public Client editClient(Client client, int id) {
        Client currentClient = clientRepository.findById(id);
        if (currentClient == null) {
            Client messageClient = new Client();
            messageClient.setName("Client not found");
            return messageClient;
        }
        currentClient.setName(client.getName());
        currentClient.setCode(client.getCode());
        currentClient.setAddress(client.getAddress());
        return clientRepository.save(currentClient);
    }

    public List<Client> search(String keyword) {
        return clientRepository.findByNameContainingOrAddressContaining(keyword, keyword);
    }

    @Transactional
    public void deleteClient(int id) {
        projectRepository.deleteByCustomer_id(id);
        clientRepository.deleteById(id);
        System.out.println("Client deleted");
        // throw new RuntimeException("Failed to delete client");
    }

}
