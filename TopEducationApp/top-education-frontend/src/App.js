import './App.css';
import { BrowserRouter as Router, Route, Routes } from 'react-router-dom';
// Home component
import HomeComponent from './components/HomeComponent';
// Student components
import StudentDetailsComponent from './components/StudentDetailsComponent';
import CreateStudentComponent from './components/CreateStudentComponent';
import StudentListFunComponent from './components/StudentListFunComponent';
import StudentFileUpload from './components/StudentFileUploadComponent';
import ScoreFileUpload from './components/ScoreFileUploadComponent';

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
                    {/* Upload student file */}
                    <Route
                        path="/uploadStudents"
                        element={<StudentFileUpload />}
                    />
                    {/* Upload score file */}
                    <Route path="/uploadScores" element={<ScoreFileUpload />} />
                </Routes>
            </Router>
        </div>
    );
}

export default App;
