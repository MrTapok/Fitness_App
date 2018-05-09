package system.dao;

import org.springframework.stereotype.Repository;
import system.entities.Trainer;

import java.util.List;

@Repository
public class TrainerDao extends GenericDao<Trainer>
{
    public List<Trainer> getAllTrainers() {
        return getAll("Trainer");
    }

    public Trainer getTrainer(int id){
        return getElement("from Trainer where id=:n", id);
    }

    public void saveTrainer(Trainer trainer){
        save(trainer);
    }

    public List<Trainer> getTrainer(String name){
        return getList("from Trainer where name=:n", name);
    }

    public void deleteTrainer(Trainer trainer){
        delete(trainer);
    }
}
