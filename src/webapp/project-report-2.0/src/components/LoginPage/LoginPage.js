import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import Container from '@mui/material/Container';
import Typography from '@mui/material/Typography';
import TextField from '@mui/material/TextField';
import Button from '@mui/material/Button';
import Box from '@mui/material/Box';


const LoginPage = ({onLoginSuccess}) => {
  const [username, setUsername] = useState('');
  const [password, setPassword] = useState('');
  const navigate = useNavigate();
  const navigateToControlPanel=()=>{navigate("/ControlPanel")}
  const handleFormSubmit = async (event) => {
    event.preventDefault();
    const userData = {
      login: username,
      password: password,
    };

    try {
          const response = await fetch('http://77.50.236.202:48225/api/login', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify(userData),
    });
    
      const responseBody = await response.json();
      
      if (responseBody.status === true) {
        localStorage.setItem('token', responseBody.user.token);
        onLoginSuccess(responseBody.user);
        switch (responseBody.user.role.name){
          case 'ADMIN':
            navigateToControlPanel();
            break;
          case 'PM':
            navigateToControlPanel();
            break;
              case 'TM':
              navigateToControlPanel();
              break;
          default:
            navigate('/')
        }

      } else {
        console.error('Ошибка при входе');
      }
    } catch (error) {
      console.error('Ошибка при выполнении запроса:', error);
    }
  };

  return (
    <Container maxWidth="sm">
      <Box mt={5}>
        <Typography variant="h4" align="center" gutterBottom>
          Login to Project Report 2.0
        </Typography>
        <form onSubmit={handleFormSubmit}>
          <Box mt={3}>
            <TextField
              label="Login"
              variant="outlined"
              fullWidth
              value={username}
              onChange={(e) => setUsername(e.target.value)}
            />
          </Box>
          <Box mt={2}>
            <TextField
              type="password"
              label="Password"
              variant="outlined"
              fullWidth
              value={password}
              onChange={(e) => setPassword(e.target.value)}
            />
          </Box>
          <Box mt={2} display="flex" justifyContent="center">
            <Button variant="contained" color="primary" type="submit">
              Log in
            </Button>
          </Box>
        </form>
      </Box>
    </Container>
  );
};

export default LoginPage;
