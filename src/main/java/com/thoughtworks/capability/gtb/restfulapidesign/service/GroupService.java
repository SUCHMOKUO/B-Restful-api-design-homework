package com.thoughtworks.capability.gtb.restfulapidesign.service;

import com.thoughtworks.capability.gtb.restfulapidesign.domain.Group;
import com.thoughtworks.capability.gtb.restfulapidesign.exception.GroupNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Service
public class GroupService {

    private final List<Group> groups = new ArrayList<>(6);

    @PostConstruct
    private void initGroups() {
        for (int i = 1; i <= 6; i++) {
            groups.add(Group.builder()
                    .id(i)
                    .name("未命名组")
                    .build());
        }
    }

    private Group checkAndGetGroup(Integer id) {
        if (id < 1 || id > 6) {
            throw new GroupNotFoundException();
        }

        return groups.get(id - 1);
    }

    public Group updateGroup(Integer id, Group group) {
        Group groupToUpdate = checkAndGetGroup(id);

        groupToUpdate.setName(group.getName());

        return groupToUpdate;
    }
}
