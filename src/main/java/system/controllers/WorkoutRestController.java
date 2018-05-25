package system.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import system.entities.Pass;
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

    @RequestMapping(value = "/addManager", method = RequestMethod.POST)
    public void addManager(@RequestBody User user){
        workoutService.addManager(user);
    }

    @RequestMapping(value = "/addTrainer", method = RequestMethod.POST)
    public void addTrainer(@RequestBody User user){
        workoutService.addTrainer(user);
    }

    @RequestMapping(value = "/getCurrentUser", method = RequestMethod.GET)
    public User getCurrentUser(){
        return workoutService.getCurrentUser();
    }

    @RequestMapping(value = "/getAllUsers", method = RequestMethod.GET)
    public List<User> getUsers(){
        return workoutService.getAllUsers();
    }

    @RequestMapping(value = "/getAllTrainers", method = RequestMethod.GET)
    public List<User> getTrainers(){
        return workoutService.getTrainers();
    }

    @RequestMapping(value = "/getAllManagers", method = RequestMethod.GET)
    public List<User> getManagers(){
        return workoutService.getManagers();
    }

    @RequestMapping(value = "/getAllTrainers", method = RequestMethod.GET)
    public List<User> getAdmins(){
        return workoutService.getAdmins();
    }

    @RequestMapping(value = "/getAllWorkouts", method = RequestMethod.GET)
    public List<User> getWorkouts(){
        return workoutService.getAllWorkouts();
    }

    @RequestMapping(value = "/getAllTypes", method = RequestMethod.GET)
    public List<User> getTypes(){
        return workoutService.getAllTypes();
    }

    @RequestMapping(value = "/getAllPasses", method = RequestMethod.GET)
    public List<User> getPasses(){
        return workoutService.getAllPasses();
    }

    @RequestMapping(value = "/addWorkoutToUser", method = RequestMethod.POST)
    public void addFoodToCart(@RequestBody Workout workout){
        workoutService.addWorkoutToUser(workout.getId());
    }

    @RequestMapping(value = "/addPassToUser", method = RequestMethod.POST)
    public void addFoodToCart(@RequestBody Pass pass){
        workoutService.addPassToUser(pass.getId());
    }

    @RequestMapping(value = "/getUserWorkout", method = RequestMethod.GET)
    public List<Workout> getUserWorkout(){
        return workoutService.getUserWorkout();
    }

    @RequestMapping(value = "/getUserPass", method = RequestMethod.GET)
    public List<Pass> getUserPass(){
        return workoutService.getUserPass();
    }

    @RequestMapping(value = "/addWorkout", method = RequestMethod.POST)
    public void addWorkout(@RequestBody Workout workout){
        workoutService.addWorkout(workout);
    }

    @RequestMapping(value = "/addPass", method = RequestMethod.POST)
    public void addPass(@RequestBody Pass pass){
        workoutService.addPass(pass);
    }
}
