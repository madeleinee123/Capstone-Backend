package com.whelmed.demo.service;

public class ListService {
    public String getLists(){
        return "Calling getLists in ListService ===>";
    }
    public String getList(long listId){
        return "Calling getList in ListService ===>";
    }
    public String createList(String list){
        return "Calling createList in ListService ===>";
    }
    public String updateList(long listId, String body){
        return "Calling updateList in ListService ===>";
    }
    public String deleteList(long listId){
        return "Calling deleteList in ListService";
    }
}
