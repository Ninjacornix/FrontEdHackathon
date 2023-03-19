import axios from "axios"
import React from "react"

export default function Records() {
    const [records, setRecords] = React.useState([])

    React.useEffect(() => {
        axios.get('http://localhost:3000/api/records', {
            headers: {
                Authorization: `Bearer ${localStorage.getItem('token')}`
            }
        }).then(res => {
            setRecords(res.data)
        })
        .catch(err => {
            console.log(err)
        })
    }, [])


    return (
        <div className="flex flex-col items-center w-full h-full bg-gray-50 rounded">
            <div className="mt-8 mb-8 bg-slate-700 rounded">
                <h1 className="text-2xl font-bold text-center">Records</h1>
            </div>
        </div>
    )
}