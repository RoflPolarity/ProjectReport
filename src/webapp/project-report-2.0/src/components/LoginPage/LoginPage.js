import React, { useState } from 'react';
import Container from '@mui/material/Container';
import Typography from '@mui/material/Typography';
import TextField from '@mui/material/TextField';
import Button from '@mui/material/Button';
import Box from '@mui/material/Box';

const LoginPage = () => {
  const [username, setUsername] = useState('');
  const [password, setPassword] = useState('');

  const handleFormSubmit = async (event) => {
    event.preventDefault();

    // Создаем объект с данными пользователя для отправки на сервер
    const userData = {
      username: username,
      password: password,
    };

    try {
      // Отправляем POST-запрос на сервер
      const response = await fetch('ваш_сервер/api/login', {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
        },
        body: JSON.stringify(userData),
      });

      if (response.ok) {
        // Вход успешный, выполняем действия после успешной авторизации
        console.log('Успешный вход!');
      } else {
        // Вход неуспешный, обрабатываем ошибку
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
