import { useState } from 'react';
import Form from 'react-bootstrap/Form';
import Button from 'react-bootstrap/Button';
import swal from 'sweetalert';
import styled, { createGlobalStyle } from 'styled-components';

// Components import
import HeaderComponent from './HeaderComponent';
// Services import
import StudentService from '../services/StudentService';

export default function CreateStudent() {
    const initialState = {
        rut: '99999999-K',
        firstName: 'Aquiles',
        lastName: 'Baeza',
        birthDate: '29-02-2000',
        schoolType: '0',
        schoolName: 'Test School',
        graduationYear: '2018',
        examsTaken: '0',
        averageScore: '0',
        paymentMethod: 'Cash',
        agreedInstallments: '1',
        installmentsPaid: '0',
        overdueInstallments: '0',
        lastPaymentDate: '',
        totalAmountToPay: '0',
        totalAmountPaid: '0',
    };

    // Initialize the state
    const [input, setInput] = useState(initialState);

    // Event handlers

    const changeRUTHandler = (event) => {
        setInput({ ...input, rut: event.target.value });
    };
    const changeFirstNameHandler = (event) => {
        setInput({ ...input, firstName: event.target.value });
    };
    const changeLastNameHandler = (event) => {
        setInput({ ...input, lastName: event.target.value });
    };
    const changeBirthDateHandler = (event) => {
        setInput({ ...input, birthDate: event.target.value });
    };
    const changeSchoolTypeHandler = (event) => {
        setInput({ ...input, schoolType: event.target.value });
    };
    const changeSchoolNameHandler = (event) => {
        setInput({ ...input, schoolName: event.target.value });
    };
    const changeGraduationYearHandler = (event) => {
        setInput({ ...input, graduationYear: event.target.value });
    };
    const changeAgreedInstallmentsHandler = (event) => {
        setInput({ ...input, agreedInstallments: event.target.value });
    };

    // Create a student

    const saveStudent = (e) => {
        e.preventDefault();
        swal({
            title: '¿Está seguro de que desea enviar este proveedor?',
            text: 'Una vez enviado, no podrá ser modificado.',
            icon: 'warning',
            buttons: ['Cancelar', 'Enviar'],
            dangerMode: true,
        }).then((respuesta) => {
            if (respuesta) {
                swal('Student correctly saved', {
                    icon: 'success',
                    timer: '3000',
                });
                const student = {
                    rut: input.rut,
                    firstName: input.firstName,
                    lastName: input.lastName,
                    birthDate: input.birthDate,
                    schoolType: input.schoolType,
                    schoolName: input.schoolName,
                    graduationYear: input.graduationYear,
                    examsTaken: input.examsTaken,
                    averageScore: input.averageScore,
                    paymentMethod: input.paymentMethod,
                    agreedInstallments: input.agreedInstallments,
                    installmentsPaid: input.installmentsPaid,
                    overdueInstallments: input.overdueInstallments,
                    lastPaymentDate: input.lastPaymentDate,
                    totalAmountToPay: input.totalAmountToPay,
                    totalAmountPaid: input.totalAmountPaid,
                };

                console.log(input.rut);
                console.log(input.firstName);
                console.log(input.lastName);
                console.log('student => ' + JSON.stringify(student));

                StudentService.saveStudent(student).then(() => {});
            } else {
                swal({ text: 'Student could not be registed', icon: 'error' });
            }
        });
    };

    return (
        <div>
            <GlobalStyle />
            <HomeStyle>
                <HeaderComponent />
                <div className="container-create">
                    <Form>
                        <Form.Group
                            className="mb-3"
                            controlId="rut"
                            value={input.rut}
                            onChange={changeRUTHandler}
                        >
                            <Form.Label for="rut">Código:</Form.Label>
                            <Form.Control
                                type="text"
                                name="rut"
                                placeholder="66666666-K"
                            />
                        </Form.Group>

                        <Form.Group
                            className="mb-3"
                            controlId="firstName"
                            value={input.firstName}
                            onChange={changeFirstNameHandler}
                        >
                            <Form.Label for="firstName">Nombre:</Form.Label>
                            <Form.Control
                                type="text"
                                name="firstName"
                                placeholder="Juan"
                            />
                        </Form.Group>

                        <Form.Group
                            className="mb-3"
                            controlId="lastName"
                            value={input.lastName}
                            onChange={changeLastNameHandler}
                        >
                            <Form.Label for="lastName">Apellido:</Form.Label>
                            <Form.Control
                                type="text"
                                name="lastName"
                                placeholder="Perez"
                            />
                        </Form.Group>

                        <Form.Group
                            className="mb-3"
                            controlId="birthDate"
                            value={input.birthDate}
                            onChange={changeBirthDateHandler}
                        >
                            <Form.Label for="birthDate">
                                Fecha de nacimiento:
                            </Form.Label>
                            <Form.Control
                                type="date"
                                name="birthDate"
                                placeholder="01/01/2000"
                            />
                        </Form.Group>

                        <Form.Group
                            className="mb-3"
                            controlId="schoolType"
                            value={input.schoolType}
                            onChange={changeSchoolTypeHandler}
                        >
                            <Form.Label for="schoolType">
                                Tipo de colegio:
                            </Form.Label>
                            <Form.Control
                                type="text"
                                name="schoolType"
                                placeholder="0 -> Municipal, 1 -> Subsidized, 2 -> Private"
                            />
                        </Form.Group>

                        <Form.Group
                            className="mb-3"
                            controlId="schoolName"
                            value={input.schoolName}
                            onChange={changeSchoolNameHandler}
                        >
                            <Form.Label for="schoolName">
                                Nombre del colegio:
                            </Form.Label>
                            <Form.Control
                                type="text"
                                name="schoolName"
                                placeholder="Colegio de Prueba"
                            />
                        </Form.Group>

                        <Form.Group
                            className="mb-3"
                            controlId="graduationYear"
                            value={input.graduationYear}
                            onChange={changeGraduationYearHandler}
                        >
                            <Form.Label for="graduationYear">
                                Año de egreso:
                            </Form.Label>
                            <Form.Control
                                type="text"
                                name="graduationYear"
                                placeholder="2018"
                            />
                        </Form.Group>

                        <Form.Group
                            className="mb-3"
                            controlId="agreedInstallments"
                            value={input.agreedInstallments}
                            onChange={changeAgreedInstallmentsHandler}
                        >
                            <Form.Label for="agreedInstallments">
                                Cantidad de cuotas:
                            </Form.Label>
                            <Form.Control
                                type="text"
                                name="agreedInstallments"
                                placeholder="1"
                            />
                        </Form.Group>
                    </Form>
                    <Button type="submit" onClick={saveStudent}>
                        Save Student
                    </Button>
                </div>
            </HomeStyle>
        </div>
    );
}

// Create GlobalStyle
const GlobalStyle = createGlobalStyle`
    body {
        background-color: #f5f5f5;
    }
`;
// Create HomeStyle
const HomeStyle = styled.div`

    .container-create {
        display: flex;
        flex-direction: column;
        justify-content: center;
        align-items: center;
        margin: 20px;
    }

    .Form {
        display: flex;
        flex-direction: column;
        justify-content: center;
        align-items: center;
        margin: 20px;
    }

    .Form.Group {
        display: flex;
        flex-direction: column;
        justify-content: center;
        align-items: center;
        margin: 20px;
    }

    .menu-text {
        display: flex;
        justify-content: center;
        align-items: center;
        margin: 20px;
    }
`;
