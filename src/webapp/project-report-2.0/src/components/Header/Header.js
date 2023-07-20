import React from 'react';
import AppBar from '@mui/material/AppBar';
import Toolbar from '@mui/material/Toolbar';
import Typography from '@mui/material/Typography';
import Button from '@mui/material/Button';
import { Link } from 'react-router-dom';

const linkStyle = {
  textDecoration: 'none',
  color: 'inherit',
  cursor: 'pointer',
};

export default function Header() {
  return (
    <AppBar position="static">
      <Toolbar>
        <Typography variant="h6" sx={{ flexGrow: 1 }}>
          <Link to="/" style={linkStyle}>
            Project Report 2.0
          </Link>
        </Typography>
        <Button color="inherit" component={Link} to="/login">
          Log in
        </Button>
      </Toolbar>
    </AppBar>
  );
};
