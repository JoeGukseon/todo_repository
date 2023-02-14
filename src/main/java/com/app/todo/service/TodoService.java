package com.app.todo.service;

import com.app.todo.entity.Todo;
import com.app.todo.exception.BusinessLogicException;
import com.app.todo.exception.ExceptionCode;
import com.app.todo.repository.TodoRepository;
import com.app.todo.utils.CustomBeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TodoService {

    private final TodoRepository todoRepository;

    private final CustomBeanUtils<Todo> beanUtils;

    public TodoService(TodoRepository todoRepository, CustomBeanUtils<Todo> beanUtils) {
        this.todoRepository = todoRepository;
        this.beanUtils = beanUtils;
    }

    public Todo createTodo(Todo todo) {
        return todoRepository.save(todo);
    }

    public List<Todo> findMembers() {
        return todoRepository.findAll();
    }

    public Todo findTodo(Long todoId) {
        return findVerifiedTodo(todoId);
    }

    private Todo findVerifiedTodo(Long todoId) {
        Optional<Todo> optionalTodo = todoRepository.findById(todoId);
        Todo findTodo =
                optionalTodo.orElseThrow(() ->
                        new BusinessLogicException(ExceptionCode.TODO_NOT_FOUND));
        return findTodo;
    }

    public Todo updateTodo(Todo todo) {
        Todo findTodo = findVerifiedTodo(todo.getId());

        Todo updateTodo = beanUtils.copyNonNullProperties(todo, findTodo);

        return todoRepository.save(updateTodo);

    }


    public void deleteTodos() {
        todoRepository.deleteAll();
    }

    public void deleteTodo(Long todoId) {
        Todo findTodo = findVerifiedTodo(todoId);

        todoRepository.delete(findTodo);
    }
}
