import { Component } from 'react';
import PropTypes from 'prop-types';
import StudentService from '../services/StudentService'; // Import the service that provides student data
import TopBarComponent from './TopBardComponent';

class ListStudentComponent extends Component {
    constructor(props) {
        super(props)

        this.state = {
            students: [] // Initialize students state
        }
        this.addStudent = this.addStudent.bind(this);
    }

    static propTypes = {
        history: PropTypes.object.isRequired,
      };

    addStudent(){ // Add a student
        this.props.history.push('/create');
    }

    viewStudent(id){ // View a student
        this.props.history.push(`/${id}`);
    }

    updateStudent(id){ // Update a student
        alert("Updating a Student is still under construction...");
    }

    deleteStudent(id){ // Delete a student
        alert("Deleting a Student is still under construction...");
    }

    componentDidMount(){
        StudentService.getStudents().then((res) => { // Fetch student data when the component mounts
            this.setState({ students: res.data });
        });
    }

    render() {
        return (
            <div>
                <TopBarComponent />
                <h2 className="text-center">Students List</h2>
                <div className = "row">
                    <table className = "table table-striped table-bordered">

                        <thead>
                            <tr>
                                <th> Student RUT</th>
                                <th> School name</th>
                                <th> Exams taken</th>
                                <th> Average score</th>
                            </tr>
                        </thead>
                        <tbody>
                            {
                                this.state.students.map( // Map over the students state to create a table row for each student
                                    student => 
                                    <tr key = {student.id}>
                                         <td> { student.rut } </td>   
                                         <td> { student.schoolName }</td>
                                         <td> { student.averageScore }</td>
                                    </tr>
                                )
                            }
                        </tbody>
                    </table>

                </div>

            </div>
        )
    }
}

export default ListStudentComponent;