package net.javaguides.emsbackend.service.impl;

import lombok.AllArgsConstructor;
import net.javaguides.emsbackend.dto.EmployeeDto;
import net.javaguides.emsbackend.entity.Employee;
import net.javaguides.emsbackend.exception.ResourceNotFoundException;
import net.javaguides.emsbackend.mapper.EmployeeMapper;
import net.javaguides.emsbackend.repository.EmployeeRepository;
import net.javaguides.emsbackend.service.EmployeeService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class EmployeeServiceimpl implements EmployeeService {

    private EmployeeRepository employeeRepository;

    @Override
    public EmployeeDto createEmployee (EmployeeDto employeeDto){

        Employee employee = EmployeeMapper.mapToEmployee(employeeDto);
        Employee saveEmployee = employeeRepository.save(employee);

        return EmployeeMapper.mapToEmployeeDto(saveEmployee);
    }
    @Override
    public EmployeeDto getEmployeeById(Long employeeId) {
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new ResourceNotFoundException("Employee doesn't exist with the given id: " + employeeId));

        // Convert Employee entity to DTO and return
        return EmployeeMapper.mapToEmployeeDto(employee);
    }

    @Override
    public List<EmployeeDto> getAllEmployees() {
        List<Employee> employees = employeeRepository.findAll();
        return employees.stream()
                .map(employee -> EmployeeMapper.mapToEmployeeDto(employee))
                .collect(Collectors.toList());
    }

    @Override
    public EmployeeDto updateEmployee(Long employeeId, EmployeeDto updatedEmployee) {
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found with id: " + employeeId));
        employee.setFirstName(updatedEmployee.getFirstName());
        employee.setLastName(updatedEmployee.getLastName());
        employee.setEmail(updatedEmployee.getEmail());
        Employee updatedEmployeeObj = employeeRepository.save(employee);
        return EmployeeMapper.mapToEmployeeDto(updatedEmployeeObj);
    }
    @Override
    public void deleteEmployee(Long employeeId) {
        if (!employeeRepository.existsById(employeeId)) {
            throw new ResourceNotFoundException("Employee not found with id: " + employeeId);
        }
        employeeRepository.deleteById(employeeId);
    }
}


