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
        var payload = service.getAll();
        return ResponseEntity(payload, HttpStatus.OK);
    }

    @PostMapping
    @ResponseBody
    fun create(@RequestBody todo: Todo): ResponseEntity<Todo>{
        var payload = service.create(todo);
        return ResponseEntity(payload, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    @ResponseBody
    fun getOne(@PathVariable id: Long): ResponseEntity<Any>{
       var payload = service.getOneById(id);
        return  if (payload == "Todo not found")
            ResponseEntity(payload, HttpStatus.NOT_FOUND)
        else ResponseEntity(payload, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    fun update(@PathVariable id: Long, @RequestBody todo: Todo): ResponseEntity<Any> {
        try {
            var payload = service.update(id,todo);
            return ResponseEntity(payload, HttpStatus.OK);
        }catch (e: Exception){
            return ResponseEntity("Todo not found", HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    fun deleteById(@PathVariable id: Long): ResponseEntity<Any>{
        try {
            service.delete(id);
            return ResponseEntity(HttpStatus.NO_CONTENT);
        }catch (e: Exception){
            return ResponseEntity("Todo not found", HttpStatus.NOT_FOUND);
        }
    }
}