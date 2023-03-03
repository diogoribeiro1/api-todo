package com.devus.apikotlin.services

import com.devus.apikotlin.entities.Todo
import com.devus.apikotlin.repositories.TodoRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import java.util.Objects

@Service
class TodoService {

    @Autowired
    lateinit var repository: TodoRepository;

    fun create(todo: Todo): ResponseEntity<Todo>{
       var payload = repository.save(todo);
       return ResponseEntity.status(201).body(payload);
    }

    fun getAll(): ResponseEntity<List<Todo>>{
        var payload = repository.findAll();
        return ResponseEntity.status(200).body(payload);
    }

    fun getOneById(id: Long): ResponseEntity<Any>{
        var payload = if (repository.findById(id).isEmpty()) "Todo not found" else repository.findById(id).get() ;
        return ResponseEntity.status(200).body(payload);
    }

    fun update(id: Long, todo: Todo): ResponseEntity<Todo>{
        var todoPayload = repository.findById(id).get()
        todoPayload.title = todo.title
        todoPayload.description = todo.description
        todoPayload = repository.save(todoPayload)
        return ResponseEntity.ok(todoPayload);
    }

    fun delete(id: Long): ResponseEntity<Any>{
        var payload = if (repository.findById(id).isEmpty()) "Todo not found" else repository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}