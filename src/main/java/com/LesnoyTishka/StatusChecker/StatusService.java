package com.LesnoyTishka.StatusChecker;

import com.LesnoyTishka.StatusChecker.entities.DefaultStatusChecker;
import com.LesnoyTishka.StatusChecker.entities.AbstractStatusChecker;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Service
public class StatusService {
    List<AbstractStatusChecker> statusCheckerList;

    AbstractStatusChecker cryptoStatusChecker = new DefaultStatusChecker();
    AbstractStatusChecker geekBrainsStatusChecker = new DefaultStatusChecker("GeekBrains", "https://geekbrains.ru");

    @PostConstruct
    public void init() {
        statusCheckerList = new ArrayList<>();
        statusCheckerList.add(cryptoStatusChecker);
        statusCheckerList.add(geekBrainsStatusChecker);
    }

    public void checkStatus() {
        for (AbstractStatusChecker checker : statusCheckerList) {
            checker.checkStatus();
        }
    }

    public List<AbstractStatusChecker> getStatusCheckerList() {
        return statusCheckerList;
    }

    public void createNewStatusChecker(String title, String url) {
        AbstractStatusChecker statusChecker = new DefaultStatusChecker(title, url);
        statusCheckerList.add(statusChecker);
    }

    public void delete(String title) {
        statusCheckerList.removeIf(checker -> checker.getTitle().equals(title));
    }

    public void deleteAll() {
        statusCheckerList.clear();
    }
}
