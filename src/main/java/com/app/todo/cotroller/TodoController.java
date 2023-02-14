package com.app.todo.cotroller;

import com.app.todo.dto.TodoDto;
import com.app.todo.entity.Todo;
import com.app.todo.mapper.TodoMapper;
import com.app.todo.service.TodoService;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Positive;
import java.util.List;

@RestController
@Validated
public class TodoController {
    private final TodoService todoService;

    private final TodoMapper todoMapper;

    public TodoController(TodoService todoService, TodoMapper todoMapper) {
        this.todoService = todoService;
        this.todoMapper = todoMapper;
    }

    @PostMapping
    public TodoDto.Response postTodo(@RequestBody TodoDto.Post todoPost) {
        Todo todo = todoService.createTodo(todoMapper.todoPostToTodo(todoPost));
        TodoDto.Response todoResponse = todoMapper.todoToTodoResponse(todo);
        return todoResponse;
    }

    @GetMapping
    public List<TodoDto.Response> getTodos() {
        List<Todo> todos = todoService.findMembers();
        List<TodoDto.Response> todoResponses = todoMapper.todosToTodoResponses(todos);

        return todoResponses;
    }

    @GetMapping("/{todo-id}")
    public TodoDto.Response getTodo(@PathVariable("todo-id") @Positive Long todoId) {
        Todo todo = todoService.findTodo(todoId);
        TodoDto.Response todoResponse = todoMapper.todoToTodoResponse(todo);

        return todoResponse;
    }

    @PatchMapping("/{todo-id}")
    public TodoDto.Response patchTodo(@PathVariable("todo-id") @Positive Long todoId,
                                      @RequestBody TodoDto.Patch todoPatch) {
        todoPatch.setId(todoId);
        TodoDto.Response todoResponse =
                todoMapper.todoToTodoResponse(todoService.updateTodo(todoMapper.todoPatchToTodo(todoPatch)));
        return todoResponse;
    }

    @DeleteMapping
    public ResponseEntity deleteTodos() {
        todoService.deleteTodos();

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/{todo-id}")
    public ResponseEntity deleteTodo(@PathVariable("todo-id") @Positive Long todoId) {
        todoService.deleteTodo(todoId);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
