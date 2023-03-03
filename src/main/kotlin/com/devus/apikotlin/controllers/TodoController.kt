package com.devus.apikotlin.controllers

import com.devus.apikotlin.entities.Todo
import com.devus.apikotlin.repositories.TodoRepository
import com.devus.apikotlin.services.TodoService
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
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.RestController
import java.util.Objects
import java.util.Optional

@RestController
@RequestMapping("/todos")
class TodoController {

    @Autowired
    lateinit var service: TodoService;

    @GetMapping
    @ResponseBody
    fun getAll(): ResponseEntity<List<Todo>>{
        return service.getAll();
    }

    @PostMapping
    @ResponseBody
    fun create(@RequestBody todo: Todo): ResponseEntity<Todo>{
        return service.create(todo);
    }

    @GetMapping("/{id}")
    @ResponseBody
    fun getOne(@PathVariable id: Long): ResponseEntity<Any>{
        return service.getOneById(id);
    }

    @PutMapping("/{id}")
    fun update(@PathVariable id: Long, @RequestBody todo: Todo): ResponseEntity<Todo>{
        return service.update(id,todo);
    }

    @DeleteMapping("/{id}")
    fun deleteById(@PathVariable id: Long): ResponseEntity<Any>{
        return service.delete(id);
    }
}