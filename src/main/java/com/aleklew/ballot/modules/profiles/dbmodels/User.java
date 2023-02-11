package com.aleklew.ballot.modules.profiles.dbmodels;

import java.time.LocalDate;
import java.util.*;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.aleklew.ballot.modules.ballot.dbmodels.BallotEntity;
import com.aleklew.ballot.modules.general.infrastructure.HelperMethods;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Entity
@Table(name="Users")
//@JsonIdentityInfo(property = "userID", generator = ObjectIdGenerators.PropertyGenerator.class)
public class User implements UserDetails {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="UserID")
	private int userID;

	@JsonIgnore
	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "ownerId", fetch = FetchType.LAZY)
	private Set<BallotEntity> ballotEntities;

	@Column(name="Username")
	private String userName;

	@Column(name="FirstName")
	private String firstName;

	@Column(name="LastName")
	private String lastName;

	@Column(name="Email")
	private String email;

	@Column(name="Birthday", columnDefinition = "DATE")
	private LocalDate localDate;

	@Column(name="PESEL")
	private String pesel;

	@ManyToOne
	@JoinColumn(name="RoleID")
	private Role role;

	@Column(name="PwdHash")
	private String pwdHash;

	@Column(name="ActivationCode")
	private String activationCode;

	@Column(name="ChangePasswordCode")
	private String changePasswordCode;

	@Column(name = "PasswordCodeExpDate")
	private Date passwordCodeExpDate;

	@Column(name="AccountStatus")
	private int accountStatus;

	public User(String userName, String password, String firstName, String lastName, String pesel, String email) {
		this.userName = userName;
		this.firstName = firstName;
		this.lastName = lastName;
		this.pesel = pesel;
		this.email = email;
		this.pwdHash = HelperMethods.passwordToHash(password, userName);
	}

	public User() {
	}

	// region GETTERS AND SETTERS

	public int getID() {
		return userID;
	}
		
	public String getFirstName()
	{
		return firstName;
	}

	public String getLastName()
	{
		return lastName;
	}

	public String getPESEL()
	{
		return pesel;
	}

	public String getActivationCode() {
		return activationCode;
	}

	public String getEmail() {
		return email;
	}

	public int getAccountStatus() {
		return accountStatus;
	}

	public void setAccountStatus(int accountStatus) {
		this.accountStatus = accountStatus;
	}

	public void setActivationCode(String activationCode) {
		this.activationCode = activationCode;
	}

	public String getChangePasswordCode() {
		return changePasswordCode;
	}

	public void setChangePasswordCode(String changePasswordCode) {
		this.changePasswordCode = changePasswordCode;
	}

	public Date getPasswordCodeExpDate() {
		return passwordCodeExpDate;
	}

	public void setPasswordCodeExpDate(Date passwordCodeExpDate) {
		this.passwordCodeExpDate = passwordCodeExpDate;
	}

	public void setPassword(String password)
	{
		pwdHash = HelperMethods.passwordToHash(password, userName);
	}

	public boolean checkPassword(String password)
	{
		return Objects.equals(pwdHash, HelperMethods.passwordToHash(password, userName));
	}

	Role getRole()
	{
		return role;
	}

	public void setRole(Role r)
	{
		role = r;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return Arrays.asList(role);
	}

	@Override
	public String getPassword() {
		return pwdHash;
	}


	@Override
	public String getUsername() {
		return userName;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return accountStatus == 2;
	}



	// endregion

}
