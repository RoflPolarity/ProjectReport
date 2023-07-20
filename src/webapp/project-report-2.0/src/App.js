import React from 'react';
import {Routes, Route} from 'react-router-dom';
import Header from './components/Header/Header';
import MainContent from './components/MainContent/MainContent';
import Footer from './components/Footer/Footer';
import LoginPage from './components/LoginPage/LoginPage';

export default function App() {
  return (
    <div>
      <Header />
      <Routes>        
        <Route path="/" element={<MainContent/>} />
        <Route path="/login" element={<LoginPage/>} />
      </Routes>
      <Footer />
    </div>
    
  );
}