package com.whelmed.demo.repository;

import com.whelmed.demo.model.Group;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GroupRepository extends JpaRepository<Group, Long> {
    Group findByName(String categoryName);
}
