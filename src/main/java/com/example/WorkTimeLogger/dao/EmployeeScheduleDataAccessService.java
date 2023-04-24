package com.example.WorkTimeLogger.dao;

import com.example.WorkTimeLogger.model.EmployeeSchedule;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;

@Repository("DAO")
public class EmployeeScheduleDataAccessService implements EmployeeScheduleDao{

    private static HashMap<Integer, EmployeeSchedule> EmployeesSchedulesDB = new HashMap<>();

    @Override
    public boolean insertEmployeeEnter(int employeeId) {
        if(EmployeesSchedulesDB.containsKey(employeeId)) {
            EmployeeSchedule employeeSchedule = EmployeesSchedulesDB.get(employeeId);
            if(employeeSchedule.insertEmployeeEnter()) {
                EmployeesSchedulesDB.replace(employeeId, employeeSchedule);
                return true;
            }
            return false;
        }

        EmployeeSchedule employeeSchedule = new EmployeeSchedule(employeeId);
        employeeSchedule.insertEmployeeEnter();
        EmployeesSchedulesDB.put(employeeId, employeeSchedule);
        return true;
    }

    @Override
    public boolean insertEmployeeExit(int employeeId) {
        if(!EmployeesSchedulesDB.containsKey(employeeId))
            return false;

        EmployeeSchedule employeeSchedule = EmployeesSchedulesDB.get(employeeId);
        if(employeeSchedule.insertEmployeeExit()) {
            EmployeesSchedulesDB.replace(employeeId, employeeSchedule);
            return true;
        }
        return false;
    }

    @Override
    public ArrayList<EmployeeSchedule> getAllEmployeeInfo() {
        return new ArrayList<EmployeeSchedule>(EmployeesSchedulesDB.values());
    }

    @Override
    public EmployeeSchedule getEmployeeInfoById(int employeeId) {
        return EmployeesSchedulesDB.get(employeeId);
    }
}
