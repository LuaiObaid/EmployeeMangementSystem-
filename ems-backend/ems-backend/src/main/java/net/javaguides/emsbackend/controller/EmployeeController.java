package net.javaguides.emsbackend.controller;

import net.javaguides.emsbackend.dto.EmployeeDto;
import net.javaguides.emsbackend.service.EmployeeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin("*")
@RestController
@RequestMapping("/api/employee")
public class EmployeeController {

    private final EmployeeService employeeService;

    // Inject EmployeeService via constructor
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    // build rest api
    @PostMapping
    public ResponseEntity<EmployeeDto> createEmployee(@RequestBody EmployeeDto employeeDto){
        EmployeeDto saveEmployee = employeeService.createEmployee(employeeDto);
        return new ResponseEntity<>(saveEmployee, HttpStatus.CREATED);
    }

    // build Get employee rest api
    @GetMapping("{id}")
   public  ResponseEntity<EmployeeDto> getEmployeeById(@PathVariable("id") Long employeeId){
       EmployeeDto employeeDto= employeeService.getEmployeeById(employeeId);
       return ResponseEntity.ok(employeeDto);
    }

    // build Get all employee rest api
    @GetMapping
    public  ResponseEntity<List<EmployeeDto>> getAllEmployee(){
        List <EmployeeDto> employees= employeeService.getAllEmployees();
        return ResponseEntity.ok(employees);
    }

    // build update  employee rest api
    @PutMapping("{id}")
    public ResponseEntity<EmployeeDto> updateEmployee(@PathVariable("id") Long employeeId, @RequestBody EmployeeDto updatedEmployee) {
        EmployeeDto employeeDto = employeeService.updateEmployee(employeeId, updatedEmployee);
        return ResponseEntity.ok(employeeDto);
    }
    // build delete employee api
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteEmployee(@PathVariable("id") Long employeeId){
        employeeService.deleteEmployee((employeeId));
        return ResponseEntity.ok("Employee deleted successfully");
    }
}
