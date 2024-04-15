package com.example.demo.departement;
import io.micrometer.common.lang.NonNull;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Validated
@RestController
public class DepartmentController {


        @PostMapping("api/department")
        public ResponseEntity<Department> newDepartment(@Valid @RequestBody @NonNull Department department) {
            Department savedDepartment = DepartmentService.insertDepartment(department);
            return new ResponseEntity<>(savedDepartment, HttpStatus.OK);
        }

        @PutMapping("api/department/{id}")
        public ResponseEntity<Department> updateDepartment(@Valid @RequestBody @NonNull Department department, @PathVariable @NonNull Long id) {
            Department savedDepartment = DepartmentService.updateDepartment(department, id);
            return new ResponseEntity<>(savedDepartment, HttpStatus.OK);
        }

        @DeleteMapping("api/department/{id}")
        public void deleteDepartment(@PathVariable @NonNull Long id) {
            DepartmentService.deleteDepartment(id);

    }

}
