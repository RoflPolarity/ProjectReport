import React from 'react';
import Card from '@mui/material/Card';
import CardContent from '@mui/material/CardContent';
import Typography from '@mui/material/Typography';
import pdf_icon from './pdf.png'
import default_icon from './default.png';



const Report = ({ report }) => {
  const { artifactPath } = report;

  const getIconForFileType = (fileName) => {
    const extension = fileName.split('.').pop();
    switch (extension.toLowerCase()) {
      case 'pdf':
        return pdf_icon; // Пример пути к иконке PDF
      case 'jpg':
      case 'jpeg':
      case 'png':
        return '/icons/image-icon.png'; // Пример пути к иконке изображения
      // Добавьте другие расширения и пути к иконкам по аналогии
      default:
        return default_icon; // Иконка по умолчанию
    }
  };

  return (
    <Card style={{ marginBottom: '16px', marginLeft:'15px' }}>
      <CardContent>
        <Typography variant="subtitle2" color="textSecondary">
          Дата публикации: {report.dateCreate}
        </Typography>
        <Typography variant="subtitle2" color="textSecondary">
          ТМ: {report.project.tm.name} {report.project.tm.surname}
        </Typography>
        <Typography variant="body1">{report.text}</Typography>
        <Typography variant="body2">Мотивация команды: {report.teamMood}</Typography>
        <Typography variant="body2">Удовлетворенность заказчика: {report.customerSatisfaction}</Typography>
        <Typography variant="body2">Завершенность проекта: {report.projectActivity}</Typography>
        {artifactPath && artifactPath.map((fileName, index) => (
          <div key={index}>
            <img src= {getIconForFileType(fileName)} />
            <p>{fileName}</p>
          </div>
        ))}
      </CardContent>
    </Card>
  );
};

export default Report;
