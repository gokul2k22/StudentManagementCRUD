package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.example.dto.StudentDTO;
import com.example.entity.Student;
import com.example.repository.StudentRepoSitory;
import org.springframework.data.domain.*;
import org.springframework.beans.BeanUtils;

import com.example.exception.ResourceNotFoundException;

@Service
public class StudentServiceImpl implements StrudentService {

	@Autowired
	private StudentRepoSitory stdRepo;
	
	// Creating student
	@Override
	public StudentDTO createStd(StudentDTO stdDto) {
		// TODO Auto-generated method stub
		Student std = Student.builder()
                .name(stdDto.getName())
                .email(stdDto.getEmail())
                .course(stdDto.getCourse())
                .build();
			Student saved = stdRepo.save(std);
			BeanUtils.copyProperties(saved, stdDto);
			return stdDto;

	}

	@Override
	public StudentDTO getStdById(Long id) {
		// TODO Auto-generated method stub
		
		Student std = stdRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Student Not Found" + id));
		StudentDTO stdDto = StudentDTO.builder().build();
		BeanUtils.copyProperties(std, stdDto);
		return stdDto;
	}

	@Override
	public StudentDTO updateStd(Long id, StudentDTO stdDto) {
		// TODO Auto-generated method stub
		Student std = stdRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Student Not Found" + id));
				
				std.setName(stdDto.getName());
				std.setEmail(stdDto.getEmail());
				std.setCourse(stdDto.getCourse());
				
				Student updt = stdRepo.save(std);
				BeanUtils.copyProperties(std, updt);
		return stdDto;
	}

	@Override
	public void deleteStudent(Long id) {
		// TODO Auto-generated method stub
		
		Student std = stdRepo.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Student Not Found" + id));
		
		stdRepo.delete(std);
	}

	@Override
	public Page<StudentDTO> getAllStudent(int page, int size, String sortBy, String sortDir, String keyword) {
		// TODO Auto-generated method stub
		Sort sort = sortDir.equalsIgnoreCase("asc") ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
		Pageable pageable = PageRequest.of(page, size, sort);
		
		Page<Student> students;
		if(keyword != null && !keyword.isEmpty()) {
			students = stdRepo.findByNameContainingIgnoreCaseOrEmailContainingIgnoreCase(keyword, keyword, pageable);
		} else {
			students = stdRepo.findAll(pageable);
		}
		return students.map(student ->{
			StudentDTO stdDto = StudentDTO.builder().build();
			BeanUtils.copyProperties(student, stdDto);
			return stdDto;
		});
	}

}
