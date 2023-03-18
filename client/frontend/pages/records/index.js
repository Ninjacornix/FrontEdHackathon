import React from "react";
import Navbar from "@/components/layout/Navbar";
import { AiFillDelete } from "react-icons/ai";
import { AiOutlinePlus } from "react-icons/ai";
import { AiFillEye } from "react-icons/ai";
import { useState } from "react";
import {
  TextField,
  Box,
  Button,
  Typography,
  Modal,
  IconButton,
} from "@mui/material";

const Records = () => {
  const dummyRecords = [
    {
      id: 1,
      name: "Low disk space",
      source: "github.com",
      severity:"High",
      potentialImpact:0.85,
      time:"18/3/2023"
    },
    {
      id: 2,
      name: "SQL Injection Vulnerability",
      source: "OWASP",
      severity: "Critical",
      potentialImpact: 0.95,
      time:"17/3/2023"
      },
  
      {
      id: 3,
      name: "Cross-Site Scripting (XSS) Attack",
      source: "NIST",
      severity: "High",
      potentialImpact: 0.80,
      time:"16/3/2023"
      },
  
      {
      id: 4,
      name: "Broken Authentication and Session Management",
      source: "SANS Institute",
      severity: "Medium",
      potentialImpact: 0.70,
      time:"15/3/2023"
      },
  
      {
      id: 5,
      name: "Denial-of-Service (DoS) Attack",
      source: "CERT/CC",
      severity: "High",
      potentialImpact: 0.90,
      time:"14/3/2023"
      },
  ];

  const [records, setRecords] = useState(dummyRecords);

  
  //Modal
  const [open, setOpen] = useState(false);
  const handleOpen = () => setOpen(true);
  const handleClose = () => setOpen(false);
  const [name,setName] = useState("");
  const [source,setSource] = useState("");
  const [severity,setSeverity] = useState("");
  const [potentialImpact,setPotentialImpact] = useState("");
  const [index,setIndex] = useState(0);

  const style = {
    position: "absolute",
    top: "50%",
    left: "50%",
    transform: "translate(-50%, -50%)",
    width: 400,
    bgcolor: "background.paper",
    border: "2px solid #000",
    boxShadow: 24,
    p: 4,
    color: "#000",
  };

  const handleSubmit = (event) => {
    event.preventDefault();
    const date = new Date();

    let day = date.getDate();
    let month = date.getMonth() + 1;
    let year = date.getFullYear();

    const newRecord = {
      id: records.length + 1,
      name:name,
      source:source,
      severity:severity,
      potentialImpact:potentialImpact,
      time:`${day}/${month}/${year}`,
    };

    setRecords([...records, newRecord]);
    setName("");
    setSource("");
    setSeverity("");
    setPotentialImpact("");
    handleClose();
    
  };

  const deleteRecord = (id) => {
    setRecords(records.filter((record) => record.id !== id));
    
  };

  return (
    <>
      <Navbar />
      <div className="my-8 w-5/6 m-auto font-sans">
        <h1 className="text-4xl font-bold font-mono text-center mb-10">
          RECORDS
        </h1>
        <button
          onClick={handleOpen}
          className="flex items-center justify-center p-4 w-2/6 m-auto h-16 border-solid border-2 rounded border-blue-900 text-black bg-blue-900 gap-2"
        >
          ADD RECORD <AiOutlinePlus />
        </button>
        <div className="my-12">
          {records.map((record) => (
            <div key={record.id}
              className={` my-8 p-4 rounded text-black bg-sky-500`}
            >
              <div className="flex items-center justify-between">
                <div className="text-3xl"><IconButton onClick={() => setIndex(record.id)}><AiFillEye /></IconButton></div>
                <h2 className="text-3xl w-3/6 font-bold font-mono">
                  {record.name}{" "}
                </h2>
                <p>{record.time}</p>
                <IconButton onClick={() => deleteRecord(record.id)}>
                  <AiFillDelete
                    className="text-3xl"
                    
                  />
                </IconButton>
              </div>
              {index === record.id && <div>
              <div className="flex my-4 items-center justify-center text-center">
                <h3 className="flex-1">Source</h3>
                <h3 className="flex-1">Severity</h3>
                <h3 className="flex-1">PotentialImpact</h3>
              </div>
              <div className="flex my-4 text-center">
                <p className="flex-1">{record.source}</p>
                <p className="flex-1">{record.severity}</p>
                <p className="flex-1">{record.potentialImpact}</p>
              </div>
              </div>}
              
            </div>
          ))}
        </div>

        <Modal
          open={open}
          onClose={handleClose}
          aria-labelledby="modal-modal-title"
          aria-describedby="modal-modal-description"
        >
          <Box sx={style}>
            <Typography id="modal-modal-title" variant="h6" component="h2">
              Add Record
            </Typography>
            <Typography
              id="modal-modal-description"
              sx={{
                mt: 2,
                display: "flex",
                flexDirection: "column",
                gap: "10px",
              }}
              component="span"
            >
              <TextField
                id="outlined-basic"
                label="Name"
                variant="outlined"
                value={name}
                onChange={(e) => setName(e.target.value)}
              />
              <br />
              <TextField
                id="outlined-basic"
                label="Source"
                variant="outlined"
                value={source}
                onChange={(e) => setSource(e.target.value)}
              />
              <br />
              <TextField
                id="outlined-basic"
                label="Severity"
                variant="outlined"
                value={severity}
                onChange={(e) => setSeverity(e.target.value)}
              />
              <br />
              <TextField
                id="outlined-basic"
                label="PotentialImpact"
                variant="outlined"
                value={potentialImpact}
                onChange={(e) => setPotentialImpact(e.target.value)}
                type="number"
                
                InputProps={{ inputProps: { min: 0, max: 1.00, step:0.01 } }}
              />

              <br />
              <Button variant="contained" onClick={handleSubmit}>
                Submit
              </Button>
            </Typography>
          </Box>
        </Modal>
      </div>
    </>
  );
};

export default Records;