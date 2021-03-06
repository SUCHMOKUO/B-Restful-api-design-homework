package com.thoughtworks.capability.gtb.restfulapidesign.controller;

import com.thoughtworks.capability.gtb.restfulapidesign.domain.Group;
import com.thoughtworks.capability.gtb.restfulapidesign.exception.UnknownActionException;
import com.thoughtworks.capability.gtb.restfulapidesign.service.GroupService;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(path = "/v1/groups")
public class GroupController {

    private final GroupService groupService;

    public GroupController(GroupService groupService) {
        this.groupService = groupService;
    }

    @PatchMapping("/{id}")
    public Group updateGroup(@NonNull @PathVariable Integer id,
                             @Valid @RequestBody Group group) {
        return groupService.updateGroup(id, group);
    }

    @GetMapping
    public List<Group> getAllGroups() {
        return groupService.getAllGroups();
    }

    @PutMapping
    public List<Group> modifyGroups(@RequestParam String action) {
        if ("regroup".equals(action)) {
            return groupService.regroupAndGetAll();
        }

        throw new UnknownActionException();
    }
}
