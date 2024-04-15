package com.ilv.helloworld.department;

import com.ilv.helloworld.security.Roles;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.annotation.security.RolesAllowed;
import jakarta.validation.Valid;
import lombok.NonNull;
import org.springframework.context.annotation.Role;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


@RestController
@SecurityRequirement(name = "bearerAuth")
@Validated
public class DepartmentController {



    private final DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @PostMapping("/api/department")
    @RolesAllowed(Roles.Read)
    public ResponseEntity<Department> newDepartment(@Valid @RequestBody @NonNull Department department) {
        Department savedDepartment = departmentService.insertDepartment(department);
        return new ResponseEntity<>(savedDepartment, HttpStatus.OK);
    }

    @PutMapping("/iapi/department/{d}")
    @RolesAllowed(Roles.Update)
    public ResponseEntity<Department> updateDepartment(@Valid @RequestBody @NonNull Department department, @PathVariable @NonNull Long id) {
        Department savedDepartment = departmentService.updateDepartment(department, id);
        return new ResponseEntity<>(savedDepartment, HttpStatus.OK);
    }

    @DeleteMapping("/api/department/{id}")
    @RolesAllowed(Roles.Admin)
    public void deleteDepartment(@PathVariable @NonNull Long id) {
        departmentService.deleteDepartment(id);
    }
}