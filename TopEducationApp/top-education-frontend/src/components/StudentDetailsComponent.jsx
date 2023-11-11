import { useEffect, useState } from 'react';
import styled, { createGlobalStyle } from 'styled-components';
import { useParams } from 'react-router-dom'; // Importa el hook useParams para obtener el rut de la URL
import HeaderComponent from './HeaderComponent';
import StudentService from '../services/StudentService';

export default function Details() {
    const { rut } = useParams(); // Obtiene el rut de la URL
    const [student, setStudent] = useState(null);

    useEffect(() => {
        // Cargar los detalles del estudiante cuando el componente se monta
        StudentService.getStudentByRut(rut)
            .then((response) => setStudent(response.data))
            .catch((error) => console.error('Error fetching student details:', error));
    }, [rut]); // Dependencia rut para recargar cuando cambia

    return (
        <div>
            <HeaderComponent />
            <GlobalStyle />
            <HomeStyle>
                <h1 className="Menu-text">
                    <b>Top Education Menu</b>
                </h1>
                {student ? (
                    <div className="StudentDetails">
                        <h2>Student Details</h2>
                        <p>RUT: {student.rut}</p>
                        <p>School Name: {student.schoolName}</p>
                        {/* Otros detalles del estudiante */}
                    </div>
                ) : (
                    <p>Loading...</p>
                )}
            </HomeStyle>
        </div>
    );
}

const GlobalStyle = createGlobalStyle`
    body {
        background-color: #262626;
    }
`;

const HomeStyle = styled.div`
    .StudentDetails {
        display: flex;
        flex-direction: column;
        align-items: center;
        gap: 10px;
        margin: 20px;
        color: #ffffff;
    }
`;
