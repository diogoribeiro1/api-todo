package com.devus.apikotlin.services

import com.devus.apikotlin.entities.Task
import com.devus.apikotlin.repositories.TaskRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class TaskService {

    @Autowired
    lateinit var repository: TaskRepository;

    fun create(task: Task): Task{
        return repository.save(task);
    }

    fun getAll(): List<Task>{
        return repository.findAll();
    }

    fun getOneById(id: Long): Any{
        var payload = repository.findById(id).orElseThrow();
        return payload;
    }

    fun update(id: Long, task: Task): Task{
        var todoPayload = repository.findById(id).orElseThrow();
        todoPayload.title = task.title
        todoPayload.description = task.description
        todoPayload = repository.save(todoPayload)
        return todoPayload;
    }

    fun delete(id: Long){
       repository.findById(id).orElseThrow();
       repository.deleteById(id);
    }

}