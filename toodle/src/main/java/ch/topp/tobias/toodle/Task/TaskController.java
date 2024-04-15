package ch.topp.tobias.toodle.Task;

import jakarta.annotation.security.RolesAllowed;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

public class TaskController {


    @GetMapping("api/department")
    @RolesAllowed(Roles.Read)
    public ResponseEntity<List<Department>> all() {
        List<Department> result = departmentService.getDepartments();
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
