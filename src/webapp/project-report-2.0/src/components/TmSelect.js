// TmSelect.js
import React from 'react';
import Select from '@mui/material/Select';
import MenuItem from '@mui/material/MenuItem';
import FormControl from '@mui/material/FormControl';
import InputLabel from '@mui/material/InputLabel';

const TmSelect = ({ tmList, onSelectTm, selectedTm, onRemoveTm }) => {
  const handleTmSelect = (e) => {
    onSelectTm(e.target.value);
  };
  const handleRemoveTm = () => {
    onRemoveTm();
  };

  return (
    <div>
      <FormControl fullWidth variant="outlined" margin="normal">
        <InputLabel htmlFor="tm">ТМ</InputLabel>
        <Select
          label="ТМ"
          value={selectedTm || ''}
          onChange={handleTmSelect}
        >
          {tmList.map((tm) => (
            <MenuItem key={tm.id} value={tm}>
              {`${tm}`}
            </MenuItem>
          ))}
        </Select>
      </FormControl>
      <button type="button" onClick={handleRemoveTm}>
        Удалить ТМ
      </button>
    </div>
  );
};

export default TmSelect;
