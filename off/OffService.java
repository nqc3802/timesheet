package com.example.timesheet.off;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OffService {
    @Autowired
    private OffRepository offRepository;

    public List<Off> getOffs() {
        return offRepository.findAll();
    }

    public void validateOffRequest(Off off) throws IllegalArgumentException {
        switch (off.getRequest_type()) {
            case "Onsite":
            case "Remote":
                if (off.getHour() != null || off.getLeave_type_id() != null) {
                    throw new IllegalArgumentException(
                            "Hour and leave_type_id must be null for Onsite and Remote request type");
                }
                break;
            case "Đi muộn":
            case "Về sớm":
                if (off.getHour() == null || off.getHour() == 0 || off.getHour() > 2 || off.getLeave_type_id() != null) {
                    throw new IllegalArgumentException(
                            "Hour must be greater than 0 and less than or equal to 2 and leave_type_id must be null for Đi muộn and Về sớm request type");
                }
                break;
            case "Off":
                if (off.getHour() != null || off.getLeave_type_id() == 0) {
                    throw new IllegalArgumentException(
                            "Hour must be null and leave_type_id must be greater than 0 for Off request type");
                }
                break;
            default:
                throw new IllegalArgumentException("Invalid request type");
        }
    }

    public Off addNewOff(Off off) {
        validateOffRequest(off);
        return offRepository.save(off);
    }

    public List<Off> getOffsByMonthAndYear(int user_id, int month, int year) {
        return offRepository.findOffsByMonthAndYear(user_id, month, year);
    }

    public List<Off> getOffsByMyProject(int prj_id) {
        return offRepository.findOffsByMyProject(prj_id);
    }
}
