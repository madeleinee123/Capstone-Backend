package com.whelmed.demo.service;

import com.whelmed.demo.model.Group;
import com.whelmed.demo.repository.GroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GroupService {
    private GroupRepository groupRepository;
    @Autowired
    public void setCategoryRepository(GroupRepository groupRepository) {
        this.groupRepository = groupRepository;
    }
    public List<Group> getGroups(){
        System.out.println("Calling getGroups in GroupService ===>");
        return groupRepository.findAll();
    }
    public String getGroup(long groupId){
        return "Calling getGroup in GroupService ===>";
    }
    public String createGroup(String group){
        return "Calling createGroup in GroupService ===>";
    }
    public String updateGroup(long groupId, String body){
        return "Calling updateGroup in GroupService ===>";
    }
    public String deleteGroup(long groupId){
        return "Calling deleteGroup in GroupService";
    }
}
