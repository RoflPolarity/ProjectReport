import React from 'react';
import { Link } from 'react-router-dom';
import Accordion from '@mui/material/Accordion';
import AccordionSummary from '@mui/material/AccordionSummary';
import AccordionDetails from '@mui/material/AccordionDetails';
import Typography from '@mui/material/Typography';
import ExpandMoreIcon from '@mui/icons-material/ExpandMore';
import Button from '@mui/material/Button';
import ReportIcon from '@mui/icons-material/Report';
import Report from './Report';

const ReportsAccordion = ({ reports, project_id }) => {
  return (
        <Accordion style={{ width: '100%' }}>
        <AccordionSummary expandIcon={<ExpandMoreIcon />}>
        <Typography>Отчеты</Typography>
      </AccordionSummary>
      <AccordionDetails>
        <div style={{ width: '100%' }}>
        <Link to={`/project/add-report?project_id=${project_id}`} style={{ textDecoration: 'none' }}>
            <Button
              variant="contained"
              color="primary"
              startIcon={<ReportIcon />}
              style={{ marginBottom: '16px' }}
            >
              Добавить отчет
            </Button>
          </Link>
          {reports.map((report) => (
            <Report key={report.id} report={report} />
          ))}
        </div>
      </AccordionDetails>
    </Accordion>
  );
};

export default ReportsAccordion;
