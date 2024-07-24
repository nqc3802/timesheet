package com.example.timesheet.position;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PositionService {
    @Autowired
    private PositionRepository positionRepository;

    public List<Position> getPositions() {
        return positionRepository.findAll();
    }

    public List<Position> search(String keyword) {
        return positionRepository.findByNameContainingOrShortnameContainingOrCodeContaining(keyword, keyword, keyword);
    }

    public Position addNewBranch(Position position) {
        return positionRepository.save(position);
    }

    public Position editPosition(int id, Position position) {
        Position currentPosition = positionRepository.findById(id);
        if (currentPosition == null) {
            Position messagePosition = new Position();
            messagePosition.setName("Position not found");
            return messagePosition;
        }
        currentPosition.setName(position.getName());
        currentPosition.setName(position.getShortname());
        currentPosition.setCode(position.getCode());
        currentPosition.setDescription(position.getDescription());
        return positionRepository.save(currentPosition);
    }

    public Position deletePosition(int id) {
        return positionRepository.deleteById(id);
    }

}
