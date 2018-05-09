package system.dao;

import org.springframework.stereotype.Repository;
import system.entities.WorkoutType;

import java.util.List;

@Repository
public class WorkoutTypeDao extends GenericDao<WorkoutType>
{

    public List<WorkoutType> getAllTypes(){
        return getAll("Type");
    }

    public WorkoutType getType(String name){
        return getElement("from Type where name=:n", name);
    }

    public void saveType(WorkoutType type){
        save(type);
    }

    public List<WorkoutType> getTypes(String name){
        return getList("from Type where name=:n", name);
    }

    public void deleteType(WorkoutType type){
        delete(type);
    }
}
