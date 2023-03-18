import React from 'react'
import Navbar from '@/components/layout/Navbar'
import { AiFillDelete } from 'react-icons/ai';


const Alerts = () => {
  const dummyAlerts = [
    {
      id: 1,
      message: "Low disk space",
      description: "Your hard drive is almost full. Please free up some space.",
      dangerLevel: 30
    },
    {
      id: 2,
      message: "Server down",
      description: "The server is currently unavailable. Our engineers are working to fix the issue.",
      dangerLevel: 90
    },
    {
      id: 3,
      message: "Unauthorized access",
      description: "Someone tried to access your account without your permission. Please change your password immediately.",
      dangerLevel: 60
    }
  ];

  const dangerClass = (dangerLevel) => {
    if(dangerLevel <= 33 && dangerLevel >= 0){
      return 'bg-red-200'
    }else if(dangerLevel <= 66 && dangerLevel >= 34 ){
      return 'bg-red-300'
    }else{
      return 'bg-red-500'
    }
  };
  
  return (
    <>
    <Navbar />
    <div className='my-8 w-5/6 m-auto'>
      <h1 className='text-4xl font-bold font-mono text-center'>ALERTS</h1>
      <div>
        {dummyAlerts.map((alert) => 
          <div className={` my-8 p-4 rounded text-black ${dangerClass(alert.dangerLevel)}`}>
            <div className='flex items-center justify-between'>
            <h2 className='text-3xl font-bold font-mono'>{alert.message} </h2>
            <AiFillDelete />
            </div>
            <p>{alert.description}</p>
          </div>
        )}
      </div>

    </div>
    </>
  )
}

export default Alerts