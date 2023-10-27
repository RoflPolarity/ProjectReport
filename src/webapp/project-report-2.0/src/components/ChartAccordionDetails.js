import * as React from 'react';
import { Typography, AccordionDetails } from '@mui/material';
import { BarChart } from '@mui/x-charts/BarChart';

const ChartAccordionDetails = ({ reports }) => {
  const isEmptyReports = !reports || reports.length === 0;
  const BadData = [];
  const AverageData = [];
  const GoodData = [];
  const xLabels = [];

  if (!isEmptyReports) {
    reports.forEach(report => {
  
      xLabels.push(report.id);
      let BadMarks = 0;
      let AverageMarks = 0;
      let GoodMarks = 0;
      
      if (report['customerSatisfaction'] === 'Bad') {
        BadMarks+=1;
      }
      if (report['customerSatisfaction'] === 'Average') {
        AverageMarks += 2;
      }
      if (report['customerSatisfaction'] === 'Good') {
        GoodMarks += 3;
      }
      
      if (report['projectActibity'] === 'Bad') {
        BadMarks+=1;
            }
      if (report['projectActibity'] === 'Average') {
        AverageMarks += 2;
      }
      if (report['projectActibity'] === 'Good') {
        GoodMarks += 3;
      }

      if (report['teamMood'] === 'Bad') {
        BadMarks+=1;
      }
      if (report['teamMood'] === 'Average') {
        AverageMarks += 2;
      }
      if (report['teamMood'] === 'Good') {
        GoodMarks += 3;
      }
      AverageData.push(AverageMarks);
      BadData.push(BadMarks);
      GoodData.push(GoodMarks);
    });
  }

  

  return (
    <AccordionDetails >
        <div style={{ width: '100%', marginBottom: '20px' }}>
          {isEmptyReports ? (
            <Typography>Нет данных для отображения</Typography>
            ) : (
              <div id='chart-container'>
            <BarChart
            width={600}
            height={400}
            series={[
              { data: BadData, label: 'Bad', id: 'BvId', stack: 'total' , color:'#fdb462'},
              { data: AverageData, label: 'Average', id: 'AvId', stack: 'total' },
              { data: GoodData, label: 'Good', id: 'GvId', stack: 'total' },
            ]}
            xAxis={[{ data: xLabels, scaleType: 'band', categoryGapRatio: 0.3}]}
          />
            </div>
          )}
        </div>
    </AccordionDetails>
  );
};

export default ChartAccordionDetails;


