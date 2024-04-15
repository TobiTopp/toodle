package com.example.demo.departement;

import java.util.Arrays;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public class Departmentrepository {

    public List<Department> getDepartments() {
        Department dep1 = new Department(1L, "Abteilung A");
        Department dep2 = new Department(2L, "Abteilung B");
        return Arrays.asList(dep1, dep2);
    }

    public Department getDepartment(Long id) {
        return new Department(id, "Abteilung A");
    }


    public interface DepartmentRepository extends JpaRepository<Department, Long> {
        List<Department> findByOrderByNameAsc();
    }

}






