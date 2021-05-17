package com.whelmed.demo.service;

import com.whelmed.demo.exception.*;
import com.whelmed.demo.model.Group;
import com.whelmed.demo.model.Task;
import com.whelmed.demo.repository.*;
import com.whelmed.demo.security.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GroupService {
    private GroupRepository groupRepository;
    private TaskRepository taskRepository;
    @Autowired
    public void setGroupRepository(GroupRepository groupRepository) {
        this.groupRepository = groupRepository;
    }
    @Autowired
    public void setTaskRepository(TaskRepository taskRepository){
        this.taskRepository = taskRepository;
    }
    public List<Group> getGroups(){
        System.out.println("Calling getGroups in GroupService ===>");
        MyUserDetails userDetails = (MyUserDetails) SecurityContextHolder.getContext().getAuthentication()
                .getPrincipal();
        return groupRepository.findByUserId(userDetails.getUser().getId());
    }
    public Group getGroup(long groupId){
        System.out.println("Calling getGroup in GroupService ===>");
        MyUserDetails userDetails = (MyUserDetails) SecurityContextHolder.getContext().getAuthentication()
                .getPrincipal();
        Optional<Group> group = groupRepository.findByIdAndUserId(groupId, userDetails.getUser().getId());
        if(group.isPresent()){
            return group.get();
        }else{
            throw new InformationNotFoundException("Group with id " + groupId + " does not exist for given user");
        }
    }
    public Group createGroup(Group group){
        System.out.print("Calling createGroup in GroupService ===>");
        MyUserDetails userDetails = (MyUserDetails) SecurityContextHolder.getContext().getAuthentication()
                .getPrincipal();
        Group maybe = groupRepository.findByNameAndUserId(group.getName(), userDetails.getUser().getId());
        if(maybe == null){
            group.setUser(userDetails.getUser());
            return groupRepository.save(group);
        }else{
            throw new InformationExistException("Group with name " + group.getName() + " already exists for given user");
        }
    }
    public Group updateGroup(long groupId, Group group){
        System.out.println("Calling updateGroup in GroupService ===>");
        MyUserDetails userDetails = (MyUserDetails) SecurityContextHolder.getContext().getAuthentication()
                .getPrincipal();
        Optional<Group> exists = groupRepository.findByIdAndUserId(groupId, userDetails.getUser().getId());
        Group maybe = groupRepository.findByNameAndUserId(group.getName(), userDetails.getUser().getId());
        if (exists.isPresent()){
            if (maybe == null || (maybe.getId() == groupId)){
                exists.get().setName(group.getName());
                exists.get().setDescription(group.getDescription());
                return groupRepository.save(exists.get());
            }else{
                throw new InformationExistException("Group with name " + group.getName() + " already exists for given user");
            }
        }else{
            throw new InformationNotFoundException("A group with id " + groupId + " does not exist for given user");
        }
    }
    public void deleteGroup(long groupId){
        System.out.println("Calling deleteGroup in GroupService ===>");
        Group group = this.getGroup(groupId);
        groupRepository.delete(group);
    }
    public List<Task> getTasks(long groupId){
        System.out.println("Calling getTasks in GroupService ===>");
        Group group  = this.getGroup(groupId);
        return group.getTaskList();
    }
    public Task getTask(Long groupId, Long taskId){
        System.out.println("Calling getTask in GroupService ===>");
        Optional<Task> task = this.getTasks(groupId).stream().filter(
                p -> p.getId().equals(taskId)).findFirst();
        if (task.isPresent()){
            return task.get();
        }else{
            throw new InformationNotFoundException("Task with id " + taskId + " doesn't exist in group " + groupId);
        }
    }
    public Task createTask(Long groupId, Task task){
        System.out.println("Calling createTask in GroupService ===>");
        MyUserDetails userDetails = (MyUserDetails) SecurityContextHolder.getContext().getAuthentication()
                .getPrincipal();
        Group group = this.getGroup(groupId);
        Optional<Task> maybe = this.getTasks(groupId).stream().filter(
                p -> p.getTitle().equals(task.getTitle())).findFirst();
        if (maybe.isEmpty()){
            task.setUser(userDetails.getUser());
            task.setGroup(group);
            return taskRepository.save(task);
        }else{
            throw new InformationExistException("Task with name " + task.getTitle() + " already exists in this group.");
        }
    }
    public Task updateTask(Long groupId, Long taskId, Task task){
        System.out.println("Calling updateTask in GroupService ===>");
        Task exists = this.getTask(groupId, taskId);
        Optional<Task> maybe = this.getTasks(groupId).stream().filter(
                p -> p.getTitle().equals(task.getTitle())).findFirst();
        if(maybe.isEmpty() || maybe.get().getId().equals(task.getId())){
            exists.setTitle(task.getTitle());
            exists.setDueDate(task.getDueDate());
            exists.setConsequence(task.getConsequence());
            exists.setPriority(task.getPriority());
            exists.setGroup(task.getGroup());
            return taskRepository.save(exists);
        }else{
            throw new InformationExistException("A task with name " + task.getTitle() + " already exists in group with id " + groupId);
        }

    }
    public void deleteTask(Long groupId, Long taskId){
        System.out.println("Calling deleteTask in GroupService ===>");
        Task task = this.getTask(groupId, taskId);
        taskRepository.deleteById(task.getId());
    }
    public Task updateIsComplete(Long groupId, Long taskId){
        System.out.println("Calling updateIsComplete in GroupService ===>");
        Task task = this.getTask(groupId, taskId);
        Boolean isComplete = task.isComplete();
        task.setComplete(!isComplete);
        return taskRepository.save(task);
    }
}
