package com.devus.apikotlin.controllers

import com.devus.apikotlin.entities.Todo
import com.devus.apikotlin.repositories.TodoRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Repository
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/todos")
class TodoController {

    @Autowired
    lateinit var repository: TodoRepository;

    @GetMapping
    fun getAll(): List<Todo>{
        return repository.findAll();
    }

    @PostMapping
    fun create(@RequestBody todo: Todo): Todo{
        return repository.save(todo);
    }
}