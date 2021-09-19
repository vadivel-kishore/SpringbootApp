package com.example.application.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="Customer")
public class Customer {

	@Id
	@SequenceGenerator(
			name="customer_seq",
			allocationSize=1
			)
	
	@GeneratedValue(
			strategy=GenerationType.SEQUENCE,
			generator = "customer_seq"
			)
	
	
	private Long id;
	private String firstName;
	private String lastName;
	private String emailID;
	
}
