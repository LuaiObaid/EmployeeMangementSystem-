import React, { useEffect, useState } from 'react';
import { useNavigate } from 'react-router-dom';
function EmployeeComponent() {
    const navigator = useNavigate();
    const [employeeData, setEmployeeData] = useState({
        firstName: "",
        lastName: "",
        email: ""
      });
    
      const handleChange = (e) => {
        const { name, value } = e.target;
        setEmployeeData(prevData => ({
          ...prevData,
          [name]: value
        }));
      };
    const addEmployee = async (e) => {
      
        e.preventDefault();
        try {
          const response = await fetch("http://localhost:8080/api/employee", {
            method: "POST",
            headers: {
              "Content-Type": "application/json",
            },
            body: JSON.stringify(employeeData),
          });
    
          if (!response.ok) {
            throw new Error(`HTTP error! Status: ${response.status}`);
          }
    
          const data = await response.json();
          console.log("New employee added:", data);
          // Clear the input fields after successful submission
          setEmployeeData({
            firstName: "",
            lastName: "",
            email: ""
          });
        } catch (error) {
          console.error("Error adding employee:", error);
        }
        navigator('/employees')
      };
  return (
    <div className='container' style={{ marginTop: '30px' }}>
    <div className='row justify-content-center'>
      <div className='col-md-6 border p-4'>
        <h2 className='text-center mb-4'>Add Employee</h2>
        <form onSubmit={addEmployee}>
          <div className='form-group'>
            <label htmlFor='firstName'>First Name:</label>
            <input type='text' className='form-control' name='firstName' id='firstName' placeholder='Enter first name' value={employeeData.firstName} onChange={handleChange} required />
          </div>
          <div className='form-group'>
            <label htmlFor='lastName'>Last Name:</label>
            <input type='text' className='form-control' name='lastName' id='lastName' placeholder='Enter last name' value={employeeData.lastName} onChange={handleChange} required />
          </div>
          <div className='form-group'>
            <label htmlFor='email'>Email:</label>
            <input type='email' className='form-control' name='email' id='email' placeholder='Enter email' value={employeeData.email} onChange={handleChange} required />
          </div>
          <button type='submit' className='btn btn-success'>Submit</button>
        </form>
      </div>
    </div>
  </div>
  
  )
}

export default EmployeeComponent