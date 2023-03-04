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

    fun create(todo: Todo): Todo{
        return repository.save(todo);
    }

    fun getAll(): List<Todo>{
        return repository.findAll();
    }

    fun getOneById(id: Long): Any{
        var payload = if (repository.findById(id).isEmpty()) "Todo not found" else repository.findById(id).get() ;
        return payload;
    }

    fun update(id: Long, todo: Todo): Todo{
        var todoPayload = repository.findById(id).orElseThrow();
        todoPayload.title = todo.title
        todoPayload.description = todo.description
        todoPayload = repository.save(todoPayload)
        return todoPayload;
    }

    fun delete(id: Long){
       repository.findById(id).orElseThrow();
       repository.deleteById(id);
    }

}