package com.app.todo.dto;

import com.app.todo.entity.Todo;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

public class TodoDto {
    @AllArgsConstructor
    @Getter
    public static class Post {
        private String title;

        private Long todo_order;

        private Boolean completed;
    }

    @AllArgsConstructor
    @Getter
    public static class Response {
        private Long id;

        private String title;

        private Long todo_order;

        private Boolean completed;
    }

    @Getter
    @AllArgsConstructor
    public static class Patch {
        @Setter
        private Long id;
        private String title;

        private Long todo_order;

        private Boolean completed;
    }

}
