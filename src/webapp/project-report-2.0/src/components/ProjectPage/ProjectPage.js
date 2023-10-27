import React, { useState, useEffect } from 'react';
import ProjectInfoCard from './ProjectInfoCard';
import ReportsAccordion from './ReportsAccordion';
import AverageMetricsAccordion from './AverageMetricsAccordion';
import { useParams } from 'react-router-dom';

import axios from 'axios';

const ProjectPage = () => {
  const{projectNumber} = useParams();
  const [reports, setReports] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);
  const [project, setProject] = useState()

  useEffect(() => {
    // Fetch reports from the server
    const fetchReports = async () => {
      try {
        const response = await axios.get(`http://77.50.236.202:48225/api/getReports/${projectNumber}`,
        {headers: {
          Accept: 'application/json',
          Authorization: `Bearer ${localStorage.getItem('token')}`,
        },});
        
        if (response.status === 200) {
          setReports(response.data);
        } else if (response.status === 204) {
          setReports([]);
        }
  
        setLoading(false);
      } catch (error) {
        setError(error);
        setLoading(false);
      }
    };

    const fetchProject = async () => {
      try {
        const response = await axios.get(`http://77.50.236.202:48225/api/getProject/${projectNumber}`,
        {headers: {
          Accept: 'application/json',
          Authorization: `Bearer ${localStorage.getItem('token')}`,
        },});
        
        if (response.status === 200) {
          setProject(response.data);
        } else if (response.status === 204) {
          setReports([]);
        }
  
        setLoading(false);
      } catch (error) {
        setError(error);
        setLoading(false);
      }
    };
    fetchProject();
    fetchReports();
  }, [projectNumber]);

  if (loading) {
    return <p>Loading...</p>;
  }

  if (error) {
    return <p>Error: {error.message}</p>;
  }
  return (
    <div style={{ display: 'flex', margin: '20px' }}>
  <div style={{ flex: '0 0 auto' }}>
    {project && <ProjectInfoCard project={project} />}
  </div>
  <div style={{ marginLeft: '20px', flex: '1' }}>
    <ReportsAccordion reports={reports} project_id={projectNumber}/>
    <AverageMetricsAccordion reports={reports} />
  </div>
</div>

  );
};

export default ProjectPage;
