package com.example.WorkTimeLogger.api;

import com.example.WorkTimeLogger.model.EmployeeSchedule;
import com.example.WorkTimeLogger.service.EmployeeScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class EmployeeScheduleController {
    private final EmployeeScheduleService employeeScheduleService;

    @Autowired
    public EmployeeScheduleController(EmployeeScheduleService employeeScheduleService) {
        this.employeeScheduleService = employeeScheduleService;
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleEnterException(Exception e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Failed operation");
    }

    @PostMapping(value = "/enter")
    public void insertEmployeeEnter(@RequestParam(value = "id") int employeeId) {
        if(!this.employeeScheduleService.insertEmployeeEnter(employeeId))
            throw new RuntimeException("Couldn't insert employee enter for employee id: " + employeeId);
    }

    @PostMapping(value = "/exit")
    public void insertEmployeeExit(@RequestParam(value = "id") int employeeId) {
        if(!this.employeeScheduleService.insertEmployeeExit(employeeId))
            throw new RuntimeException("Couldn't insert employee exit for employee id: " + employeeId);
    }

    @GetMapping(value = "/info")
    public List<EmployeeSchedule> getAllEmployeeInfo(Integer employeeId) {
        return employeeScheduleService.getAllEmployeeInfo();
    }

    @GetMapping(value = "/info", params = "id")
    public ResponseEntity<EmployeeSchedule> getEmployeeInfoById(@RequestParam(value = "id") Integer employeeId) {
        EmployeeSchedule employeeSchedule = employeeScheduleService.getEmployeeInfoById(employeeId);

        if(employeeSchedule == null)
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(employeeSchedule);
    }
}
