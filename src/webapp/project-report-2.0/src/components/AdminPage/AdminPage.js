import React, { useEffect, useState } from 'react';
import Container from '@mui/material/Container';
import Typography from '@mui/material/Typography';
import Button from '@mui/material/Button';
import { Link } from 'react-router-dom';
import Paper from '@mui/material/Paper';

const AdminPage = () => {
  const [projects, setProjects] = useState([]);

  // Функция для получения списка проектов с сервера
  const fetchProjects = async () => {
    try {
      const response = await fetch('http://77.50.236.202:48225/api/GetAllProjects', {
        method: 'GET',
        headers: {
          Authorization: `Bearer ${localStorage.getItem('token')}`,
        },
      });
      const data = await response.json();
      setProjects(data);
    } catch (error) {
      console.error('Ошибка при получении списка проектов:', error);
    }
  };

  useEffect(() => {
    fetchProjects();
  }, []);

  return (
    <Container maxWidth="sm">
      <Typography variant="h4" align="center" gutterBottom style={{ 
                fontFamily: 'Roboto Slab, serif',
                fontSize: '40px',
                color: 'black',
                marginTop: '10px',
              }}>
        Список проектов
      </Typography>
      {projects && projects.length > 0 ? (
        projects.map((project) => (
          <Link to={`/project/${project.projectNumber}`} style={{ textDecoration: 'none', width: '100%' }}>
            <Paper
              key={project.projectNumber}
              elevation={3}
              style={{
                padding: '16px',
                marginBottom: '16px',
                transition: 'transform 0.2s ease-in-out',
              }}
              onMouseOver={(e) => {
                e.currentTarget.style.transform = 'scale(1.025)';
              }}
              onMouseOut={(e) => {
                e.currentTarget.style.transform = 'scale(1)';
              }}
            >
              <Typography variant="h6" style={{ 
                fontFamily: 'Roboto Slab, serif',
                fontSize: '25px',
                color: 'black',
              }}>{project.name}</Typography>
              <Typography variant="body2" style={{ 
                fontFamily: 'Roboto Slab, serif',
                fontSize: '16px',
                color: 'black',
              }}>Дата начала: {project.dateStart}</Typography>
              <Typography variant="body2" style={{ 
                fontFamily: 'Roboto Slab, serif',
                fontSize: '16px',
                color: 'black',
              }}>Дата окончания: {project.dateEnd}</Typography>
              <Typography variant="body2" style={{ 
                fontFamily: 'Roboto Slab, serif',
                fontSize: '15px',
                color: 'black',
              }}>Статус: {project.status}</Typography>
              <Typography variant="body2" style={{ 
                fontFamily: 'Roboto Slab, serif',
                fontSize: '15px',
                color: 'black',
              }}>Направление: {project.direction.name}</Typography>
              <div>
              {project.pms.map(pm => (
                <div key={pm.id}>
                  <Typography variant='body2' style={{
                    fontFamily: 'Roboto Slab, serif',
                    fontSize: '15px',
                    color: 'black'
                  }}>
                  ПМ: {pm.name} {pm.surname}
                  </Typography>
                </div>
              ))}
            </div>
            <div>
              {project.tms.map(tm => (
                <div key={tm.id}>
                  <Typography variant='body2' style={{
                    fontFamily: 'Roboto Slab, serif',
                    fontSize: '15px',
                    color: 'black'
                  }}>ТМ: {tm.name} {tm.surname}
                  </Typography>
                </div>
              ))}
            </div>
            </Paper>
          </Link>
        ))
      ) : (
        <p>No projects found</p>
      )}
      
      <div style={{ display: 'flex', justifyContent: 'center', marginTop: '16px' }}>
        <Link to="/AddProject">
          <Button variant="contained" color="primary" startIcon={<span>+</span>}>
            Добавить
          </Button>
        </Link>
      </div>
    </Container>
  );
};

export default AdminPage;
