package ch.coop.personal.personal.dao.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "personal_info")
public class Personal {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
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
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDateTime date;
}
