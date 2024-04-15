package com.example.demo.departement;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentService {

    private static Departmentrepository repository;

    public DepartmentService(Departmentrepository.DepartmentRepository repository) {
        this.repository = (Departmentrepository) repository;
    }

    public List<Department> getDepartments() {
        return repository.findByOrderByNameAsc();
    }

    public Department getDepartment(Long id) {
        return repository.findById(id)
                .orElseThrow(EntityNotFoundException::new);
    }

    public static Department insertDepartment(Department department) {
        return repository.save(department);
    }

    public static Department updateDepartment(Department department, Long id) {
        return repository.findById(id)
                .map(departmentOrig -> {
                    departmentOrig.setName(department.getName());
                    return repository.save(departmentOrig);
                })
                .orElseGet(() -> repository.save(department));
    }

    public static void deleteDepartment(Long id) {
        repository.deleteById(id);
    }
}