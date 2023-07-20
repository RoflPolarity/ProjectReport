import React from 'react';
import Box from '@mui/material/Box';
import Typography from '@mui/material/Typography';

const Footer = () => {
  return (
    <Box mt={2} p={2} bgcolor="primary.main" color="white">
      <Typography variant="body2" align="center">
        &copy; {new Date().getFullYear()} Project Report 2.0. All rights reserved.
      </Typography>
    </Box>
  );
};

export default Footer;
