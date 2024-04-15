package com.ilv.helloworld;

import com.ilv.helloworld.department.Department;
import com.ilv.helloworld.department.DepartmentRepository;
import com.ilv.helloworld.department.DepartmentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

class DepartmentServiceTests {

    private DepartmentService departmentService;
    private final DepartmentRepository departmentRepositoryMock = mock(DepartmentRepository.class);

    private final Department departmentMock = mock(Department.class);

    @BeforeEach
    void setUp() {
        departmentService = new DepartmentService(departmentRepositoryMock);
    }

    @Test
    void createDepartment() {
        when(departmentRepositoryMock.save(departmentMock)).thenReturn(departmentMock);
        departmentService.insertDepartment(departmentMock);
        verify(departmentRepositoryMock, times(1)).save(any());
    }
}