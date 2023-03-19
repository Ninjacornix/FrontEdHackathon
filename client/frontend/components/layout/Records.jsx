import axios from "axios"
import React from "react"

export default function Records() {
    const [records, setRecords] = React.useState([])

    React.useEffect(() => {
        axios.get('https://evil-evaluators-spring-evil-evaluators-3.azuremicroservices.io/records', {}).then(res => {
            console.log(res.data)
        })
        .catch(err => {
            console.log(err)
        })
    }, [])

    return (
        <div className="flex flex-col items-center w-full h-full bg-gray-50 rounded">
            {records.map((record, index) => {
                return (
                    <div key={index} className="mt-8 mb-8 bg-slate-700 rounded">
                        <h1 className="text-2xl font-bold text-center">{record.name}</h1>
                    </div>
                )
            })}
        </div>
    )
}