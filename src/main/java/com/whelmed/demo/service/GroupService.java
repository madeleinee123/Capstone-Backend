package com.whelmed.demo.service;

import com.whelmed.demo.exception.InformationExistException;
import com.whelmed.demo.exception.InformationNotFoundException;
import com.whelmed.demo.model.Group;
import com.whelmed.demo.repository.GroupRepository;
import com.whelmed.demo.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
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
        return groupRepository.findAll();
    }
    public Group getGroup(long groupId){
        System.out.println("Calling getGroup in GroupService ===>");
        Optional<Group> group = groupRepository.findById(groupId);
        if(group.isPresent()){
            return group.get();
        }else{
            throw new InformationNotFoundException("Group with id " + groupId + " does not exist");
        }
    }
    public Group createGroup(Group group){
        System.out.print("Calling createGroup in GroupService ===>");
        Group maybe = groupRepository.findByName(group.getName());
        if(maybe == null){
            return groupRepository.save(group);
        }else{
            throw new InformationExistException("Group with name " + group.getName() + " already exists");
        }
    }
    public Group updateGroup(long groupId, Group group){
        System.out.println("Calling updateGroup in GroupService ===>");
        Optional<Group> exists = groupRepository.findById(groupId);
        Group maybe = groupRepository.findByName(group.getName());
        if (exists.isPresent()){
            if (maybe == null || (maybe.getId() == groupId)){
                exists.get().setName(group.getName());
                exists.get().setDescription(group.getDescription());
                return groupRepository.save(exists.get());
            }else{
                throw new InformationExistException("Group with name " + group.getName() + " already exists");
            }
        }else{
            throw new InformationNotFoundException("A group with id " + groupId + " does not exist");
        }
    }
    public void deleteGroup(long groupId){
        System.out.println("Calling deleteGroup in GroupService ===>");
        Group group = this.getGroup(groupId);
        groupRepository.delete(group);
    }
    public String getTasks(long groupId){
        return "Calling getTasks in GroupService ===>";
    }
    public String getTask(long groupId, long taskId){
        return "Calling getTask in GroupService ===>";
    }
    public String createTask(long groupId){
        return "Calling createTask in GroupService ===>";
    }
    public String updateTask(long groupId, long taskId){
        return "Calling updateTask in GroupService ===>";
    }
    public String deleteTask(long groupId, long taskId){
        return "Calling deleteTask in GroupService ===>";
    }
}
