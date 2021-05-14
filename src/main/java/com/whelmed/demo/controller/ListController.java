package com.whelmed.demo.controller;

import com.whelmed.demo.service.ListService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api")
public class ListController {
    private final ListService listService = new ListService();

    @GetMapping(path = "/lists/")
    public String getLists(){
        System.out.println("Calling getLists in ListController ===>");
        return listService.getLists();
    }
    @GetMapping(path = "/lists/{listId}")
    public String getList(@PathVariable long listId){
        System.out.println("Calling getList in ListController ===>");
        return listService.getList(listId);
    }
    @PostMapping(path = "/lists")
    public String createList(@RequestBody String list){
        System.out.println("Calling createList in ListController ===>");
        return listService.createList(list);
    }
    @PutMapping(path = "/lists/{listId}")
    public String updateList(@PathVariable long listId, @RequestBody String body) {
        System.out.println("Calling updateList in ListController ===>");
        return listService.updateList(listId, body);
    }
    @DeleteMapping(path = "/lists/{listId}")
    public String deleteList(@PathVariable long listId){
        System.out.println("Calling deleteList in ListController ===>");
        return listService.deleteList(listId);
    }
}
