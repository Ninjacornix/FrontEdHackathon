import React from "react";
import Navbar from "@/components/layout/Navbar";
import { AiFillDelete } from "react-icons/ai";
import { AiOutlinePlus } from "react-icons/ai";
import { useState } from "react";
import {
  TextField,
  Box,
  Button,
  Typography,
  Modal,
  IconButton,
} from "@mui/material";

const Alerts = () => {
  const dummyAlerts = [
    {
      id: 1,
      message: "Low disk space",
      description: "Your hard drive is almost full. Please free up some space.",
      dangerLevel: 30,
    },
    {
      id: 2,
      message: "Server down",
      description:
        "The server is currently unavailable. Our engineers are working to fix the issue.",
      dangerLevel: 90,
    },
    {
      id: 3,
      message: "Unauthorized access",
      description:
        "Someone tried to access your account without your permission. Please change your password immediately.",
      dangerLevel: 60,
    },
  ];

  const [alerts, setAlerts] = useState(dummyAlerts);

  const dangerClass = (dangerLevel) => {
    if (dangerLevel <= 33 && dangerLevel >= 0) {
      return "bg-red-200";
    } else if (dangerLevel <= 66 && dangerLevel >= 34) {
      return "bg-red-300";
    } else {
      return "bg-red-500";
    }
  };
  //Modal
  const [open, setOpen] = useState(false);
  const handleOpen = () => setOpen(true);
  const handleClose = () => setOpen(false);
  const [message,setMessage] = useState("");
  const [description,setDescription] = useState("");
  const [dangerLevel,setDangerLevel] = useState("");

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
    

    const newAlert = {
      id: alerts.length + 1,
      message: message,
      description: description,
      dangerLevel: dangerLevel,
    };

    setAlerts([...alerts, newAlert]);
    setMessage("");
    setDescription("");
    setDangerLevel("");
    handleClose();
    console.log(alerts);
  };

  const deleteAlert = (id) => {
    setAlerts(alerts.filter((alert) => alert.id !== id));
    console.log(alerts);
  };

  return (
    <>
      <Navbar />
      <div className="my-8 w-5/6 m-auto font-sans">
        <h1 className="text-4xl font-bold font-mono text-center mb-10">
          ALERTS
        </h1>
        <button
          onClick={handleOpen}
          className="flex items-center justify-center p-4 w-2/6 m-auto h-16 border-solid border-2 rounded border-red-100 text-black bg-red-100"
        >
          ADD ALERT <AiOutlinePlus />
        </button>
        <div className="my-12">
          {alerts.map((alert) => (
            <div key={alert.id}
              className={` my-8 p-4 rounded text-black ${dangerClass(
                alert.dangerLevel
              )}`}
            >
              <div className="flex items-center justify-between">
                <h2 className="text-3xl font-bold font-mono">
                  {alert.message}{" "}
                </h2>
                <IconButton onClick={() => deleteAlert(alert.id)}>
                  <AiFillDelete
                    className="text-3xl"
                    
                  />
                </IconButton>
              </div>
              <p>{alert.description}</p>
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
              Add Alert
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
                label="Message"
                variant="outlined"
                value={message}
                onChange={(e) => setMessage(e.target.value)}
              />
              <br />
              <TextField
                id="outlined-basic"
                label="Description"
                variant="outlined"
                value={description}
                onChange={(e) => setDescription(e.target.value)}
              />
              <br />
              <TextField
                id="outlined-basic"
                label="Danger level"
                variant="outlined"
                value={dangerLevel}
                onChange={(e) => setDangerLevel(e.target.value)}
                type="number"
                InputProps={{ inputProps: { min: 0, max: 100 } }}
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

export default Alerts;
