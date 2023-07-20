import React from 'react';
import Container from '@mui/material/Container';
import Typography from '@mui/material/Typography';
import Box from '@mui/material/Box';

export default function MainContent(){
  return (
    <Container maxWidth="md">
      <Box mt={3}>
        <Typography variant="h4" component="h1" gutterBottom>
          Welcome to Project Report 2.0!
        </Typography>
        <Typography variant="body1" gutterBottom>
          Some text here 
        </Typography>
      </Box>
    </Container>
  );
};
