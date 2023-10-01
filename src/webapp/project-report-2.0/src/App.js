import React, { useState, useEffect  } from 'react';
import {Routes, Route} from 'react-router-dom';
import Header from './components/Header/Header';
import MainContent from './components/MainContent/MainContent';
import LoginPage from './components/LoginPage/LoginPage';
import AdminPage from './components/AdminPage/AdminPage';
import AddProject from './components/addProject/AddProject';
import ProjectPage from './components/ProjectPage/ProjectPage';
import AddReportPage from './components/AddReport';
import ReportPage from './components/ReportPage/ReportPage';
import EditProjectPage from './components/ProjectPage/EditProjectPage';

export default function App() {
  const [isLoggedIn, setIsLoggedIn] = useState(false);
  const [userInfo, setUserInfo] = useState({});

  const handleLoginSuccess = (userInfo) => {
    setIsLoggedIn(true);
    setUserInfo(userInfo);
    localStorage.setItem("username", userInfo.name + " " + userInfo.surname);
  };

  useEffect(() => {
    const storedUsername = localStorage.getItem("username");
    if (storedUsername) {
      // Если имя пользователя есть в LocalStorage, обновляем состояние
      setIsLoggedIn(true);
      setUserInfo({
        name: storedUsername.split(" ")[0],
        surname: storedUsername.split(" ")[1],
      });
    }
  }, []);

  return (
    <div>
      <Header isLoggedIn={isLoggedIn} userInfo={userInfo}/>
      <Routes>        
        <Route path="/" element={<MainContent/>} />
        <Route path="/login" element={<LoginPage onLoginSuccess={handleLoginSuccess}/>} />
        <Route path='/ControlPanel' element={<AdminPage/>}/>
        <Route path='/AddProject' element={<AddProject/>}/>
        <Route path="/project/:projectNumber" element={<ProjectPage />} />
        <Route path='/project/add-report' element={<AddReportPage />}/>
        <Route path='/report/:projectNumber' element={<ReportPage/>}/>
        <Route path='/edit/:projectNumber' element={<EditProjectPage/>}/>
      </Routes>
    </div>
    
  );
}