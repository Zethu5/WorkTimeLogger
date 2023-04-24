package com.example.WorkTimeLogger.service;

import com.example.WorkTimeLogger.dao.EmployeeScheduleDao;
import com.example.WorkTimeLogger.model.EmployeeSchedule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class EmployeeScheduleService {
    private final EmployeeScheduleDao employeeScheduleDao;

    @Autowired
    public EmployeeScheduleService(@Qualifier("DAO") EmployeeScheduleDao employeeScheduleDao) {
        this.employeeScheduleDao = employeeScheduleDao;
    }

    public boolean insertEmployeeEnter(int employeeId) {
        return employeeScheduleDao.insertEmployeeEnter(employeeId);
    }

    public boolean insertEmployeeExit(int employeeId) {
        return employeeScheduleDao.insertEmployeeExit(employeeId);
    }

    public ArrayList<EmployeeSchedule> getAllEmployeeInfo() {
        return employeeScheduleDao.getAllEmployeeInfo();
    }

    public EmployeeSchedule getEmployeeInfoById(int employeeId) {
        return employeeScheduleDao.getEmployeeInfoById(employeeId);
    }
}
