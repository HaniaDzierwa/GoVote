package com.aleklew.ballot.modules.profiles.models;

import java.security.MessageDigest;
import java.time.LocalDate;
import java.util.Set;

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

import com.aleklew.ballot.modules.general.dbmodels.Ballot;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@Table(name="Users", schema="dbo")
@JsonIdentityInfo(property = "userID", generator = ObjectIdGenerators.PropertyGenerator.class)
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="UserID")
	private int userID;

	@JsonIgnore
	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "owner", fetch = FetchType.EAGER)
	private Set<Ballot> ballots;

	@Column(name="Username")
	private String userName;

	@Column(name="FirstName")
	private String firstName;

	@Column(name="LastName")
	private String lastName;

	@Column(name="Birthday", columnDefinition = "DATE")
	private LocalDate localDate;

	@Column(name="PESEL")
	private String pesel;

	@ManyToOne
	@JoinColumn(name="RoleID")
	private Role role;

	@Column(name="PwdHash")
	private String pwdHash;

	// User(String username, String password, String firstName, String lastName)
	// {
	// 	this.userName = username;
	// 	this.pwdHash = passwordToHash(password);
	// 	this.firstName = firstName;
	// 	this.lastName = lastName;
	// }

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

	private String passwordToHash(String password)
	{
		try
		{
			password = password + "-bajo-jajo-17";
			MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
			messageDigest.update(password.getBytes());
			return new String(messageDigest.digest());
		}
		catch (Exception ex)
		{
			return new String("");
		}
	}

	public void setPassword(String password)
	{
		pwdHash = passwordToHash(password);
	}

	public boolean checkPassword(String password)
	{
		return pwdHash == passwordToHash(password);
	}

	Role getRole()
	{
		return role;
	}

	public void setRole(Role r)
	{
		role = r;
	}

	public Set<Ballot> getOwnedBallots() {
		return ballots;
	}
}
