package com.ilv.helloworld;

import com.ilv.helloworld.department.Department;
import com.ilv.helloworld.department.DepartmentRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

@DataJpaTest()
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(false)
class DBTests {

    @Autowired
    private DepartmentRepository departmentRepository;

    @Test
    void insertDepartment() {
        Department depA = this.departmentRepository.save(new Department(1l, "Abteilung A"));
        Assertions.assertNotNull(depA.getId());
        Department depB = this.departmentRepository.save(new Department(2l, "Abteilung B"));
        Assertions.assertNotNull(depB.getId());
    }
}