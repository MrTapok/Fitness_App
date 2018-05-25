package system.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import system.entities.User;
import system.service.WorkoutService;

import java.util.List;

@Controller
public class WorkoutController
{
    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String shopRegistration() {
        return "/index.html";
    }

    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public String home() {
        return "/home.html";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login() {
        return "/login.html";
    }

    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public String registration() {
        return "/registration.html";
    }

    @RequestMapping(value = "/error", method = RequestMethod.GET)
    public String error() {
        return "/error.html";
    }

    @RequestMapping(value = "/workout", method = RequestMethod.GET)
    public String cart() {
        return "/workout.html";
    }

    @RequestMapping(value = "/information", method = RequestMethod.GET)
    public String information() {
        return "/information.html";
    }

    @RequestMapping(value = "/admin", method = RequestMethod.GET)
    public String admin() {
        return "/admin.html";
    }

    @RequestMapping(value = "/manager", method = RequestMethod.GET)
    public String manager() {
        return "/manager.html";
    }
}
