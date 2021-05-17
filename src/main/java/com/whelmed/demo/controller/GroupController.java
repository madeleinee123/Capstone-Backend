package com.whelmed.demo.controller;

import java.util.HashMap;
import java.util.List;

import com.whelmed.demo.model.Group;
import com.whelmed.demo.model.Task;
import com.whelmed.demo.service.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api")
public class GroupController {
    private GroupService groupService;
    @Autowired
    public void setGroupService(GroupService groupService){
        this.groupService = groupService;
    }

    @GetMapping(path = "/groups")
    public List<Group> getGroups(){
        System.out.println("Calling getGroups in GroupController ===>");
        return groupService.getGroups();
    }
    @GetMapping(path = "/groups/{groupId}")
    public Group getGroup(@PathVariable Long groupId){
        System.out.println("Calling getGroup in GroupController ===>");
        return groupService.getGroup(groupId);
    }
    @PostMapping(path = "/groups")
    public Group createGroup(@RequestBody Group group){
        System.out.println("Calling createGroup in GroupController ===>");
        return groupService.createGroup(group);
    }
    @PutMapping(path = "/groups/{groupId}")
    public Group updateGroup(@PathVariable Long groupId, @RequestBody Group body) {
        System.out.println("Calling updateGroup in GroupController ===>");
        return groupService.updateGroup(groupId, body);
    }
    @DeleteMapping(path = "/groups/{groupId}")
    public ResponseEntity<HashMap> deleteGroup(@PathVariable Long groupId){
        System.out.println("Calling deleteGroup in GroupController ===>");
        groupService.deleteGroup(groupId);
        HashMap responseMessage = new HashMap();
        responseMessage.put("status", "Group with id " + groupId + " was successfully deleted.");
        return new ResponseEntity<HashMap>(responseMessage, HttpStatus.OK);
    }
    @GetMapping(path = "/groups/{groupId}/tasks")
    public List<Task> getTasks(@PathVariable Long groupId){
        System.out.println("Calling getTasks in GroupController ===>");
        return groupService.getTasks(groupId);
    }
    @GetMapping(path = "/groups/{groupId}/tasks/{taskId}")
    public Task getTask(@PathVariable Long groupId, @PathVariable Long taskId){
        System.out.println("Calling getTask in GroupController ===>");
        return groupService.getTask(groupId, taskId);
    }
    @PostMapping(path = "/groups/{groupId}/tasks")
    public Task createTask(@PathVariable Long groupId, @RequestBody Task task){
        System.out.println("Calling createTask in GroupController ===>");
        return groupService.createTask(groupId, task);
    }
    @PutMapping(path = "/groups/{groupId}/tasks/{taskId}")
    public Task updateTask(@PathVariable Long groupId, @PathVariable Long taskId, @RequestBody Task task){
        System.out.println("Calling updateTask in GroupController ===>");
        return groupService.updateTask(groupId, taskId, task);
    }
    @DeleteMapping(path = "/groups/{groupId}/tasks/{taskId}")
    public ResponseEntity<HashMap> deleteTas(@PathVariable Long groupId, @PathVariable Long taskId){
        System.out.println("Calling deleteTask in GroupController ===>");
        groupService.deleteTask(groupId, taskId);
        HashMap responseMessage = new HashMap();
        responseMessage.put("status", "Task with id " + taskId + " in group with id " + groupId + " was successfully deleted.");
        return new ResponseEntity<HashMap>(responseMessage, HttpStatus.OK);
    }
    @PatchMapping(path = "/groups/{groupId}/tasks/{taskId}")
    public Task updateIsComplete(@PathVariable Long groupId, @PathVariable Long taskId){
        System.out.println("Calling updateIsComplete in GroupController ===>");
        return groupService.updateIsComplete(groupId, taskId);
    }
}
