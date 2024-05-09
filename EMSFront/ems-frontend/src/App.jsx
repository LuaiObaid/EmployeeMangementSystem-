import { HeaderComponent } from './components/HeaderComponent'
import './App.css'
import ListEmployeeComponent from './components/ListEmployeeComponent'
import FooterCompnent from './components/FooterCompnent'
import EmployeeComponent from './components/EmployeeComponent'
import { BrowserRouter, Route, Routes } from 'react-router-dom'

function App() {
  

  return (
    <>
      <BrowserRouter>
        <HeaderComponent/>
        <Routes>
           <Route path='/' element={ <ListEmployeeComponent/>}></Route>
           <Route path='/employees' element={ <ListEmployeeComponent/>}></Route>
           <Route path='/add-employee' element={ <EmployeeComponent/>}></Route>
        </Routes>
        <FooterCompnent/>
       </BrowserRouter>
    </>
  )
}

export default App
