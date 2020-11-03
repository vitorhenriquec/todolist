package com.vitorhenriquec.todolist.rest;

import com.vitorhenriquec.todolist.domain.Task;
import com.vitorhenriquec.todolist.dto.TaskDTO;
import com.vitorhenriquec.todolist.exception.ResourceNotFoundException;
import com.vitorhenriquec.todolist.mapper.TaskDTOMapper;
import com.vitorhenriquec.todolist.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/v1/todo")
@RequiredArgsConstructor
public class TaskRestController {
    private final TaskService taskService;

    @GetMapping
    public List<TaskDTO> findAll(){
        return this.taskService.findAll().stream().map(TaskDTOMapper::map).collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public TaskDTO getOne(@RequestParam Long id) throws ResourceNotFoundException {
        return TaskDTOMapper.map(this.taskService.findOne(id));
    }

    @PostMapping
    public TaskDTO save(@RequestBody TaskDTO taskDTO) throws ResourceNotFoundException {
        Task task = TaskDTOMapper.map(taskDTO);
        return TaskDTOMapper.map(this.taskService.save(task));
    }

    @PutMapping
    public TaskDTO edit(@RequestParam Long id, @RequestBody TaskDTO taskDTO) throws ResourceNotFoundException {
        Task task = TaskDTOMapper.map(taskDTO);
        task.setId(id);
        return TaskDTOMapper.map(this.taskService.save(task));
    }

    @DeleteMapping
    public void deleteAll(){
        this.taskService.deleteAll();
    }

    @DeleteMapping("/{id}")
    public void deleteOne(@RequestParam Long id) throws ResourceNotFoundException {
        this.taskService.deleteByid(id);
    }

}
