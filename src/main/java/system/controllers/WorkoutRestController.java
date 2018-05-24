package system.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import system.entities.Trainer;
import system.entities.Workout;
import system.entities.User;
import system.service.WorkoutService;

import java.util.List;

@RestController
public class WorkoutRestController
{
    private WorkoutService workoutService;

    @Autowired
    public void setWorkoutService(WorkoutService workoutService) {
        this.workoutService = workoutService;
    }

    @RequestMapping(value = "/submitRegistration", method = RequestMethod.POST)
    public boolean fitnessRegistration(@RequestBody User user){
        return workoutService.addUser(user);
    }

    @RequestMapping(value = "/deleteUser", method = RequestMethod.POST)
    public void deleteUser(@RequestBody User user){
        workoutService.deleteUser(user.getId());
    }

    @RequestMapping(value = "/getUsername", method = RequestMethod.GET)
    public String getUsername(){
        return workoutService.getUserName();
    }

    @RequestMapping(value = "/addAdmin", method = RequestMethod.POST)
    public void addAdmin(@RequestBody User user){
        workoutService.addAdmin(user);
    }


    //work in progress

}
