package ru.job4j.accidents.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.job4j.accidents.model.Accident;
import ru.job4j.accidents.model.AccidentType;
import ru.job4j.accidents.service.AccidentService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@AllArgsConstructor
@RequestMapping(name = "/accidents")
public class AccidentController {
    private final AccidentService accidents;

    @GetMapping("/createAccident")
    public String viewCreateAccident(Model model) {
        model.addAttribute("types", accidents.getTypes());
        return "createAccident";
    }

    @PostMapping("/saveAccident")
    public String save(@ModelAttribute Accident accident) {
        accidents.save(accident);
        return "redirect:/index";
    }

    @GetMapping("/editAccident")
    public String viewEditAccident(@RequestParam int id, Model model) {
        Optional<Accident> optionalAccident = accidents.findById(id);
        if (optionalAccident.isEmpty()) {
            model.addAttribute("error", "Нарушение с указанным "
                    + "идентификатором не найдено");
            return "errors/404";
        }
        model.addAttribute("types", accidents.getTypes());
        model.addAttribute("accident", optionalAccident.get());
        return "editAccident";
    }

    @PostMapping("/update")
    public String update(@ModelAttribute Accident accident, @RequestParam("typeId") int id) {
        accident.setType(accidents.getType(id));
        accidents.update(accident);
        return "redirect:/index";
    }
}