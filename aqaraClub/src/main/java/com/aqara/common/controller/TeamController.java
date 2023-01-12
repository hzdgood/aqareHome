package com.aqara.common.controller;

import com.aqara.common.entity.Team;
import com.aqara.common.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/team")
public class TeamController {

    @Autowired
    TeamService TeamService;

    @CrossOrigin
    @RequestMapping("/select")
    public List<Team> select() {
        return TeamService.select();
    }

    @CrossOrigin
    @RequestMapping("/insert")
    public void insert(Team Team) {
        TeamService.insert(Team);
    }

    @CrossOrigin
    @RequestMapping("/update")
    public void update(Team Team) {
        TeamService.update(Team);
    }

    @CrossOrigin
    @RequestMapping("/delete")
    public void delete(Integer id) {
        TeamService.delete(id);
    }
}
