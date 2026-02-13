package com.example.service;


import org.springframework.data.domain.Page;

import com.example.dto.StudentDTO;

public interface StrudentService {
	StudentDTO createStd(StudentDTO stdDto);
	StudentDTO getStdById(Long id);
	StudentDTO updateStd(Long id, StudentDTO stdDto);
	void deleteStudent(Long id);
	Page<StudentDTO> getAllStudent(int page, int size, String sortBy, String sortDir, String keyword);
	
}
