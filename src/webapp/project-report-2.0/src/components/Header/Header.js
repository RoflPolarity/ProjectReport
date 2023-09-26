import React, { useState } from 'react';
import AppBar from '@mui/material/AppBar';
import Toolbar from '@mui/material/Toolbar';
import Typography from '@mui/material/Typography';
import Button from '@mui/material/Button';
import Menu from '@mui/material/Menu';
import MenuItem from '@mui/material/MenuItem';
import { Link } from 'react-router-dom';
import { useNavigate } from 'react-router-dom';

const linkStyle = {
  textDecoration: 'none',
  color: 'inherit',
  cursor: 'pointer',
};

export default function Header({ isLoggedIn }) {
  const [anchorEl, setAnchorEl] = useState(null);
  const navigate = useNavigate();

  const handleMenuClick = (event) => {
    setAnchorEl(event.currentTarget);
  };

  const handleMenuClose = () => {
    setAnchorEl(null);
  };

  const handleLogout = () => {
    localStorage.clear();
    localStorage.setItem('username', ''); // Обновите имя и фамилию на пустую строку
    handleMenuClose();
    navigate('/login');
  };

  return (
    <AppBar position="static">
      <Toolbar style={{ display: 'flex', justifyContent: 'space-between', alignItems: 'center' }}>
        <div style={{ display: 'flex', alignItems: 'center' }}>
          <Typography variant="h6" sx={{ marginRight: '20px' }}>
            <Link to="/" style={linkStyle}>
              Project Report 2.0
            </Link>
          </Typography>
          <Link to="/ControlPanel" style={linkStyle}>
            <Button color="inherit">Проекты</Button>
          </Link>
        </div>
        {isLoggedIn ? (
          <div>
            <Button
              color="inherit"
              aria-controls="user-menu"
              aria-haspopup="true"
              onClick={handleMenuClick}
            >
              {localStorage.getItem('username')}
            </Button>
            <Menu
              id="user-menu"
              anchorEl={anchorEl}
              open={Boolean(anchorEl)}
              onClose={handleMenuClose}
            >
              <MenuItem onClick={handleLogout}>Log out</MenuItem>
            </Menu>
          </div>
        ) : (
          <Button color="inherit" component={Link} to="/login">
            Log in
          </Button>
        )}
      </Toolbar>
    </AppBar>
  );
};
