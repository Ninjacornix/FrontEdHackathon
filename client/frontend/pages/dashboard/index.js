import React from 'react'
import Navbar from '@/components/layout/Navbar'
import { useRouter } from "next/router";

const Dashboard = () => {
  const router = useRouter();
  if(localStorage.getItem("token") == null) {
    router.push("/login");
  } else{
    return (
      <>
      <Navbar />
      <div>Dashboard</div>
      </>
    )
  }
}

export default Dashboard