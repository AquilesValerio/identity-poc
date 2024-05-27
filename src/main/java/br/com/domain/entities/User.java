package br.com.domain.entities;

import br.com.domain._share.NotificationPattern;
import br.com.domain.validator.UserValidator;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity(name = "user")
@Table(name = "users")
public class User {

	public User(Long id, String name, String email, String password, String cpf, List<Byte> rolesIds) {
		this.id = id;
		this.name = name;
		this.email = email;
		this.password = password;
		this.cpf = cpf;

		if (roleList == null) {
			roleList = new ArrayList<>();
		}
		for (var roleId : rolesIds) {
			roleList.add(new Role(roleId));
		}
	}

	public User(String name, String email, String password, String cpf, List<Byte> rolesIds) {

		this.name = name;
		this.email = email;
		this.password = password;
		this.cpf = cpf;

		if (roleList == null) {
			roleList = new ArrayList<>();
		}
		for (var roleId : rolesIds) {
			roleList.add(new Role(roleId));
		}
	}

	public User(String email, String password) {
		this.email = email;
		this.password = password;
	}

	public User() {}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private long id;

	@Column(name = "name", nullable = false, length = 50)
	private String name;
	@Column(name = "email", nullable = false, length = 50, unique = true)
	private String email;
	@Column(name = "password", nullable = false, length = 100)
	private String password;
	@Column(name = "cpf", nullable = false, unique = true, length = 14)
	private String cpf;

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "user_role",
		joinColumns = @JoinColumn(name = "user_id"),
		inverseJoinColumns = @JoinColumn(name = "role_id"))
	private List<Role> roleList;

	public long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getEmail() {
		return email;
	}

	public String getCpf() {
		return cpf;
	}

	public List<Role> getRoleList() {
		return roleList;
	}

	public void setRoleList(List<Role> roleList) {
		this.roleList = roleList;
	}

	public void setId(long id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getPassword() {
		return password;
	}

	public void validate(NotificationPattern notificationPattern) {
		new UserValidator().validate(this, notificationPattern);
	}
}
