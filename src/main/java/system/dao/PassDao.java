package system.dao;

import org.springframework.stereotype.Repository;
import system.entities.Pass;

import java.util.List;

@Repository
public class PassDao extends GenericDao<Pass>
{
    public List<Pass> getAllPass() {
        return getAll("Pass");
    }

    public Pass getPass(int id) {
        return getElement("from Pass where id=:n", id);
    }

    public void savePass(Pass pass) {
        save(pass);
    }

    public List<Pass> getPassByName(String name) {
        return getList("from Pass where name=:n", name);
    }

    public void deletePass(Pass pass) {
        delete(pass);
    }
}
