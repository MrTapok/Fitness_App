package system.dao;

import org.springframework.stereotype.Repository;
import system.entities.Workout;

import java.util.List;

@Repository
public class WorkoutDao extends GenericDao<Workout>
{
    public List<Workout> getAllWorkout() {
        return getAll("Workout");
    }

    public Workout getWorkout(int id) {
        return getElement("from Workout where id=:n", id);
    }

    public void saveWorkout(Workout workout) {
        save(workout);
    }

    public List<Workout> getWorkoutByName(String name) {
        return getList("from Workouts where name=:n", name);
    }

    public void deleteWorkout(Workout workout) {
        delete(workout);
    }
}
