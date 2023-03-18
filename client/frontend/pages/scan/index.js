import React, { useEffect, useState } from "react";
import Navbar from "@/components/layout/Navbar";
import { AiOutlineSearch } from "react-icons/ai";
import axios from "axios";
import { IconButton } from "@mui/material";
import { AiFillEye } from "react-icons/ai";

const Scan = () => {
  
  const [scan, setScan] = useState([]);
  const [num,setNum] = useState(-1);

  const fetchRecordsData = () => {
    axios.get("http://localhost:8080/records").then((response) => {
      setRecords([response.data]);
    });
  };

  const fetchScanData = () => {
    axios.get("http://localhost:8080/scan").then((response) => {
      setScan([response.data]);
    });
  };

  

  const dangerClass = (dangerLevel) => {
    if (dangerLevel <= 33 && dangerLevel >= 0) {
      return "bg-red-200";
    } else if (dangerLevel <= 66 && dangerLevel >= 34) {
      return "bg-red-300";
    } else {
      return "bg-red-500";
    }
  };


  
  console.log("Scan: ",scan);
  return (
    <>
      <Navbar />
      
      <div>
      <h1 className="text-4xl font-bold font-mono text-center my-10">
          SCAN
        </h1>
        <button
          onClick={fetchScanData}
          className="flex items-center justify-center text-3xl  p-4 w-2/6 m-auto h-16 border-solid border-2 rounded border-red-500 text-white bg-red-500 gap-2"
        >
          <AiOutlineSearch /> SCAN NOW
        </button>

        

        <h1 className="text-4xl font-bold font-mono text-center my-10">
          Scan History
        </h1>
        <div className="my-12">
          {scan.map((s,index) => (
            <div key={index}
              className={` my-8 p-4 rounded text-white bg-red-300 w-5/6 m-auto`}
            >
              <div className="flex items-center justify-between">
                <div>
                  <IconButton onClick={() => setNum(index === num ? -1 : index)}>
                  <AiFillEye />
                  </IconButton>
                </div>
                <h2 className="text-3xl font-bold font-mono">
                  
                  {s.message}{" "}
                </h2>
                
              </div>

              {console.log(s.data.threats)}
              <div>
              {num === index && <div className="">
                  {s.data.threats.map((threat) => <div className="border-b-2">
                    <div className="flex my-4 py-2">
                      <h3 className="flex-1 text-center">Name:</h3>
                      <h3 className="flex-1 text-center">Source:</h3>
                      <h3 className="flex-1 text-center">Severity:</h3>
                    </div>
                    <div className="flex">
                      <p className="flex-1 text-center">{threat.name}</p>
                      <p className="flex-1 text-center">{threat.source}</p>
                      <p className="flex-1 text-center">{threat.severity}</p>
                    </div>
                  </div>) }
                </div>}
              </div>
              

              
            </div>
          ))}
        </div>


      </div>
    </>
  );
};

export default Scan;
