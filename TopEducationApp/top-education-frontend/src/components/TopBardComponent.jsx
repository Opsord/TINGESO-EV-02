/* eslint-disable import/no-duplicates */
import styled from "styled-components";
import GraduationIcon from "../images/graduation-512.png";
import { Toolbar } from '@material-ui/core';
import { AppBar } from '@material-ui/core';

function TopBarComponent(){
    return(
        <>
        <AppBar position="relative">
        <Toolbar>
          <img src={GraduationIcon} width="50px" height="50px" />
        </Toolbar>
      </AppBar>
        <NavStyle>
            <header className="header">
                <div className="logo">
                    <h1>Top Education</h1>
                </div>
                <nav>
                </nav>
                <a className="btn" href="/"><button>Back to main menu</button></a>
            </header>
            </NavStyle>
        </>
    )
}

export default TopBarComponent;


const NavStyle = styled.nav`
.header{
    background-color: #1b3039;
    display: flex;
    justify-content: flex-end;
    align-items: center;
    height: 85px;
    padding: 5px 10%;
  }
.header .logo{
    margin-right: auto;
    color: white;
    font-family: 'Pacifico',serif;
  }
.header .btn button{
    margin-left: 20px;
    font-weight: 700;
    color: #1b3039;
    padding: 9px 25px;
    background: #eceff1;
    border: none;
    border-radius: 50px;
    cursor: pointer;
    transition: all 0.3s ease 0s;
  }
.header .btn button:hover{
    background-color: #e2f1f8;
    color: #ffbc0e;
    transform: scale(1.1);
  }
`