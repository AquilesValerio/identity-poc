package br.com.domain.entities;

import br.com.domain._share.NotificationPattern;
import br.com.domain.validator.RoleValidator;
import br.com.domain.validator.UserValidator;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.util.List;

@Entity(name = "roles")
@Table(name = "roles")
public class Role {

	public Role(int id, String name) {
		this.id = id;
		this.name = name;
	}

	public Role(String name) {
		this.name = name;
	}

	public Role(int id) {
		this.id = id;
	}

	public Role() {
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;

	@Column(name = "name", nullable = false, length = 50, unique = true)
	private String name;

	@ManyToMany(mappedBy = "roleList", fetch = FetchType.LAZY)
	private List<User> userList;

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void validate(NotificationPattern notificationPattern) {
		new RoleValidator().validate(this, notificationPattern);
	}
}
