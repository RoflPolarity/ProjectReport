import React, { useState, useEffect } from 'react';
import { useParams } from 'react-router-dom';
import './ReportPage.css';
import axios from 'axios';
import ChartAccordionDetails from '../ChartAccordionDetails';
import html2pdf from 'html2pdf.js';
import ReportCard from './ReportCard';
import Report from '../ProjectPage/Report';

const ReportPage = () => {
  const { projectNumber } = useParams();
  const today = new Date();
  const formattedDate = today.toLocaleDateString('ru', { year: 'numeric', month: 'long', day: 'numeric' });

  const [projectData, setProjectData] = useState(null);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);
  const [reports, setReports] = useState([]);

  useEffect(() => {
    const fetchProject = async () => {
      try {
        const response = await fetch(`http://77.50.236.202:48225/api/getProject/${projectNumber}`, {
          headers: {
            Accept: 'application/json',
            Authorization: `Bearer ${localStorage.getItem('token')}`,
          }
        });

        if (response.ok) {
          const data = await response.json();
          setProjectData(data);
        } else {
          setError('Ошибка при загрузке проекта');
        }

        setLoading(false);
      } catch (error) {
        setError('Ошибка при загрузке проекта');
        setLoading(false);
      }
    };
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

    fetchReports();
    fetchProject();
  }, [projectNumber]);

  if (loading) {
    return <div>Loading...</div>;
  }

  if (error) {
    return <div>Error: {error}</div>;
  }
  const formattedStartDate = new Date(projectData.dateStart).toLocaleDateString('ru', { year: 'numeric', month: 'long', day: 'numeric' });

  const generatePDF = () => {
    const element = document.getElementById('report-container');
    html2pdf(element);
  }

  return (
  <div>
    <div className="parent-container">
    <button onClick={generatePDF} className= "buttonDown">Скачать сформированный отчет</button>
    </div>
    <div className="report-page" id='report-page'>
      <div className="report-container" id ="report-container">
        <h1 style={{ fontSize: '20px', textAlign: 'center' }}>Отчет к Акту сдачи-приемки услуг от {formattedDate}</h1>
        <h2 style={{ fontSize: '20px', textAlign: 'center' }}>по проекту {projectData.name}</h2>
        <div className="project-info">
          <div>
            <h4>ПМ проекта: {projectData.pm.name} {projectData.pm.surname}</h4>
            <h4>ТМ проекта: {projectData.tm.name} {projectData.tm.surname}</h4>
            <h4>По проведенной работе в период с {formattedStartDate} по {formattedDate}</h4>
          </div>
        </div>
        <div>
          <h5 style={{fontSize:'25px'}}>Текущие показатели</h5>
          <ChartAccordionDetails reports={reports} />
        </div>
        <div className='cards'>
          {reports.map((report, index) => (
            <ReportCard key={index} report={report} />
          ))}
        </div>
      </div>
    </div>
  </div>
  );
};

export default ReportPage;
