package com.example.dto;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StudentDTO {

	private Long id;
	
	@NotBlank(message = "Name is mandatory")
	private String name;
	
	@Email(message = "Email should be Valid")
	private String email;
	
	
	private String course;
}
