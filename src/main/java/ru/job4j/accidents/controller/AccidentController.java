package ru.job4j.accidents.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import ru.job4j.accidents.model.Accident;
import ru.job4j.accidents.service.AccidentService;

import java.util.Optional;

@Controller
@AllArgsConstructor
public class AccidentController {
    private final AccidentService accidents;

    @GetMapping("/createAccident")
    public String viewCreateAccident() {
        return "createAccident";
    }

    @PostMapping("/saveAccident")
    public String save(@ModelAttribute Accident accident) {
        accidents.save(accident);
        return "redirect:/index";
    }

    @GetMapping("/{id}")
    public String viewEditAccident(@PathVariable int id, Model model) {
        Optional<Accident> optionalAccident = accidents.findById(id);
        if (optionalAccident.isEmpty()) {
            model.addAttribute("error", "Нарушение с указанным "
                    + "идентификатором не найдено");
            return "errors/404";
        }
        model.addAttribute("accident", optionalAccident);
        return "editAccident";
    }

    @PostMapping("/editAccident")
    public String update(@ModelAttribute Accident accident) {
        accidents.update(accident);
        return "redirect:/index";
    }
}