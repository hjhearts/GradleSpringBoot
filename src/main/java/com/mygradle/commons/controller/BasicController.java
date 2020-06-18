package com.mygradle.commons.controller;

import com.mygradle.commons.model.Todo;
import com.mygradle.commons.model.TodoResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;
@RestController
@RequestMapping("/basic")
public class BasicController {
    private final AtomicLong counter = new AtomicLong();

    @RequestMapping(value = "/todo")
    public Todo basic(){
        return new Todo(counter.incrementAndGet(), "buy noodle");
    }

    @RequestMapping(value = "/todoP", method = RequestMethod.POST)
    public Todo postTodo(@RequestParam(value = "todoTitle") String todoTitle){
        return new Todo(counter.incrementAndGet(), todoTitle);
    }

    @RequestMapping(value = "/todoR", method = RequestMethod.POST)
    public ResponseEntity<Todo> todoBasicResponseEntity(@RequestParam(value = "todoTitle")String todoTitle){
        return new ResponseEntity<>(new Todo(counter.incrementAndGet(), todoTitle), HttpStatus.CREATED);
    }

    @RequestMapping(value = "/todo/{todoId}")
    public Todo getTodoPath(@PathVariable("todoId")int todoNum){
        Todo todo1 = new Todo(1L, "java");
        Todo todo2 = new Todo(2L, "python");
        Todo todo3 = new Todo(3L, "c");

        Map<Integer, Todo> todoMap = new HashMap<>();
        todoMap.put(1, todo1);
        todoMap.put(2, todo2);
        todoMap.put(3, todo3);

        return todoMap.get(todoNum);
    }

    @RequestMapping(value = "/todoh", method = RequestMethod.GET)
    public ResponseEntity<TodoResource> getH(@RequestParam(value = "todoTitle")String title){
        TodoResource todoResource = new TodoResource(title);
        todoResource.add(linkTo(methodOn(BasicController.class).getH(title)).withSelfRel());
        return new ResponseEntity<>(todoResource, HttpStatus.OK);
    }
}
