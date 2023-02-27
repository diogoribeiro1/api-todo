package com.devus.apikotlin.controllers

import com.devus.apikotlin.entities.Todo
import com.devus.apikotlin.repositories.TodoRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Repository
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RestController
import java.util.Objects
import java.util.Optional

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

    @GetMapping("/{id}")
    fun getOne(@PathVariable id: Long): Optional<Todo>{
        return repository.findById(id);
    }

    @PutMapping("/{id}")
    fun update(@PathVariable id: Long, @RequestBody todo: Todo): ResponseEntity<Todo>{
        var todoPayload = repository.findById(id).get()
        todoPayload.title = todo.title
        todoPayload.description = todo.description
        todoPayload = repository.save(todoPayload)
        return ResponseEntity.status(HttpStatus.CREATED).body(todoPayload);
    }

    @DeleteMapping("/{id}")
    fun deleteById(@PathVariable id: Long): ResponseEntity<Objects>{
        var todoPayload = repository.deleteById(id)
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}