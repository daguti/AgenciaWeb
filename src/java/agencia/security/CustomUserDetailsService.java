/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package agencia.security;

import agencia.modelo.Usuario;
import agencia.persistencia.UserDAO;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
/**
 *
 * @author ESa10969
 */ 
public class CustomUserDetailsService implements UserDetailsService {
    private UserDAO userDao;
  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    Usuario user;
    UserDAO dao = new UserDAO();
    
    user = dao.buscarPorUsername(username);
    List<GrantedAuthority> authorities = buildUserAuthority(user.getUserType());
    return buildUserForAuthentication(user, authorities);
  }
  
  private User buildUserForAuthentication(Usuario user, 
		List<GrantedAuthority> authorities) {
		return new User(user.getUserName(), 
			user.getPass(), true, true, true, true, authorities);
	}
  
  private List<GrantedAuthority> buildUserAuthority(int userType) {
    String role = "";
    Set<GrantedAuthority> setAuths = new HashSet<GrantedAuthority>();
    
    // Build user's authorities
    switch(userType) {
      case 0: role = "ROLE_ADMIN";
        break;
      case 1: role = "ROLE_AEREA";
        break;
      case 2: role = "ROLE_HOTELERA";
        break;
      case 3: role = "ROLE_USER";
        break;
    }
    setAuths.add(new SimpleGrantedAuthority(role));

    List<GrantedAuthority> Result = new ArrayList<GrantedAuthority>(setAuths);

    return Result;
  }

    public UserDAO getUserDao() {
        return userDao;
    }

    public void setUserDao(UserDAO userDao) {
        this.userDao = userDao;
    }
  
}
