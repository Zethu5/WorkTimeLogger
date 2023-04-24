package com.example.WorkTimeLogger.dao;

import com.example.WorkTimeLogger.model.EmployeeSchedule;

import java.util.ArrayList;

public interface EmployeeScheduleDao {
    boolean insertEmployeeEnter(int employeeId);

    boolean insertEmployeeExit(int employeeId);

    ArrayList<EmployeeSchedule> getAllEmployeeInfo();

    EmployeeSchedule getEmployeeInfoById(int employeeId);
}
