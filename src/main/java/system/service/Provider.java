package system.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;
import system.entities.User;

import java.util.ArrayList;
import java.util.List;

@Service("provider")
public class Provider implements AuthenticationProvider
{

    private WorkoutService workoutService;

    @Autowired
    public void setWorkoutServiceService(WorkoutService workoutService)
    {
        this.workoutService = workoutService;
    }


    public Authentication authenticate(Authentication authentication) throws AuthenticationException
    {
        String login = authentication.getName();
        String pwd = authentication.getCredentials().toString();
        String password = WorkoutService.toMD5(pwd);
        User user = null;
        for (Object user1 : workoutService.getAllUsers()){
        if (((User) user1).getName().equals(login) && ((User) user1).getPassword().equals(password)){
        user = (User) user1;
        }
        }
        if (user!=null){
        List<GrantedAuthority> grantedAuth = new ArrayList<GrantedAuthority>();
        grantedAuth.add(new SimpleGrantedAuthority(user.getRole()));
        return new UsernamePasswordAuthenticationToken(login, password, grantedAuth);
        }else {
        return null;
        }
        }

public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
        }
}
