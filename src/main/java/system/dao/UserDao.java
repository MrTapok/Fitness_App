package system.dao;

import org.springframework.stereotype.Repository;
import system.entities.User;

import java.util.List;

@Repository

public class UserDao extends GenericDao<User>
{
    public List<User> getAllUsers(){
        return getAll("User");
    }

    public User getUser(String name){
        return getElement("from User where name=:n", name);
    }

    public User getUser(int id){
        return getElement("from User where id=:n", id);
    }

    public void saveUser(User user){
        save(user);
    }

    public void updateUser(User user){
        update(user);
    }

    public List<User> getUserByName(String name){
        return getList("from User where name=:n", name);
    }

    public List<User> getUsersByRole(String name){ return getList("from User where name=:n", name); }

    public void deleteUser(User user){
        delete(user);
    }
}
