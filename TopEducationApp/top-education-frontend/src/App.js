import './App.css';
import { BrowserRouter as Router, Route, Routes } from 'react-router-dom';
// Home component
import HomeComponent from './components/HomeComponent';
// Student components
import StudentDetailsComponent from './components/StudentDetailsComponent';
import CreateStudentComponent from './components/CreateStudentComponent';
import StudentListFunComponent from './components/StudentListFunComponent';

function App() {
    return (
        <div>
            <Router>
                <Routes>
                    {/* Home page */}
                    <Route path="/" element={<HomeComponent />} />
                    {/* Student list */}
                    <Route
                        path="/students"
                        element={<StudentListFunComponent />}
                    />
                    {/* Student details */}
                    <Route
                        path="/students/:rut"
                        element={<StudentDetailsComponent />}
                    />
                    {/* Create student */}
                    <Route
                        path="/create"
                        element={<CreateStudentComponent />}
                    />
                </Routes>
            </Router>
        </div>
    );
}

export default App;
