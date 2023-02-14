package com.app.todo.mapper;

import com.app.todo.dto.TodoDto;
import com.app.todo.entity.Todo;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TodoMapper {
    Todo todoPostToTodo(TodoDto.Post requestBody);

    Todo todoPatchToTodo(TodoDto.Patch requestBody);

    TodoDto.Response todoToTodoResponse(Todo requestBody);

    List<TodoDto.Response> todosToTodoResponses(List<Todo> todos);
}
