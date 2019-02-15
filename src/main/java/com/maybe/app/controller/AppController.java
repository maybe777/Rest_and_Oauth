package com.maybe.app.controller;

import com.maybe.app.dao.RestDao;
import com.maybe.app.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class AppController {

    private final RestDao restDao;

    @Autowired
    public AppController(RestDao userDao) {
        this.restDao = userDao;
    }

    @GetMapping
    public String login() {
        return "login";
    }

    @GetMapping("/welcome")
    public String welcome() {
        return "welcome";
    }

    //test functions
    @GetMapping("/ajax")
    public String ajax() {
        return "ajax";
    }

    @GetMapping("all")
    public ModelAndView all() {

        ModelAndView modelAndView = new ModelAndView("all");

        modelAndView.addObject("all", restDao.getAll());
        modelAndView.addObject("user", new User());

        return modelAndView;

    }

    @GetMapping("/all/fillForm")
    public ModelAndView getAll(@RequestParam("usrId") String usrId, Model model) {

        if (usrId != null) {
            User user = restDao.getById(Long.parseLong(usrId));
            model.addAttribute("user", user);
        }

        return new ModelAndView("all", "all", restDao.getAll());

    }

    @GetMapping("delete")
    public String delete(@RequestParam("id") Long id) {

        restDao.delete(id);

        return "redirect:/all";

    }

    @PostMapping("edit")
    public String edit(@ModelAttribute User user) {

        restDao.edit(user);

        return "redirect:/all";

    }

    @PostMapping("add")
    public String add(@ModelAttribute User user) {

        restDao.add(user);

        return "redirect:/all";

    }

}
