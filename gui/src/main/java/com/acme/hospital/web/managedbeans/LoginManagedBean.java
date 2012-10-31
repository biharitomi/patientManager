package com.acme.hospital.web.managedbeans;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

@ManagedBean(name = "LoginManagedBean")
@SessionScoped
public class LoginManagedBean {
	private String userName = "";
	private String password = "";
	private String loggedInUser="";

	@ManagedProperty(value = "#{authenticationManager}")
	private AuthenticationManager am;

	public String login() {
		try {
			Authentication request = new UsernamePasswordAuthenticationToken(
					this.getUserName(), this.getPassword());
			Authentication result = am.authenticate(request);
			SecurityContextHolder.getContext().setAuthentication(result);
		} catch (BadCredentialsException e) {
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error",
							"Wrong username or password!"));
			return "login";
		}
		
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if (principal instanceof UserDetails) {
			loggedInUser = ((UserDetails)principal).getUsername();
		} else {
			loggedInUser = principal.toString();
		}
		
		return "views/index?faces-redirect=true";
	}
	
	public String logout(){
		SecurityContextHolder.getContext().getAuthentication().setAuthenticated(false);
		System.out.println("******** LOGOUT was successfully *******");
		return "login?faces-redirect=true";
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public AuthenticationManager getAm() {
		return am;
	}

	public void setAm(AuthenticationManager am) {
		this.am = am;
	}

	public String getLoggedInUser() {
		return loggedInUser;
	}

	public void setLoggedInUser(String loggedInUser) {
		this.loggedInUser = loggedInUser;
	}
	
}
