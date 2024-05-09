import React, { useEffect, useState } from 'react';
import { useNavigate } from 'react-router-dom';
function ListEmployeeComponent() {
  const[getEmployees, setEmployees] = useState([])

  const navigator = useNavigate();
 

  const addEmployee = async () => {
    navigator('/add-employee')
 
  };
useEffect(()=>{
  const getEmployees = async ()=>{
    try {
        const res = await fetch('http://localhost:8080/api/employee')
        const data = await res.json();
        setEmployees(data)
         console.log(data)
    } catch (error) {
        console.log(error.message)
    }
    
  }
  getEmployees()
},[])
  return (
      <div className='container'>
      <h2 className='text-center'>Employee Details</h2>
      <button className='btn btn-primary mb-2' onClick={addEmployee}> Add Employee</button>
        <table className='table table-striped table-bordered'>
             <thead>
                <tr>
                    <th>Emplyoee Id</th>
                    <th>Emplyoee First Name</th>
                    <th>Emplyoee Last Name</th>
                    <th>Emplyoee Email</th>
                </tr>
             </thead>
             <tbody>
             {getEmployees.map(employee => (
                  <tr key={employee.id}>
                  <td>{employee.id}</td>
                  <td>{employee.firstName}</td>
                  <td>{employee.lastName}</td>
                  <td>{employee.email}</td>
                   </tr>
             ))}
               
             </tbody>
        </table>
      </div>

  );
}

export default ListEmployeeComponent;
