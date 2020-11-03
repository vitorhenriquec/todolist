package com.vitorhenriquec.todolist.mapper;

import com.vitorhenriquec.todolist.domain.Task;
import com.vitorhenriquec.todolist.dto.TaskDTO;

public class TaskDTOMapper {
    public static TaskDTO map(Task task) {
        TaskDTO taskDTO = new TaskDTO();
        taskDTO.setId(task.getId());
        taskDTO.setName(task.getName());
        taskDTO.setDone(task.getDone());
        return taskDTO;
    }

    public static Task map(TaskDTO taskDTO){
        Task task = new Task();
        task.setId(taskDTO.getId());
        task.setDone(task.getDone());
        task.setName(task.getName());
        return task;
    }
}
