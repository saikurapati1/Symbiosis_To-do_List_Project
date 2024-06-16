package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class MainApplication {
    public static void main(String[] args) {
        SpringApplication.run(MainApplication.class, args);
    }
}

@Controller
class TaskController {
    private List<Task> tasks = new ArrayList<>();

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("tasks", tasks);
        return "index";
    }

    @GetMapping("/add")
    public String addTaskForm(Model model) {
        model.addAttribute("task", new Task());
        return "add-task";
    }

    @PostMapping("/add")
    public String addTask(@ModelAttribute Task task) {
        tasks.add(task);
        return "redirect:/";
    }

    @GetMapping("/update/{index}")
    public String updateTaskForm(@PathVariable int index, Model model) {
        model.addAttribute("task", tasks.get(index));
        model.addAttribute("index", index);
        return "update-task";
    }

    @PostMapping("/update/{index}")
    public String updateTask(@PathVariable int index, @ModelAttribute Task task) {
        tasks.set(index, task);
        return "redirect:/";
    }

    @GetMapping("/delete/{index}")
    public String deleteTask(@PathVariable int index) {
        tasks.remove(index);
        return "redirect:/";
    }
}
