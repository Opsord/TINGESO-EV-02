import './App.css';
import { BrowserRouter, Route, Routes } from 'react-router-dom';
// Home component
import HomeComponent from './components/HomeComponent';
// File upload component
import FileUploadComponent from './components/FileUploadComponent';
// Score components
import ScoreDetailsComponent from './components/ScoreDetailsComponent';
import ScoreListComponent from './components/ScoreListComponent';
import CreateScoreComponent from './components/CreateScoreComponent';
// Student components
import StudentListComponent from './components/StudentListComponent';
import StudentDetailsComponent from './components/StudentDetailsComponent';
import CreateStudentComponent from './components/CreateStudentComponent';

function App() {
    return (
        <div className="App">
            <BrowserRouter>
                <Routes>
                    <Route path="/" element={<HomeComponent />} />
                    <Route path="/files" element={<FileUploadComponent />} />
                    <Route
                        path="/students"
                        element={<StudentListComponent />}
                    />
                    <Route
                        path="/students/:id"
                        element={<StudentDetailsComponent />}
                    />
                    <Route
                        path="/students/create"
                        element={<CreateStudentComponent />}
                    />
                    <Route path="/scores" element={<ScoreListComponent />} />
                    <Route
                        path="/scores/:id"
                        element={<ScoreDetailsComponent />}
                    />
                    <Route
                        path="/scores/create"
                        element={<CreateScoreComponent />}
                    />
                </Routes>
            </BrowserRouter>
        </div>
    );
}

export default App;
