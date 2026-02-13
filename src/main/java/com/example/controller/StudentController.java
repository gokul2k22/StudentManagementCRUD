package com.example.controller;


import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.dto.StudentDTO;
import com.example.service.StudentServiceImpl;

import jakarta.validation.Valid;



@RestController
@RequestMapping("/api/students")
public class StudentController {

	
	@Autowired
	private StudentServiceImpl  service;
	
	
	@PostMapping("/save")
	public ResponseEntity<StudentDTO> createStudent(@Valid @RequestBody StudentDTO stdDTO){
		return new ResponseEntity<>(service.createStd(stdDTO), HttpStatus.CREATED);
	}
	
	
	@GetMapping("/{id}")
	public ResponseEntity<StudentDTO> getStudent(@PathVariable Long id){
		return ResponseEntity.ok(service.getStdById(id));
	}
	
	
	@PutMapping("/put/{id}")
	public ResponseEntity<StudentDTO> updateStd(@PathVariable Long id, @Valid @RequestBody StudentDTO stdDto){
		return ResponseEntity.ok(service.updateStd(id, stdDto));
	}
	
	@DeleteMapping("/del/{id}")
	public ResponseEntity<Map<String, String>> delStd(@PathVariable Long id){
		service.deleteStudent(id);
		   Map<String, String> response = new HashMap<>();
		    response.put("message", "Student deleted successfully");

		    return ResponseEntity.ok(response);
	}
	
	@GetMapping("getall")
	public ResponseEntity<Page<StudentDTO>> getAllStd(
			@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "10") int size,
			@RequestParam(defaultValue = "id")  String sortBy,
			@RequestParam(defaultValue = "asc") String sortDir,
            @RequestParam(required = false) String keyword
			){
		return ResponseEntity.ok(service.getAllStudent(page, size, sortBy, sortDir, keyword));
	}
}




//
//GET Student by ID
//🔹 Endpoint
//GET http://localhost:8080/api/students/1
//
//
//👉 Replace 1 with any existing ID.
//
//No body required.
//
//✅ 3️⃣ UPDATE Student (PUT)
//🔹 Endpoint
//PUT http://localhost:8080/api/students/1
//
//🔹 Body
//{
//  "name": "Arun Kumar S",
//  "email": "arun.kumar.s@gmail.com",
//  "course": "Data Science"
//}
//
//✅ 4️⃣ DELETE Student
//🔹 Endpoint
//DELETE http://localhost:8080/api/students/1
//
//
//No body required.
//
//✅ 5️⃣ GET All Students (Pagination)
//🔹 Default
//GET http://localhost:8080/api/students
//
//🔹 Custom Pagination
//GET http://localhost:8080/api/students?page=0&size=3
//
//
//page=0 → First page
//
//size=3 → 3 records per page
//
//✅ 6️⃣ Sorting
//🔹 Sort by name Ascending
//GET http://localhost:8080/api/students?sortBy=name&sortDir=asc
//
//🔹 Sort by email Descending
//GET http://localhost:8080/api/students?sortBy=email&sortDir=desc
//
//✅ 7️⃣ Search by Name
//🔹 Search "arun"
//GET http://localhost:8080/api/students?keyword=arun
//
//🔹 Search "kavya"
//GET http://localhost:8080/api/students?keyword=kavya
//
//✅ 8️⃣ Combined Example (Search + Pagination + Sorting)
//GET http://localhost:8080/api/students?page=0&size=2&sortBy=name&sortDir=asc&keyword=raj

