package com.whelmed.demo.controller;

import java.util.List;

import com.whelmed.demo.model.Group;
import com.whelmed.demo.service.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
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
    public Group getGroup(@PathVariable long groupId){
        System.out.println("Calling getGroup in GroupController ===>");
        return groupService.getGroup(groupId);
    }
    @PostMapping(path = "/groups")
    public String createGroup(@RequestBody String group){
        System.out.println("Calling createGroup in GroupController ===>");
        return groupService.createGroup(group);
    }
    @PutMapping(path = "/groups/{groupId}")
    public String updateGroup(@PathVariable long groupId, @RequestBody String body) {
        System.out.println("Calling updateGroup in GroupController ===>");
        return groupService.updateGroup(groupId, body);
    }
    @DeleteMapping(path = "/groups/{groupId}")
    public String deleteGroup(@PathVariable long groupId){
        System.out.println("Calling deleteGroup in GroupController ===>");
        return groupService.deleteGroup(groupId);
    }
}
