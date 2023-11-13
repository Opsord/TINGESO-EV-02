import { useNavigate } from 'react-router-dom';
import { useEffect, useState } from 'react';
// Components import
import HeaderComponent from './HeaderComponent';
// CSS import
import '../css/TableFormat.css';
// Services import
import StudentService from '../services/StudentService'; // Import the service that provides student data
import AdminOfficeService from '../services/AdminOfficeService';
import InstallmentService from '../services/InstallmentService';

export default function StudentListFunComponent() {
    // Initialize the state
    const initialState = {
        studentRUT: '',
        studentList: [],
    };
    // Declare the state variable and the function to update it
    const navigate = useNavigate();

    const [input, setInput] = useState(initialState);

    useEffect(() => {
        StudentService.getAllStudents().then((res) => {
            setInput({ ...input, studentList: res.data }); // Actualizar el estado correctamente
        });
    }, []);

    // Navegate to the student details page
    const seeDetails = (studentRUT) => {
        navigate('/students/' + studentRUT);
    };

    // Update a student
    const updateStudent = (studentRUT) => {
        AdminOfficeService.updateStudentInfo(studentRUT).then(() => {
            // After updating, refresh the table
            refreshTable();
        });
    };

    // Delete a student and its installments
    const deleteStudent = (studentRUT) => {
        // Delete all the installments of the student
        InstallmentService.deleteInstallmentsByRUT(studentRUT).then(
            () => {
                // Delete the student
                StudentService.deleteStudent(studentRUT).then(() => {
                    // After deleting, refresh the table
                    refreshTable();
                });
            }
        );
    }

    // Refresh the table
    const refreshTable = () => {
        StudentService.getAllStudents().then((res) => {
            setInput({ ...input, studentList: res.data }); // Actualizar el estado correctamente
        });
    };

    return (
        <div>
            <HeaderComponent />
            <div className="main-div">
                <h1>
                    <b>Student List</b>
                </h1>
                <table className="content-table">
                    <thead>
                        <tr>
                            <th>Student RUT</th>
                            <th>School name</th>
                            <th>Exams taken</th>
                            <th>Average score</th>
                            <th>Actions</th>
                        </tr>
                    </thead>
                    <tbody>
                        {input.studentList.map((student) => (
                            <tr key={student.rut}>
                                <td> {student.rut}</td>
                                <td> {student.schoolName} </td>
                                <td> {student.examsTaken} </td>
                                <td> {student.averageScore} </td>
                                <td>
                                    <button
                                        className="input-plan-boton"
                                        onClick={() => seeDetails(student.rut)}
                                    >
                                        Details
                                    </button>
                                    <button
                                        className="input-plan-boton"
                                        onClick={() =>
                                            updateStudent(student.rut)
                                        }
                                    >
                                        Update
                                    </button>
                                    <button
                                        className="input-plan-boton"
                                        onClick={() =>
                                            deleteStudent(student.rut)
                                        }
                                    >
                                        Delete
                                    </button>
                                </td>
                            </tr>
                        ))}
                    </tbody>
                </table>
            </div>
        </div>
    );
}
