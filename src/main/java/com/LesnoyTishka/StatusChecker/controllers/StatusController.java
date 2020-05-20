package com.LesnoyTishka.StatusChecker.controllers;

import com.LesnoyTishka.StatusChecker.StatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class StatusController {
    private final StatusService statusService;

    @Autowired
    public StatusController(StatusService statusService) {
        this.statusService = statusService;
    }

    @GetMapping("/status_checker")
    public String getStatusCheckerPage(Model model) {
        statusService.checkStatus();
        model.addAttribute("checkers", statusService.getStatusCheckerList());
        return "status_checker";
    }

    @GetMapping("/status_checker/new")
    public String getNewCheckerPage() {
        return "new_checker";
    }

    @PostMapping("/status_checker/add")
    public String addNewChecker(
            @RequestParam(name = "title") String title,
            @RequestParam(name = "url") String url
    ) {
        statusService.createNewStatusChecker(title, url);
        return "redirect:/status_checker";
    }

    @GetMapping("/status_checker/delete/{title}")
    public String deleteChecker(@PathVariable(name = "title") String title) {
        statusService.delete(title);
        return "redirect:/status_checker";
    }

    @GetMapping("/status_checker/deleteAll")
    public String deleteAllCheckers() {
        statusService.deleteAll();
        return "redirect:/status_checker";
    }
}
