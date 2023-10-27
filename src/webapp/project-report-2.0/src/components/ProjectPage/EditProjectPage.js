import React, { useState, useEffect } from 'react';
import { useParams  } from 'react-router-dom';
import axios from 'axios';
import TextField from '@mui/material/TextField';
import Container from '@mui/material/Container';
import Button from '@mui/material/Button';
import Select from '@mui/material/Select';
import MenuItem from '@mui/material/MenuItem';
import FormControl from '@mui/material/FormControl';
import InputLabel from '@mui/material/InputLabel';
import TmSelect from '../TmSelect'; // Путь к вашему компоненту

const EditProjectPage = () => {
  const [project, setProject] = useState(null);
  const [name, setName] = useState('');
  const [number, setNumber] = useState('');
  const [direction, setDirection] = useState('');
  const [startDate, setStartDate] = useState('');
  const [endDate, setEndDate] = useState('');
  const [pmList, setPmList] = useState([]);
  const { projectNumber } = useParams();
  const [tmList, setTmList] = useState([]);
  const [selectedPm, setSelectedPm] = useState('');
  const [selectedTms, setSelectedTms] = useState(['']);

  const handleSelectTm = (tmValue, index) => {
    const updatedTms = [...selectedTms];
    updatedTms[index] = tmValue; // Обновляем выбранный ТМ в массиве
    setSelectedTms(updatedTms);
    console.log(selectedTms)
  };
  
  const handleAddTm = () => {
    setSelectedTms([...selectedTms, null]);
  };
  
  const handleRemoveTm = (index) => {
    const updatedTms = [...selectedTms];
    updatedTms.splice(index, 1);
    setSelectedTms(updatedTms);
  };


  const handleSave = async () => {
    const updatedProject = {
      projectName: name,
      projectNumber: number,
      direction,
      startDate: startDate,
      endDate: endDate,
      pm: [selectedPm],
      tm: selectedTms,
    };

    try {
      const response = await axios.post('http://77.50.236.202:48225/api/EditProject', updatedProject, {
        headers: {
          Accept: 'application/json',
          Authorization: `Bearer ${localStorage.getItem('token')}`,
        },
      });
      if(response.data){
        window.location.href = "/ControlPanel"
      }
    } catch (error) {
      console.error('Ошибка при обновлении проекта:', error);
    }
  };

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

  useEffect(() => {
    fetchPmList();
    fetchTmList();
    const fetchProject = async () => {
      try {
        const response = await axios.get(`http://77.50.236.202:48225/api/getProject/${projectNumber}`, {
          headers: {
            Accept: 'application/json',
            Authorization: `Bearer ${localStorage.getItem('token')}`,
          },
        });
        setProject(response);
        setName(response.data.name);
        setNumber(response.data.projectNumber);
        setDirection(response.data.direction.name);
        setStartDate(response.data.dateStart);
        setEndDate(response.data.dateEnd);
      } catch (error) {
        console.error('Ошибка при получении проекта:', error);
      }
    };
    fetchProject();
  }, [projectNumber]);

  if (!project) {
    return <div>Loading...</div>;
  }

  return (
    <Container>
      <h2>Редактировать проект</h2>
      <div>
        <TextField
          fullWidth
          label="Название"
          variant="outlined"
          value={name}
          onChange={(e) => setName(e.target.value)}
          margin="normal"
        />
      </div>
      <div>
        <TextField
          fullWidth
          label="Номер"
          variant="outlined"
          value={number}
          onChange={(e) => setNumber(e.target.value)}
          margin="normal"
        />
      </div>
      <div>
        <TextField
          fullWidth
          label="Направление"
          variant="outlined"
          value={direction}
          onChange={(e) => setDirection(e.target.value)}
          margin="normal"
        />
      </div>
      <div>
        <TextField
          fullWidth
          label="Дата начала"
          variant="outlined"
          type="date"
          value={startDate}
          onChange={(e) => setStartDate(e.target.value)}
          InputLabelProps={{ shrink: true }}
          margin="normal"
        />
      </div>
      <div>
        <TextField
          fullWidth
          label="Дата окончания"
          variant="outlined"
          type="date"
          value={endDate}
          onChange={(e) => setEndDate(e.target.value)}
          InputLabelProps={{ shrink: true }}
          margin="normal"
        />
      </div>
      <div>
        <FormControl fullWidth variant="outlined" margin="normal">
          <InputLabel htmlFor="pm">ПМ</InputLabel>
          <Select
            label="ПМ"
            onChange={(e) => setSelectedPm(e.target.value)}
            >
            {pmList.map((pm) => (
              <MenuItem key={pm.id} value={pm}>
                {`${pm}`}
              </MenuItem>
            ))}
          </Select>
        </FormControl>
      </div>
      <div>
            {selectedTms.map((selectedTm, index) => (
            <TmSelect
            key={index}
            tmList={tmList}
            selectedTm={selectedTm}
            onSelectTm={(tmValue) => handleSelectTm(tmValue, index)} // Передаем строку выбранного ТМ
            onRemoveTm={() => handleRemoveTm(index)}
          />
        ))}
      </div>
      <div>
      <Button type="button" variant="contained" color="primary" onClick={handleAddTm}>
        Добавить ТМ
      </Button>
      </div>
      <Button variant="contained" color="primary" onClick={handleSave}>Сохранить</Button>
    </Container>
  );
};

export default EditProjectPage;
