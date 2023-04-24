package com.example.WorkTimeLogger.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class EmployeeSchedule {
    private int id;
    private List<List<String>> dates;

    public EmployeeSchedule(int employeeId) {
        this.id = employeeId;
        this.dates = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public List<List<String>> getDates() {
        return dates;
    }

    // employee entry is valid if:
    // * it's his first entry ever
    // * it's first entry of the day -
    //      meaning the last occurrence of dates had an entry and an exit
    private boolean isValidEnter() {
        return dates.size() == 0 || dates.get(dates.size() - 1).size() == 2;
    }

    // employee exit is valid only if an entry already occurred and that's only possible
    // if dates is not empty
    private boolean isValidExit() {
        return dates.size() > 0 && dates.get(dates.size() - 1).size() == 1;
    }

    public boolean insertEmployeeEnter() {
        if(isValidEnter()) {
            List<String> tmp = new ArrayList<>();
            DateTimeFormatter customFormatter = DateTimeFormatter.ofPattern("dd-MM-uuuu-HH:mm");
            tmp.add(LocalDateTime.now().format(customFormatter));
            dates.add(tmp);
            return true;
        }
        return false;
    }

    public boolean insertEmployeeExit() {
        if(isValidExit()) {
            List<String> tmp = dates.get(dates.size() - 1);
            DateTimeFormatter customFormatter = DateTimeFormatter.ofPattern("dd-MM-uuuu-HH:mm");
            tmp.add(LocalDateTime.now().format(customFormatter));
            dates.set(dates.size() - 1, tmp);
            return true;
        }
        return false;
    }
}
