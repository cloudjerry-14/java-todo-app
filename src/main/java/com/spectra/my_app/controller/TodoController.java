package com.spectra.my_app.controller;

import com.spectra.my_app.model.Todo;
import com.spectra.my_app.service.TodoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@Controller
@RequestMapping("/todos")
public class TodoController {

    private final TodoService service;

    public TodoController(TodoService service) {
        this.service = service;
    }

    @GetMapping("/")
    public String rootRedirect() {
        return "redirect:/todos";
    }

    @GetMapping
    public String list(Model model) {
        model.addAttribute("todos", service.listAll());
        return "index";
    }

    @GetMapping("/new")
    public String createForm(Model model) {
        model.addAttribute("todo", new Todo());
        model.addAttribute("action", "Create");
        return "form";
    }

    @PostMapping("/save")
    public String save(@Valid @ModelAttribute Todo todo, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            // Keep the action label
            if (todo.getId() == null) {
                model.addAttribute("action", "Create");
            } else {
                model.addAttribute("action", "Update");
            }
            return "form";
        }

        service.save(todo);
        return "redirect:/todos";
    }

    @GetMapping("/{id}/edit")
    public String edit(@PathVariable Long id, Model model) {
        Optional<Todo> t = service.get(id);
        if (t.isPresent()) {
            model.addAttribute("todo", t.get());
            model.addAttribute("action", "Update");
            return "form";
        }
        return "redirect:/todos";
    }

    @GetMapping("/{id}/delete")
    public String delete(@PathVariable Long id) {
        service.delete(id);
        return "redirect:/todos";
    }
}
