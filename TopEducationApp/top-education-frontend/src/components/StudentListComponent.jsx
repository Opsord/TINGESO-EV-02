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
            students: [], // Initialize students state
        };
        this.addStudent = this.addStudent.bind(this);
    }

    static propTypes = {
        history: PropTypes.object.isRequired,
    };

    addStudent() {
        // Add a student
        this.props.history.push('/create');
    }

    viewStudent(id) {
        // View a student
        this.props.history.push(`/${id}`);
    }

    updateStudent(id) {
        AdminOfficeService.updateStudentInfo(id);
    }

    deleteStudent(id) {
        // Delete a student
        alert('Deleting a Student is still under construction...');
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
                                    <th className='buttons'> Actions</th>
                                </tr>
                            </thead>
                            <tbody>
                                {this.state.students.map(
                                    // Map over the students state to create a table row for each student
                                    (student) => (
                                        <tr key={student.id}>
                                            <td> {student.rut} </td>
                                            <td> {student.schoolName}</td>
                                            <td> {student.examsTaken}</td>
                                            <td> {student.averageScore}</td>
                                            <td><button onClick={ () => this.viewBook(student.id)} className="btn btn-info">View</button>
                                                 <button style={{marginLeft: "10px"}} onClick={ () => this.updateStudentInfo(student.id)} className="btn btn-info">Update</button>
                                                 <button style={{marginLeft: "10px"}} onClick={ () => this.updateStudentInfo(student.id)} className="btn btn-danger">Delete</button></td>
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
        text-align: center;
        justify-content: center;
        padding-top: 8px;
        font-family: "Arial Black", Gadget, sans-serif;
        font-size: 30px;
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
        border: 3px solid #16537e;
        margin: auto;
        width: 80% !important;
        border-collapse: collapse;
        min-width: 400px;
        border-radius: 5px 5px 0 0;
        overflow: hidden;
        box-shadow: 0 0 20px rgba(0, 0, 0, 0.15);
    }

    .student-table thead tr {
        background-color: #16537e;
        color: #ffffff;
        text-align: center;
        font-weight: bold;
        font-size: 20px;
    }

    .buttons {
        text-align: center;
        background-color: #ffffff;
    }

    .student-table tbody td {
        padding: 12px 15px;
        text-align: center;
        border: 1px solid #444444;
        background-color: #bcbcbc;
    }

`;
