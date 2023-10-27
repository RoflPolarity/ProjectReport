import React from 'react';
import Card from '@mui/material/Card';
import CardContent from '@mui/material/CardContent';
import Typography from '@mui/material/Typography';
import { Link } from 'react-router-dom';

const ProjectInfoCard = ({ project}) => {

  const buttonStyle = {
    background: '#4caf50',
    border: 'none',
    color: 'white',
    padding: '10px 20px',
    fontSize: '16px', 
    cursor: 'pointer',
    borderRadius: '30px',
    margin: '10px 0',
  };

  return (
    <Card style={{ width: '300px', marginRight: '16px' }}>
      <CardContent>
        <Typography variant="h6" gutterBottom>
          {project.name}
        </Typography>
        <Typography>Номер: {project.projectNumber}</Typography>
        <Typography>Дата старта: {project.dateStart}</Typography>
        <Typography>Дата окончания: {project.dateEnd}</Typography>
        <Typography>ПМ: {project.pm.name} {project.pm.surname}</Typography>
        <Typography>ТМ: {project.tm.name} {project.tm.surname}</Typography>
        <Link to={`/report/${project.projectNumber}`} style={{ textDecoration: 'none' }}>
        <button style={buttonStyle}>
          Сформировать отчет "{project.name}.pdf"
        </button>
        {console.log(project)}
        </Link>
        <Link
        
          to={{
            pathname: `/edit/${project.projectNumber}`,
            state: { project: project },
          }}
          style={{ textDecoration: 'none' }}>
          <button style={buttonStyle}>Редактировать проект</button>
        </Link>
      </CardContent>
    </Card>
  );
};

export default ProjectInfoCard;
