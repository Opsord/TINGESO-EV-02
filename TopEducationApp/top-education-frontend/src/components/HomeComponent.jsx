/* eslint-disable import/no-duplicates */
import styled, { createGlobalStyle } from 'styled-components';

import Card from '@mui/material/Card';
import CardContent from '@mui/material/CardContent';
import CardMedia from '@mui/material/CardMedia';
import Typography from '@mui/material/Typography';
import { Button, CardActionArea, CardActions } from '@mui/material';
import HeaderComponent from './HeaderComponent';

// Images import
import FileImage from '../images/excel-database-512.png';
import StudentIcon from '../images/graduated-icon.png';

export function FileUploadCard() {
  return (
    <Card sx={{ height:"450px", width:"300px", margin:"15px" }}>
      <CardActionArea>
        <CardMedia
          component="img"
          image={FileImage}
          alt="file upload"
          sx={{ objectFit: 'cover' }}
        />
        <CardContent>
          <Typography gutterBottom variant="h5" component="div">
            Upload excel file
          </Typography>
          <Typography variant="body2" color="text.secondary">
            Upload an excel file of students or scores.
          </Typography>
        </CardContent>
      </CardActionArea>
      <CardActions sx={{ display:"flex", justifyContent:"center", flexGrow: 1, alignItems: 'flex-end' }}>
        <Button size="small" color="primary" href= "/uploadStudents">
          Upload Students
        </Button>
        <Button size="small" color="primary" href= "/uploadScores">
          Upload Scores
        </Button>
      </CardActions>
    </Card>
  );
}

export function StudentCard() {
  return (
    <Card sx={{ height:"450px", width:"300px", margin:"15px" }}>
      <CardActionArea>
        <CardMedia
          component="img"
          image={StudentIcon}
          alt="student card"
          sx={{ objectFit: 'cover' }}
        />
        <CardContent>
          <Typography gutterBottom variant="h5" component="div">
            Students
          </Typography>
          <Typography variant="body2" color="text.secondary">
            View, add, update, or delete students.
          </Typography>
        </CardContent>
      </CardActionArea>

      <CardActions sx={{ display:"flex", justifyContent:"center" }}>
        <Button size="small" color="primary" href= '/students'>
          View Students
        </Button>
        <Button size="small" color="primary" href= "/create">
          Add Student
        </Button>
      </CardActions>

    </Card>
  );
}

export default function Home() {

  return (
    <div>
    <GlobalStyle />  
    <HomeStyle>

      <HeaderComponent />
      
      <h1 className="menu-text" > <b>Top Education Menu</b></h1>

      <div className = "Option-cards">
      <FileUploadCard />
      <StudentCard />
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

.Option-cards {
  display: flex;
  flex-direction: row;
  justify-content: center;
  align-items: center;
  gap: 20px;
  margin: 20px;
}

.menu-text {
  display: flex;
  justify-content: center;
  align-items: center;
  margin: 20px;
}
`;

