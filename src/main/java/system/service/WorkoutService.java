package system.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import system.dao.*;
import system.entities.Workout;
import system.entities.User;
import system.entities.Pass;

import java.security.MessageDigest;
import java.util.*;

@Service
public class WorkoutService
{
    private UserDao userDao;

    private WorkoutDao workoutDao;

    private WorkoutTypeDao workouttypeDao;

    private PassDao passDao;

    @Autowired
    public void setFoodDao(WorkoutDao workoutDao) {
        this.workoutDao = workoutDao;
    }

    @Autowired
    public void setWorkoutTypeDao(WorkoutTypeDao workouttypeDao) {
        this.workouttypeDao = workouttypeDao;
    }

    @Autowired
    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    @Autowired
    public void setTrainerDao(PassDao trainerDao) {
        this.passDao = passDao;
    }



    public void deleteUser(int id)
    {
        User user = userDao.getUser(id);
        userDao.deleteUser(user);
    }

    public User getCurrentUser() {
        User user = userDao.getUser(getUserName());
        return user;
    }

    public String getUserName()
    {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return auth.getName();
    }

    @Secured("ROLE_USER")
    public void addWorkoutToUser(int id) {
        User user = getCurrentUser();
        List<Workout> workouts = user.getWorkouts();
        workouts.add(getWorkout(id));
        userDao.update(user);
    }

    @Secured("ROLE_USER")
    public void addPassToUser(int id) {
        User user = getCurrentUser();
        List<Pass> passes = user.getPasses();
        passes.add(getPass(id));
        userDao.update(user);
    }

    public void addTrainerToWorkout(int trainer_id, int workout_id)
    {

    }

    public List<Workout> getWorkout() {
        User user = getCurrentUser();
        List<Workout> workouts = user.getWorkouts();
        return workouts;
    }

    public List<Pass> getPass() {
        User user = getCurrentUser();
        List<Pass> passes = user.getPasses();
        return passes;
    }

    public boolean addWorkout(Workout workout)
    {
        List<Workout> workouts = workoutDao.getWorkoutByName(workout.getName());
        if (!workout.getName().equals("") && workouts.isEmpty()) {
            Workout newWorkout = new Workout();
            newWorkout.setName(workout.getName());
            newWorkout.setPrice(workout.getPrice());
            newWorkout.setRemain(workout.getRemain());
            newWorkout.setType(workout.getType());
            workoutDao.saveWorkout(newWorkout);
            return true;
        }
        return false;
    }

    public boolean addPass(Pass pass)
    {
        List<Pass> passes = passDao.getPassByName(pass.getName());
        if (!pass.getName().equals("") && passes.isEmpty()) {
            Pass newPass = new Pass();
            newPass.setName(pass.getName());
            newPass.setPrice(pass.getPrice());
            newPass.setRemain(pass.getRemain());
            newPass.setType(pass.getType());
            passDao.savePass(newPass);
            return true;
        }
        return false;
    }


    public boolean addUser(User user)
    {
        List<User> users = userDao.getUserByName(user.getName());
        if (!user.getName().equals("") && !user.getPassword().equals("") && users.isEmpty()) {
            User newUser = new User();
            newUser.setName(user.getName());
            newUser.setPassword(WorkoutService.toMD5(user.getPassword()));
            newUser.setRole("ROLE_USER");
            userDao.saveUser(newUser);
            return true;
        }
        return false;
    }

    public List getTrainers()
    {
        List<User> trainers = userDao.getUsersByRole("ROLE_TRAINER");
        return trainers;
    }

    public List getAllUsers() {
        return userDao.getAllUsers();
    }

    public List getAllWorkouts() {
        return workoutDao.getAllWorkout();
    }

    public Workout getWorkout(int id) {
        return workoutDao.getWorkout(id);
    }

    public Pass getPass(int id) {
        return passDao.getPass(id);
    }

    public List getAllTypes() {
        return workouttypeDao.getAllTypes();
    }

    public List getAllPasses(){
        return passDao.getAllPass();
    }

    static public String toMD5(String md5) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] array = md.digest(md5.getBytes());
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < array.length; ++i) {
                sb.append(Integer.toHexString((array[i] & 0xFF) | 0x100).substring(1,3));
            }
            return sb.toString();
        } catch (java.security.NoSuchAlgorithmException e) {
        }
        return null;
    }


    public void addAdmin(User user)
    {
        if (user.getName()!=null && user.getPassword()!=null){
            user.setRole("ROLE_ADMIN");
            user.setPassword(toMD5(user.getPassword()));
            userDao.saveUser(user);
        }
    }

    public void addManager(User user)
    {
        if (user.getName()!=null && user.getPassword()!=null){
            user.setRole("ROLE_MANAGER");
            user.setPassword(toMD5(user.getPassword()));
            userDao.saveUser(user);
        }
    }

    public void addTrainer(User user)
    {
        if (user.getName()!=null && user.getPassword()!=null){
            user.setRole("ROLE_MANAGER");
            user.setPassword(toMD5(user.getPassword()));
            userDao.saveUser(user);
        }
    }
}
