/* eslint-disable import/no-duplicates */
import styled, { createGlobalStyle } from 'styled-components';

import Card from '@mui/material/Card';
import CardContent from '@mui/material/CardContent';
import CardMedia from '@mui/material/CardMedia';
import Typography from '@mui/material/Typography';
import { Toolbar } from '@material-ui/core';
import { AppBar } from '@material-ui/core';
import { Button, CardActionArea, CardActions } from '@mui/material';

// Css import
import '../css/Home.css';

// Images import
import FileImage from '../images/excel-database-512.png';
import StudentIcon from '../images/graduated-icon.png';
import ScoreIcon from '../images/exam-icon.png';
import GraduationIcon from "../images/graduation-512.png";

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
        <Button size="small" color="primary">
          Upload Students
        </Button>
        <Button size="small" color="primary">
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
        <Button size="small" color="primary" href='/students'>
          View Students
        </Button>
        <Button size="small" color="primary">
          Add Student
        </Button>
      </CardActions>
    </Card>
  );
}

export function ScoreCard() {
  return (
    <Card sx={{ height:"450px", width:"300px", margin:"15px" }}>
      <CardActionArea>
        <CardMedia
          component="img"
          image={ScoreIcon}
          alt="score card"
          sx={{ objectFit: 'cover' }}
        />
        <CardContent>
          <Typography gutterBottom variant="h5" component="div">
            Scores
          </Typography>
          <Typography variant="body2" color="text.secondary">
            View, add, or delete scores.
          </Typography>
        </CardContent>
      </CardActionArea>
      <CardActions sx={{ display:"flex", justifyContent:"center", }}>
        <Button size="small" color="primary">
          View Scores
        </Button>
        <Button size="small" color="primary">
          Add Score
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

      <AppBar position="relative">
        <Toolbar>
          <img src={GraduationIcon} width="50px" height="50px" />
        </Toolbar>
      </AppBar>
      
      <h1 className="Menu-text" > <b>Top Education Menu</b></h1>

      <div className = "Option-cards">
      <FileUploadCard />
      <StudentCard />
      <ScoreCard />
      </div>

    </HomeStyle>
    </div>
  );
}

// Create GlobalStyle
const GlobalStyle = createGlobalStyle`
  body {
    background-color: #262626;
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
`;

