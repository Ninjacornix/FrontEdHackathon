import React from 'react'
import Navbar from '@/components/layout/Navbar'
import { useRouter } from "next/router";

const Dashboard = () => {
  const router = useRouter();

  if(typeof window !== 'undefined' && localStorage.getItem("token") == null) {
    router.push("/login");
  }

  return (
    <>
    <Navbar />
    <div>Dashboard</div>
    </>
  )
}

export default Dashboard