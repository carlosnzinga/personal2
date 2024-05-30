package ch.coop.personal.personal.dao.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "test")
public class Personal {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID", unique = true)
	private int id;

	@Column(name = "name")
	private String name;

	@Column(name = "age", nullable = false)
	private String age;

	@Column(name = "gender")
	private String gender;

	@Column(name = "address")
	private String address;

	@Column(name = "nationality")
	private String nationality;

	@Column(name = "date", nullable = false)
	private LocalDateTime date;

}
