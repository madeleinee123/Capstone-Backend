package com.whelmed.demo.service;

import com.whelmed.demo.exception.InformationExistException;
import com.whelmed.demo.exception.InformationNotFoundException;
import com.whelmed.demo.model.Group;
import com.whelmed.demo.repository.GroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
        System.out.println("Calling deleteGroup in GroupService");
        Group group = this.getGroup(groupId);
        groupRepository.delete(group);
    }
}
