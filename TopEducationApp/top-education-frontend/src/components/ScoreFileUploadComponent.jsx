import { Component } from 'react';
import { createGlobalStyle } from 'styled-components';
import Button from 'react-bootstrap/Button';
import Form from 'react-bootstrap/Form';
import Row from 'react-bootstrap/Row';
import Col from 'react-bootstrap/Col';
import swal from 'sweetalert';
// Services import
import FileManagerService from '../services/FileManagerService';
// Components import
import HeaderComponent from './HeaderComponent';

class ScoreFileUpload extends Component {
    constructor(props) {
        super(props);
        this.state = {
            file: null,
        };
        this.onFileChange = this.onFileChange.bind(this);
    }

    onFileChange(event) {
        this.setState({ file: event.target.files[0] });
    }

    onFileUpload = () => {
        swal({
            title: '¿Are you sure?',
            text: 'Be sure that this is a score database .xcs file!',
            icon: 'warning',
            buttons: ['Cancel', 'Upload'],
            dangerMode: true,
        }).then((respuesta) => {
            if (respuesta) {
                swal('File uploaded correctly!', {
                    icon: 'success',
                    timer: '3000',
                });
                const formData = new FormData();
                formData.append('file', this.state.file);
                FileManagerService.uploadScoresFile(formData).then(() => {});
            } else {
                swal({ text: 'Upload failed', icon: 'error' });
            }
        });
    };

    render() {
        return (
            <div>
                <HeaderComponent />
                <GlobalStyle />
                <div className="main-div">
                    <div className="container-excel">
                        <h1>
                            <b>Upload score database</b>
                        </h1>
                        <div className="file-upload">
                            <Row className="mt-4">
                                <Col col="12">
                                    <Form.Group class="" controlId="formFileLg">
                                        <Form.Control
                                            type="file"
                                            size="lg"
                                            onChange={this.onFileChange}
                                        />
                                    </Form.Group>
                                    <Button
                                        className="boton-excel"
                                        onClick={this.onFileUpload}
                                    >
                                        Upload file
                                    </Button>
                                </Col>
                            </Row>
                        </div>
                    </div>
                </div>
            </div>
        );
    }
}
export default ScoreFileUpload;

const GlobalStyle = createGlobalStyle`
    .main-div {
        background-color: #F5F5F5;
        height: 100vh;
        width: 100%;
        display: flex;
        justify-content: center;
        align-items: center;
    }
    .container-excel {
        background-color: #FFFFFF;
        height: 70vh;
        width: 70vw;
        border-radius: 20px;
        display: flex;
        justify-content: center;
        align-items: center;
        flex-direction: column;
    }
    .file-upload {
        display: flex;
        justify-content: center;
        align-items: center;
        flex-direction: column;
    }
    .boton-excel {
        background-color: #2D9CDB;
        border: none;
        border-radius: 10px;
        color: white;
        font-size: 20px;
        font-weight: bold;
        padding: 10px 20px;
        margin-top: 20px;
    }
    .boton-excel:hover {
        background-color: #2D9CDB;
        color: white;
        text-decoration: none;
    }
    .boton-excel:focus {
        background-color: #2D9CDB;
        color: white;
        text-decoration: none;
    }
    .boton-excel:active {
        background-color: #2D9CDB;
        color: white;
        text-decoration: none;
    }
    .boton-excel:visited {
        background-color: #2D9CDB;
        color: white;
        text-decoration: none;
    }
`;
