package com.vitorhenriquec.todolist.service;

import com.vitorhenriquec.todolist.domain.Task;
import com.vitorhenriquec.todolist.exception.ResourceNotFoundException;
import com.vitorhenriquec.todolist.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskService {
    private final TaskRepository taskRepository;

    public List<Task> findAll() {
        return this.taskRepository.findAll();
    }

    public Task findOne(Long id) throws ResourceNotFoundException {
        return this.taskRepository.findById(id).orElseThrow(ResourceNotFoundException::new);
    }

    @Transactional
    public Task save(Task task) throws ResourceNotFoundException{
        Task taskInstance = new Task();
        if (task.getId() != null) {
            taskInstance = this.findOne(task.getId());
        }
        BeanUtils.copyProperties(task, taskInstance, Task.ignoreProperties);
        return this.taskRepository.save(task);
    }

    @Transactional
    public void deleteAll(){
        this.taskRepository.deleteAll();
    }

    @Transactional
    public void deleteByid(Long id) throws ResourceNotFoundException {
        Task task = this.findOne(id);
        this.taskRepository.delete(task);
    }

}
