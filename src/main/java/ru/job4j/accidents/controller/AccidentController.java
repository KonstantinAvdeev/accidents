package ru.job4j.accidents.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.job4j.accidents.model.Accident;
import ru.job4j.accidents.model.AccidentType;
import ru.job4j.accidents.service.*;

import java.util.Optional;
import java.util.Set;

@Controller
@AllArgsConstructor
@RequestMapping(name = "/accidents")
public class AccidentController {
    private final AccidentService accidentService;
    private final AccidentTypeService accidentTypeService;
    private final RuleService ruleService;

    @GetMapping("/createAccident")
    public String viewCreateAccident(Model model) {
        model.addAttribute("rules", ruleService.getAll());
        model.addAttribute("types", accidentTypeService.getAll());
        model.addAttribute("user", "Иван Иванов");
        return "createAccident";
    }

    @PostMapping("/saveAccident")
    public String save(@ModelAttribute Accident accident, @RequestParam("rIds") Set<String> rules) {
        accidentService.save(accident, rules);
        return "redirect:/index";
    }

    @GetMapping("/editAccident")
    public String viewEditAccident(@RequestParam int id, Model model) {
        Optional<Accident> optionalAccident = accidentService.findById(id);
        if (optionalAccident.isEmpty()) {
            model.addAttribute("error", "Нарушение с указанным "
                    + "идентификатором не найдено");
            return "errors/404";
        }
        model.addAttribute("rules", ruleService.getAll());
        model.addAttribute("types", accidentTypeService.getAll());
        model.addAttribute("accident", optionalAccident.get());
        model.addAttribute("user", "Иван Иванов");
        return "editAccident";
    }

    @PostMapping("/update")
    public String update(@ModelAttribute Accident accident,
                         @RequestParam("typeId") AccidentType type,
                         @RequestParam("rIds") Set<String> rules) {
        accident.setRules(ruleService.getSetRule(rules));
        accident.setType(type);
        accidentService.update(accident);
        return "redirect:/index";
    }

}