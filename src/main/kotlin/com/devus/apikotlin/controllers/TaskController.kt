package com.devus.apikotlin.controllers

import com.devus.apikotlin.entities.Task
import com.devus.apikotlin.services.TaskService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/tasks")
class TaskController {

    @Autowired
    lateinit var service: TaskService;

    @GetMapping
    @ResponseBody
    fun getAll(): ResponseEntity<List<Task>>{
        var payload = service.getAll();
        return ResponseEntity(payload, HttpStatus.OK);
    }

    @PostMapping
    @ResponseBody
    fun create(@RequestBody task: Task): ResponseEntity<Task>{
        var payload = service.create(task);
        return ResponseEntity(payload, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    @ResponseBody
    fun getOne(@PathVariable id: Long): ResponseEntity<Any>{
        return try {
            var payload = service.getOneById(id);
            ResponseEntity(payload, HttpStatus.OK);
        }catch (e: Exception){
            ResponseEntity("Task not found", HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{id}")
    fun update(@PathVariable id: Long, @RequestBody task: Task): ResponseEntity<Any> {
        return try {
            var payload = service.update(id,task);
            ResponseEntity(payload, HttpStatus.OK);
        }catch (e: Exception){
            ResponseEntity("Task not found", HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    fun deleteById(@PathVariable id: Long): ResponseEntity<Any>{
        return try {
            service.delete(id);
            ResponseEntity(HttpStatus.NO_CONTENT);
        }catch (e: Exception){
            ResponseEntity("Task not found", HttpStatus.NOT_FOUND);
        }
    }
}