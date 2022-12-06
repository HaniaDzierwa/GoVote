package com.aleklew.ballot;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="Roles", schema="dbo")
public class Role {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="RoleID")
	private int roleID;

	@Column(name = "RoleName")
	public String roleName;

	@Column(name = "RoleDescription")
	public String roleDescription;

	@Column(name = "PermVote")
	public Boolean permVote;

	@Column(name = "PermCreateNewBallot")
	public Boolean permCreateNewBallot;

	@Column(name = "PermDeleteOwnBallot")
	public Boolean permDeleteOwnBallot;

	@Column(name = "PermDeleteAnyBallot")
	public Boolean permDeleteAnyBallot;

	@Column(name = "PermViewUser")
	public Boolean permViewUser;

	@Column(name = "PermDeleteUser")
	public Boolean permDeleteUser;

	public int getRoleID() {
        return this.roleID;
    }

    public String getRoleName() {
        return this.roleName;
    }

    public String getRoleDescription() {
        return this.roleDescription;
    }

    public Boolean canVote() {
        return this.permVote;
    }

    public Boolean canCreateNewBallot() {
        return this.permCreateNewBallot;
    }

    public Boolean canDeleteOwnBallot() {
        return this.permDeleteOwnBallot;
    }

    public Boolean canDeleteAnyBallot() {
        return this.permDeleteAnyBallot;
    }

    public Boolean canViewUser() {
        return this.permViewUser;
    }

    public Boolean canDeleteUser() {
        return this.permDeleteUser;
    }
}
