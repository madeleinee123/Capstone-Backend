package com.whelmed.demo.repository;

import com.whelmed.demo.model.Group;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface GroupRepository extends JpaRepository<Group, Long> {
    Group findByName(String groupName);

    List<Group> findByUserId(Long userId);

    Optional<Group> findByIdAndUserId(long groupId, Long userId);

    Group findByNameAndUserId(String name, Long id);
}
