import React from 'react';
import './Report.css'; // Подключаем CSS
import def from '../ProjectPage/default.png';
import pdf from '../ProjectPage/pdf.png';

const Report = ({ report }) => {
  const { artifactPath } = report;

  const getIconForFileType = (fileName) => {
    const extension = fileName.split('.').pop();
    switch (extension.toLowerCase()) {
      case 'pdf':
        return pdf; // Пример класса для иконки PDF
      case 'jpg':
      case 'jpeg':
      case 'png':
        return 'image-icon'; // Пример класса для иконки изображения
      // Добавьте другие расширения и классы по аналогии
      default:
        return def; // Класс по умолчанию
    }
  };

  return (
    <div className="report-card"> {/* Применяем класс */}
      <div className="report-content">
        <div className="report-meta">
          <h1 className="report-date">Дата публикации: {report.dateCreate}</h1>
          <h2 className="report-tm">ТМ: {report.project.tm.name} {report.project.tm.surname}</h2>
        </div>
        <h2 className="report-text">{report.text}</h2>
        <h3 className="report-mood">Мотивация команды: {report.teamMood}</h3>
        <h3 className="report-satisfaction">Удовлетворенность заказчика: {report.customerSatisfaction}</h3>
        <h3 className="report-activity">Завершенность проекта: {report.projectActivity}</h3>
        {artifactPath && artifactPath.map((fileName, index) => (
          <div key={index} className="report-artifact">
            <img src={getIconForFileType(fileName)} alt="" /> {/* Добавляем alt для изображений */}
            <p className="artifact-name">{fileName}</p>
          </div>
        ))}
      </div>
    </div>
  );
};

export default Report;
