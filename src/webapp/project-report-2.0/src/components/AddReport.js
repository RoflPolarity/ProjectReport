import React, { useState } from 'react';
import TextField from '@mui/material/TextField';
import Button from '@mui/material/Button';
import FormControl from '@mui/material/FormControl';
import Select from '@mui/material/Select';
import MenuItem from '@mui/material/MenuItem';
import InputLabel from '@mui/material/InputLabel';
import { useLocation } from 'react-router-dom';
import axios from 'axios';
import { useDropzone } from 'react-dropzone';

const AddReportPage = () => {
  const location = useLocation();
  const queryParams = new URLSearchParams(location.search);
  const projectId = queryParams.get('project_id');
  const [text, setText] = useState('');
  const [teamMotivation, setTeamMotivation] = useState('');
  const [successOfProject, setSuccessOfProject] = useState('');
  const [customerSatisfaction, setCustomerSatisfaction] = useState('');
  const [files, setFiles] = useState([]);

  const { getRootProps, getInputProps } = useDropzone({
    onDrop: (acceptedFiles) => {
      setFiles(acceptedFiles);
    },
  });

  const handleSubmit = async (e) => {
    e.preventDefault();
  
    const formData = new FormData();
    formData.append('project_id', projectId);
    formData.append('text', text);
    formData.append('team_motivation', teamMotivation);
    formData.append('success_of_project', successOfProject);
    formData.append('customer_satisfaction', customerSatisfaction);
  
    if (files.length === 0) {
      formData.append('files', null);
    } else {
      files.forEach((file) => {
        formData.append(`files`, file);
      });
    }
  
    try {
      await axios.post('http://77.50.236.202:48225/api/addReport', formData, {
        headers: {
          Authorization: `Bearer ${localStorage.getItem('token')}`,
          'Content-Type': 'multipart/form-data',
        },
      });
  
      window.location.href = `/project/${projectId}`;
    } catch (error) {
      console.error('Ошибка при добавлении отчета:', error);
    }
  };

  return (
    <div style={{ padding: '20px' }}>
      <form onSubmit={handleSubmit}>
        <div style={{ display: 'flex' }}>
          <TextField
            label="Текст"
            multiline
            rows={4}
            value={text}
            onChange={(e) => setText(e.target.value)}
            required
            style={{ width: '60%', marginRight: '20px'}}
          />
          <div style={{ display: 'flex', flexDirection: 'column', width: '40%' }}>
            <FormControl style={{ marginBottom: '20px' }}>
              <InputLabel>Настроение команды</InputLabel>
              <Select
                value={teamMotivation}
                onChange={(e) => setTeamMotivation(e.target.value)}
                required
              >
                <MenuItem value="Good">Good</MenuItem>
                <MenuItem value="Average">Average</MenuItem>
                <MenuItem value="Bad">Bad</MenuItem>
              </Select>
            </FormControl>
            <FormControl style={{ marginBottom: '20px' }}>
              <InputLabel>Удовлетворенность заказчика</InputLabel>
              <Select
                value={customerSatisfaction}
                onChange={(e) => setCustomerSatisfaction(e.target.value)}
                required
              >
                <MenuItem value="Good">Good</MenuItem>
                <MenuItem value="Average">Average</MenuItem>
                <MenuItem value="Bad">Bad</MenuItem>
              </Select>
            </FormControl>
            <FormControl>
              <InputLabel>Активность проекта</InputLabel>
              <Select
                value={successOfProject}
                onChange={(e) => setSuccessOfProject(e.target.value)}
                required
              >
                <MenuItem value="Good">Good</MenuItem>
                <MenuItem value="Average">Average</MenuItem>
                <MenuItem value="Bad">Bad</MenuItem>
              </Select>
            </FormControl>
          </div>
          <div {...getRootProps()} style={{ border: '1px dashed black', padding: '20px', marginBottom: '20px', marginLeft:'20px'}}>
          <input {...getInputProps()} />
          <p>Перетащите файлы сюда или кликните для выбора</p>
          <ul>
            {files.map(file => (
              <li key={file.name}>{file.name}</li>
            ))}
          </ul>
        </div>
        </div>
        <Button type="submit" variant="contained" color="primary">
          Добавить
        </Button>
      </form>
    </div>
  );
};

export default AddReportPage;
