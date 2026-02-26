package com.spectra.my_app.service;

import com.spectra.my_app.model.Todo;
import com.spectra.my_app.repository.TodoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TodoService {

    private final TodoRepository repository;

    public TodoService(TodoRepository repository) {
        this.repository = repository;
    }

    public List<Todo> listAll() {
        return repository.findAll();
    }

    public Optional<Todo> get(Long id) {
        return repository.findById(id);
    }

    public Todo save(Todo todo) {
        return repository.save(todo);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}
