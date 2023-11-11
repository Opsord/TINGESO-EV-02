import { useEffect, useState } from 'react';
import styled, { createGlobalStyle } from 'styled-components';
import { useParams } from 'react-router-dom'; // Importa el hook useParams para obtener el rut de la URL
import HeaderComponent from './HeaderComponent';
// CSS import
import '../css/TableFormat.css';
// Services import
import StudentService from '../services/StudentService';
import InstallmentService from '../services/InstallmentService';
import AdminOfficeService from '../services/AdminOfficeService';
import ScoreService from '../services/ScoreService';

export default function Details() {
    // Declare the state variable and the function to update it
    const { rut } = useParams();
    // Initialize the student state
    const [student, setStudent] = useState(null);
    // Initialize the installments state
    const [installments, setInstallments] = useState([]);
    // Initialize the scores state
    const [scores, setScores] = useState([]);

    // Update a student info
    const updateStudent = (rut) => {
        AdminOfficeService.updateStudentInfo(rut).then(() => {
            // After updating, refresh the details
            refreshStudentDetails();
        });
    };

    // Refresh the student details
    const refreshStudentDetails = () => {
        StudentService.getStudentByRut(rut).then((res) => {
            setStudent(res.data);
        });
    };

    // Mark an installment as paid
    const markAsPaid = (installmentId) => {
        console.log(installmentId);
        InstallmentService.markInstallmentAsPaid(installmentId).then(() => {
            // After marking as paid, refresh the table
            refreshInstallmentsTable();
        });
    };

    // Refresh the table
    const refreshInstallmentsTable = () => {
        InstallmentService.getInstallmentsByRUT(rut).then((res) => {
            setInstallments(res.data);
        });
    };

    useEffect(() => {
        // Load student details when the component mounts
        StudentService.getStudentByRut(rut)
            .then((response) => setStudent(response.data))
            .catch((error) =>
                console.error('Error fetching student details:', error)
            );
    }, [rut]);

    useEffect(() => {
        // Load installments when the component mounts
        InstallmentService.getInstallmentsByRUT(rut)
            .then((response) => setInstallments(response.data))
            .catch((error) =>
                console.error('Error fetching installments:', error)
            );
    }, [rut]);

    useEffect(() => {
        // Load scores when the component mounts
        ScoreService.getScoresByRUT(rut)
            .then((response) => setScores(response.data))
            .catch((error) => console.error('Error fetching scores:', error));
    }, [rut]);

    return (
        <div>
            <HeaderComponent />
            <GlobalStyle />
            <HomeStyle>
                <h1 className="menu-text">
                    <b>Student details</b>
                </h1>
                <div className="student-details-box">
                    {student ? (
                        <div className="StudentDetails">
                            <div className="info-item">
                                <span className="label">RUT:</span>
                                <span className="value">{student.rut}</span>
                            </div>

                            <div className="info-item">
                                <span className="label">Name:</span>
                                <span className="value">{`${student.firstName} ${student.lastName}`}</span>
                            </div>

                            <div className="info-item">
                                <span className="label">Birthdate:</span>
                                <span className="value">
                                    {student.birthDate}
                                </span>
                            </div>

                            <div className="info-item">
                                <span className="label">School Type:</span>
                                <span className="value">
                                    {student.schoolType}
                                </span>
                            </div>

                            <div className="info-item">
                                <span className="label">School Name:</span>
                                <span className="value">
                                    {student.schoolName}
                                </span>
                            </div>

                            <div className="info-item">
                                <span className="label">Graduation Year:</span>
                                <span className="value">
                                    {student.graduationYear}
                                </span>
                            </div>

                            <div className="info-item">
                                <span className="label">Exams Taken:</span>
                                <span className="value">
                                    {student.examsTaken}
                                </span>
                            </div>

                            <div className="info-item">
                                <span className="label">Average Score:</span>
                                <span className="value">
                                    {student.averageScore}
                                </span>
                            </div>
                            <div className="info-item">
                                <span className="label">Payment Method:</span>
                                <span className="value">
                                    {student.paymentMethod}
                                </span>
                            </div>

                            <div className="info-item">
                                <span className="label">
                                    Agreed Installments:
                                </span>
                                <span className="value">
                                    {student.agreedInstallments}
                                </span>
                            </div>

                            <div className="info-item">
                                <span className="label">
                                    Installments Paid:
                                </span>
                                <span className="value">
                                    {student.installmentsPaid}
                                </span>
                            </div>
                            
                            <div className="info-item">
                                <span className="label">
                                    Overdue Installments:
                                </span>
                                <span className="value">
                                    {student.overdueInstallments}
                                </span>
                            </div>

                            <div className="info-item">
                                <span className="label">
                                    Last Payment Date:
                                </span>
                                <span className="value">
                                    {student.lastPaymentDate}
                                </span>
                            </div>

                            <div className="info-item">
                                <span className="label">
                                    Total Amount to Pay:
                                </span>
                                <span className="value">
                                    {student.totalAmountToPay}
                                </span>
                            </div>

                            <div className="info-item">
                                <span className="label">
                                    Total Amount Paid:
                                </span>
                                <span className="value">
                                    {student.totalAmountPaid}
                                </span>
                            </div>
                            
                        </div>
                        
                    ) : (
                        <p>Loading...</p>
                    )}
                    <button
                        className="input-plan-boton"
                        onClick={() => {
                            updateStudent(rut);
                        }}
                    >
                        Update
                    </button>
                </div>
                
                <h3 className="menu-text">
                    <b>Scores</b>
                </h3>
                <div className="student-scores-box">
                        <table className="content-table">
                            <thead>
                                <tr>
                                    <th> Exam date</th>
                                    <th> Score</th>
                                </tr>
                            </thead>
                            <tbody>
                                {scores.map((score) => (
                                    <tr key={score.id}>
                                        <td>{score.examDate}</td>
                                        <td>{score.score}</td>
                                    </tr>
                                ))}
                            </tbody>
                        </table>
                </div>

                <h3 className="menu-text">
                    <b>Installments</b>
                </h3>
                <table className="content-table">
                    <thead>
                        <tr>
                            <th> Amount</th>
                            <th> Status</th>
                            <th> Payment date</th>
                            <th> Due date</th>
                            <th> Overdue Status</th>
                            <th> Overdue penalty</th>
                            <th> Actions</th>
                        </tr>
                    </thead>
                    <tbody>
                        {installments.map((installment) => (
                            <tr key={installment.id}>
                                <td>{installment.installmentAmount}</td>
                                <td>{installment.installmentStatus}</td>
                                <td>{installment.installmentPaymentDate}</td>
                                <td>{installment.installmentDueDate}</td>
                                <td>{installment.installmentOverdueStatus}</td>
                                <td>{installment.installmentOverduePenalty}</td>
                                <td>
                                    <button
                                        className="input-plan-boton"
                                        onClick={() =>
                                            markAsPaid(installment.id)
                                        }
                                    >
                                        Mark as paid
                                    </button>
                                </td>
                            </tr>
                        ))}
                    </tbody>
                </table>
            </HomeStyle>
        </div>
    );
}

const GlobalStyle = createGlobalStyle`
    body {
        background-color: #ffffff;
    }
`;

const HomeStyle = styled.div`
    .menu-text {
        justify-content: center;
        text-align: center;
        color: #000000;
    }

    .student-details-box {
        display: flex;
        justify-content: center;
        border: 3px solid #000000;
        border-radius: 10px;
        background-color: #bcbcbc;
        margin-left: 20%;
        margin-right: 20%;
        height: 300px;
    }

    .StudentDetails {
        display: grid;
        grid-template-columns: 1fr 1fr 1fr;
        margin: 2%;
        align-items: center;
        justify-content: center;
        gap: 25px;
        margin: auto;
        color: #00000;
        letter-spacing: -1px;
        font-size: 17px;
    }

    .info-item {
        display: flex;
        align-items: center;
    }

    .label {
        font-weight: bold;
        margin-right: 5px;
    }

    .value {
        font-size: 16px;
    }
`;
