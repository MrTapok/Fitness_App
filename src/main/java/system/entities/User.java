package system.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "user", schema = "sql7239701")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "role", nullable = false)
    private String role;

    @Column(name = "rating")
    private int rating;

    @Column(name = "vote")
    private int vote;

    @ManyToMany(cascade = { CascadeType.ALL })
    @JoinTable(
            name = "user_workout",
            joinColumns = { @JoinColumn(name = "user_id") },
            inverseJoinColumns = { @JoinColumn(name = "workout_id") }
    )
    @JsonIgnore
    private List<Workout> workouts;

    @ManyToMany(cascade = { CascadeType.ALL })
    @JoinTable(
            name = "user_pass",
            joinColumns = { @JoinColumn(name = "userp_id") },
            inverseJoinColumns = { @JoinColumn(name = "pass_id") }
    )
    @JsonIgnore
    private List<Pass> passes;

    public List<Workout> getWorkouts() {
        return workouts;
    }

    public List<Pass> getPasses() {
        return passes;
    }

    public void setWorkouts(List<Workout> cart) {
        this.workouts = cart;
    }

    public int getVote() {
        return vote;
    }

    public void setVote(int vote) {
        this.vote = vote;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
