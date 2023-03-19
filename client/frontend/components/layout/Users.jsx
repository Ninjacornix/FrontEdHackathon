import { AiFillEdit } from "react-icons/ai";
import { AiFillDelete } from "react-icons/ai";
import { useState } from "react";
import {
  TextField,
  Box,
  Button,
  Typography,
  Modal,
  IconButton,
} from "@mui/material";

export default function Users(props) {
  const [open, setOpen] = useState(false);
  const handleOpen = () => setOpen(true);
  const handleClose = () => setOpen(false);
  const [userId,setUserId] = useState(0);

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
  return (
    <div className="flex flex-col items-center w-full h-full">
      {props.users.map((user, index) => {
        return (
          <div
            key={index}
            className="mt-8 mb-8 bg-slate-700 rounded w-3/6 p-4 flex items-center justify-between"
          >
            <h1 className="text-2xl font-bold text-center">{user.name}</h1>
            <div>
              <IconButton onClick={() => {
                props.handleEdit(user.id);
                setUserId(user.id);
              }}>
                <AiFillEdit />
              </IconButton>
              <IconButton onClick={() => props.deleteUser(user.id)}>
                <AiFillDelete />
              </IconButton>
            </div>
          </div>
        );
      })}

      <Modal
        open={open}
        onClose={handleClose}
        aria-labelledby="modal-modal-title"
        aria-describedby="modal-modal-description"
      >
        <Box sx={style}>
          <Typography id="modal-modal-title" variant="h6" component="h2">
            Edit User
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
              onChange={(e) => props.setName(e.target.value)}
            />
            <br />
            <TextField
              id="outlined-basic"
              label="LastName"
              variant="outlined"
              onChange={(e) => props.setLastName(e.target.value)}
            />
            <br />
            <TextField
              id="outlined-basic"
              label="Password"
              variant="outlined"
              onChange={(e) => props.setPassword(e.target.value)}
            />
            <br />

            <TextField
              id="outlined-basic"
              label="Email"
              variant="outlined"
              onChange={(e) => props.setEmail(e.target.value)}
            />
            <br />

            <TextField
              id="outlined-basic"
              label="Role"
              variant="outlined"
              onChange={(e) => props.setRole(e.target.value)}
            />
            <br />

            <TextField
              id="outlined-basic"
              label="PhoneNumber"
              variant="outlined"
              onChange={(e) => props.setPhoneNumber(e.target.value)}
            />
            <br />
            <Button
              variant="contained"
              onClick={() => props.handleEdit(user)}
            >
              Edit
            </Button>
          </Typography>
        </Box>
      </Modal>
    </div>
  );
}
