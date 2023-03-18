import React from "react";
import Navbar from "@/components/layout/Navbar";
import Users from "@/components/layout/Users";
import { useState } from "react";

const System = () => {

  
  const dummySystem = [
    {
      id: 1,
      name: "Username 1",
    },
    {
      id: 2,
      name: "Username 2",
    },
    {
      id: 3,
      name: "Username 3",
    }
  ];

  const [users, setUsers] = useState(dummySystem);

  return (
    <>
      <Navbar />
      <div className="my-8 w-5/6 m-auto font-sans"></div>
      <h1 className="text-4xl font-bold font-mono text-center mb-10">
        SYSTEM
      </h1>
      <div className="centered flex flex-col items-center justify-center px-6 py-8 mx-auto md:h-screen lg:py-0">
        <Users users={users} />
      </div>
    </>
  );
};

export default System;
