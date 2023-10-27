import React, { useState, useEffect } from 'react';
import Container from '@mui/material/Container';
import Typography from '@mui/material/Typography';
import TextField from '@mui/material/TextField';
import Button from '@mui/material/Button';
import MenuItem from '@mui/material/MenuItem';
import FormControl from '@mui/material/FormControl';
import InputLabel from '@mui/material/InputLabel';
import Select from '@mui/material/Select';
import Autocomplete from '@mui/material/Autocomplete';
import { useNavigate } from 'react-router-dom';

const AddProject = () => {
  const navigate = useNavigate();
  const navigateToControlPanel=()=>{navigate("/ControlPanel")}
  const [projectName, setProjectName] = useState('');
  const [projectNumber, setProjectNumber] = useState('');
  const [startDate, setStartDate] = useState('');
  const [endDate, setEndDate] = useState('');
  const [status, setStatus] = useState('');
  const [pmList, setPmList] = useState([]);
  const [tmList, setTmList] = useState([]);
  const [selectedPm, setSelectedPm] = useState('');
  const [selectedTm, setSelectedTm] = useState('');
  const [direction, setDirection] = useState('');
  const [dateError, setDateError] = useState(false);

  const validateDates = () => {
    const startDateObj = new Date(startDate);
    const endDateObj = new Date(endDate);

    if (endDateObj < startDateObj) {
      setDateError(true);
    } else {
      setDateError(false);
    }
  };

  useEffect(() => {
    validateDates();
  }, [startDate, endDate]);

  useEffect(() => {
    fetchPmList();
    fetchTmList();
  }, []);

  const fetchPmList = async () => {
    try {
      const response = await fetch('http://77.50.236.202:48225/api/GetPmList', {
        headers: {
          Authorization: `Bearer ${localStorage.getItem('token')}`,
        },
      });
      const data = await response.json();
      setPmList(data);
    } catch (error) {
      console.error('Ошибка при получении списка ПМ:', error);
    }
  };

  const fetchTmList = async () => {
    try {
      const response = await fetch('http://77.50.236.202:48225/api/GetPmList', {
        headers: {
          Authorization: `Bearer ${localStorage.getItem('token')}`,
        },
      });
      const data = await response.json();
      setTmList(data);
    } catch (error) {
      console.error('Ошибка при получении списка ТМ:', error);
    }
  };

  const handleFormSubmit = (event) => {
    event.preventDefault();

    const projectEndDate = endDate ? endDate : null;
    const startDateObj = new Date(startDate);
    const endDateObj = new Date(projectEndDate);
    const today = new Date();
    let projectStatus;

    if (projectEndDate) {
      if (endDateObj <= today) {
        projectStatus = 'Завершен';
      } else {
        projectStatus = 'В процессе';
      }
    } else {
      projectStatus = 'В процессе';
    }

    const projectData = {
      projectName: projectName,
      projectNumber: projectNumber,
      startDate: startDate,
      endDate: projectEndDate,
      status: projectStatus,
      pm: selectedPm,
      tm: selectedTm,
      direction: direction,
    };

    fetch('http://77.50.236.202:48225/api/AddProject', {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json',
        Authorization: `Bearer ${localStorage.getItem('token')}`,
      },
      body: JSON.stringify(projectData),
    })
      .then((response) => response.json())
      .then((data) => {
        if (data.success) {
          window.location.href = "/ControlPanel";
        }
            })
      .catch((error) => {
        console.error('Ошибка при добавлении проекта:', error);
      });
  };

  return (
    <Container maxWidth="sm">
      <Typography variant="h4" align="center" gutterBottom style={{marginTop:'7px'}}>
        Добавить проект
      </Typography>
      <form onSubmit={handleFormSubmit}>
        <TextField
          label="Название проекта"
          variant="outlined"
          fullWidth
          value={projectName}
          onChange={(e) => setProjectName(e.target.value)}
          margin='normal'
        />
  
        <div style={{ margin: '16px 0' }}>
          <TextField
            label="Номер проекта"
            variant="outlined"
            fullWidth
            value={projectNumber}
            onChange={(e) => setProjectNumber(e.target.value)}
            margin='normal'
          />
        </div>
        
        <FormControl fullWidth variant="outlined" margin='normal'>
          <InputLabel htmlFor="direction">Направление</InputLabel>
          <Select
            value={direction}
            label="Направление"
            onChange={(e) => setDirection(e.target.value)}>
          <MenuItem value="АТ">АТ</MenuItem>
          <MenuItem value="ФТ">ФТ</MenuItem>
          <MenuItem value="НТ">НТ</MenuItem>
          <MenuItem value="DEV">DEV</MenuItem>
          <MenuItem value="Другое">Другое</MenuItem>
          </Select>
        </FormControl>

        <div style={{ margin: '16px 0' }}>
          <TextField
            label="Дата начала проекта"
            variant="outlined"
            fullWidth
            type="date"
            value={startDate}
            onChange={(e) => setStartDate(e.target.value)}
            InputLabelProps={{ shrink: true }}
            margin="normal"
          />
        </div>
  
        <div style={{ margin: '16px 0' }}>
          <TextField
            label="Дата окончания проекта"
            variant="outlined"
            fullWidth
            type="date"
            value={endDate}
            onChange={(e) => setEndDate(e.target.value)}
            InputLabelProps={{ shrink: true }}
            margin="normal"
          />
        </div>
        {dateError && (
          <Typography variant="body2" color="error">
            Дата окончания меньше даты начала проекта!
          </Typography>
        )}
        <div style={{ margin: '16px 0' }}>
          <Autocomplete
            value={selectedPm}
            id='pm-select'
            onChange={(event, newValue) => setSelectedPm(newValue)}
            options={pmList}
            renderInput={(params) => <TextField {...params} label="Выберите ПМ" />}
          />
        </div>
  
        <div style={{ margin: '16px 0' }}>
          <Autocomplete
            margin='normal'
            value={selectedTm}
            id="tm-select"        
            onChange={(event, newValue) => setSelectedTm(newValue)}
            options={tmList}
            renderInput={(params) => <TextField {...params} label="Выберите ТМ" />}
          />
        </div>
  
        <div style={{ margin: '16px 0' }}>
          
          <Button
            variant="contained"
            color="primary"
            type="submit"
            margin='normal'
          >
            Создать проект
          </Button>
        </div>
  
      </form>
    </Container>
  );
}  

export default AddProject;
