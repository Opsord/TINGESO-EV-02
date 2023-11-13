import { Component } from 'react';
import PropTypes from 'prop-types';
import styled from 'styled-components';
import HeaderComponent from './HeaderComponent';
// Services import
import StudentService from '../services/StudentService'; // Import the service that provides student data
import AdminOfficeService from '../services/AdminOfficeService';

class ListStudentComponent extends Component {
    constructor(props) {
        
        super(props);
        this.state = {
            selectedRUT: '',
            students: [], // Initialize students state
        };
        // Bind de los métodos
        this.addStudent = this.addStudent.bind(this);
        this.viewStudent = this.viewStudent.bind(this);
        this.updateStudent = this.updateStudent.bind(this);
        this.deleteStudent = this.deleteStudent.bind(this);
    }

    static propTypes = {
        history: PropTypes.object.isRequired,
    };

    addStudent() {
        // Add a student
        this.props.history.push('/create');
    }

    viewStudent(rut) {
        // Navigate to the student details page
        this.props.history.push(`/${rut}`);
    }

    refreshTable() {
        // Fetch updated student data and update the state
        StudentService.getAllStudents().then((res) => {
            this.setState({ students: res.data });
        });
    }

    updateStudent(rut) {
        // Get student by id and update
        AdminOfficeService.updateStudentInfo(rut).then(() => {
            // After updating, refresh the table
            this.refreshTable();
        });
    }

    deleteStudent(rut) {
        // Delete a student
        StudentService.deleteStudent(rut).then(() => {
            // After deleting, refresh the table
            this.refreshTable();
        });
    }

    componentDidMount() {
        StudentService.getAllStudents().then((res) => {
            // Fetch student data when the component mounts
            this.setState({ students: res.data });
        });
    }

    render() {
        return (
            <div>
                <StudentPageStyle>
                    <HeaderComponent />
                    <h2 className="table-name">Students List</h2>
                    <div className="row">
                        <table className="student-table">
                            <thead>
                                <tr>
                                    <th> Student RUT</th>
                                    <th> School name</th>
                                    <th> Exams taken</th>
                                    <th> Average score</th>
                                    <th className="buttons"> Actions</th>
                                </tr>
                            </thead>
                            <tbody>
                                {this.state.students.map(
                                    // Map over the students state to create a table row for each student
                                    (student) => (
                                        <tr key={student.rut}>
                                            <td> {student.rut} </td>
                                            <td> {student.schoolName}</td>
                                            <td> {student.examsTaken}</td>
                                            <td> {student.averageScore}</td>
                                            <td>
                                                <button
                                                    onClick={() =>
                                                        this.viewStudent(
                                                            student.rut
                                                        )
                                                    }
                                                    className="btn btn-info"
                                                >
                                                    View
                                                </button>
                                                <button
                                                    style={{
                                                        marginLeft: '10px',
                                                    }}
                                                    onClick={() =>
                                                        this.updateStudent(
                                                            student.rut
                                                        )
                                                    }
                                                    className="btn btn-info"
                                                >
                                                    Update
                                                </button>
                                                <button
                                                    style={{
                                                        marginLeft: '10px',
                                                    }}
                                                    onClick={() =>
                                                        this.deleteStudent(
                                                            student.rut
                                                        )
                                                    }
                                                    className="btn btn-danger"
                                                >
                                                    Delete
                                                </button>
                                            </td>
                                        </tr>
                                    )
                                )}
                            </tbody>
                        </table>
                    </div>
                </StudentPageStyle>
            </div>
        );
    }
}

export default ListStudentComponent;

const StudentPageStyle = styled.div`

    .table-name {
        text-align: center;
        font-size: 30px;
        margin-top: 30px;
        justify-content: center;
        padding-top: 8px;
        font-family: 'Arial Black', Gadget, sans-serif;
        letter-spacing: 0px;
        word-spacing: 2px;
        color: #000000;
        font-weight: 700;
        text-decoration: none solid rgb(68, 68, 68);
        font-style: normal;
        font-variant: normal;
        text-transform: uppercase;
    }

    .table {
        margin: auto;
        width: 80% !important;
        border-collapse: collapse;
    }

    .student-table {
        margin: auto;
        width: 80% !important;
        border-collapse: collapse;
        min-width: 400px;
        border-radius: 5px 5px 0 0;
        overflow: hidden;
        box-shadow: 0 0 30px rgba(0, 0, 0, 0.15);
    }

    .student-table thead tr {
        background-color: #16537e;
        color: #ffffff;
        text-align: center;
        font-weight: bold;
        font-size: 20px;
    }

    .student-table thead th {
        border: 5px solid #ffffff;
        padding: 12px 15px;
        border-radius: 15px;
    }

    .buttons {
        text-align: center;
        background-color: #16537e;
    }

    .student-table tbody tr {
        text-align: center;
        border: 2px solid #ffffff;
        background-color: #bcbcbc;
        height: 50px;
    }

    .student-table tbody td {
        border: 2px solid #ffffff;
    }
`;
