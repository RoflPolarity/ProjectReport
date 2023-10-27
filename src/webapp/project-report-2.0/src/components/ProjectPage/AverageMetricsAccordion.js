import React from 'react';
import Accordion from '@mui/material/Accordion';
import AccordionSummary from '@mui/material/AccordionSummary';
import AccordionDetails from '@mui/material/AccordionDetails';
import Typography from '@mui/material/Typography';
import ExpandMoreIcon from '@mui/icons-material/ExpandMore';
import ChartAccordionDetails from '../ChartAccordionDetails';

const AverageMetricsAccordion = ({ reports }) => {
  
  return (
    <Accordion>
      <AccordionSummary expandIcon={<ExpandMoreIcon />}>
        <Typography>Показатели</Typography>
      </AccordionSummary>
      <AccordionDetails>
        <ChartAccordionDetails reports={reports} />
      </AccordionDetails>
    </Accordion>
  );
};

export default AverageMetricsAccordion;
