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
    public void setPassDao(PassDao passDao) {
        this.passDao = passDao;
    }



    public void deleteUser(int id) //check
    {
        User user = userDao.getUser(id);
        userDao.deleteUser(user);
    }

    public User getCurrentUser() {  //check
        User user = userDao.getUser(getUserName());
        return user;
    }

    public String getUserName()  //check
    {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return auth.getName();
    }

    @Secured("ROLE_USER")  //check
    public void addWorkoutToUser(int id) {
        User user = getCurrentUser();
        Workout work = getWorkout(id);
        List<Workout> workouts = user.getWorkouts();
        workouts.add(getWorkout(id));
        work.setRemain(work.getRemain() - 1);
        userDao.update(user);
        workoutDao.update(work);
    }

    @Secured("ROLE_USER")  //check
    public void addPassToUser(int id) {
        User user = getCurrentUser();
        Pass ps = getPass(id);
        List<Pass> passes = user.getPasses();
        passes.add(getPass(id));
        ps.setRemain(ps.getRemain() - 1);
        userDao.update(user);
        passDao.update(ps);
    }

    public List<Workout> getUserWorkout() {  //check
        User user = getCurrentUser();
        List<Workout> workouts = user.getWorkouts();
        return workouts;
    }

    public List<Pass> getUserPass() {  //check
        User user = getCurrentUser();
        List<Pass> passes = user.getPasses();
        return passes;
    }

    @Secured("ROLE_MANAGER")
    public boolean addWorkout(Workout workout)  //check
    {
        List<Workout> workouts = workoutDao.getWorkoutByName(workout.getName());
        if (!workout.getName().equals("") && workouts.isEmpty()) {
            Workout newWorkout = new Workout();
            newWorkout.setName(workout.getName());
            newWorkout.setPrice(workout.getPrice());
            newWorkout.setDescription(workout.getDescription());
            newWorkout.setRemain(workout.getRemain());
            newWorkout.setType(workout.getType());
            workoutDao.saveWorkout(newWorkout);
            return true;
        }
        return false;
    }

    @Secured("ROLE_MANAGER")
    public boolean addPass(Pass pass) //check
    {
        List<Pass> passes = passDao.getPassByName(pass.getName());
        if (!pass.getName().equals("") && passes.isEmpty()) {
            Pass newPass = new Pass();
            newPass.setName(pass.getName());
            newPass.setPrice(pass.getPrice());
            newPass.setDescription(pass.getDescription());
            newPass.setRemain(pass.getRemain());
            newPass.setType(pass.getType());
            passDao.savePass(newPass);
            return true;
        }
        return false;
    }

    public boolean addUser(User user)  //check
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

    public List getTrainers()  //check
    {
        List<User> trainers = userDao.getUsersByRole("ROLE_TRAINER");
        return trainers;
    }

    public List getManagers()  //check
    {
        List<User> managers = userDao.getUsersByRole("ROLE_MANAGER");
        return managers;
    }

    public List getAdmins()  //check
    {
        List<User> admins = userDao.getUsersByRole("ROLE_ADMIN");
        return admins;
    }

    public List getAllUsers() {  //check
        return userDao.getAllUsers();
    }  //check

    public List getAllWorkouts() {  //check
        return workoutDao.getAllWorkout();
    }

    public List getAllTypes() {  //check
        return workouttypeDao.getAllTypes();
    }

    public List getAllPasses(){  //check
        return passDao.getAllPass();
    }

    public Workout getWorkout(int id) {
        return workoutDao.getWorkout(id);
    }

    public Pass getPass(int id) {
        return passDao.getPass(id);
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


    public void addAdmin(User user)  //check
    {
        if (user.getName()!=null && user.getPassword()!=null){
            user.setRole("ROLE_ADMIN");
            user.setPassword(toMD5(user.getPassword()));
            userDao.saveUser(user);
        }
    }

    public void addManager(User user)  //check
    {
        if (user.getName()!=null && user.getPassword()!=null){
            user.setRole("ROLE_MANAGER");
            user.setPassword(toMD5(user.getPassword()));
            userDao.saveUser(user);
        }
    }

    public void addTrainer(User user)  //check
    {
        if (user.getName()!=null && user.getPassword()!=null){
            user.setRole("ROLE_MANAGER");
            user.setPassword(toMD5(user.getPassword()));
            userDao.saveUser(user);
        }
    }
}
