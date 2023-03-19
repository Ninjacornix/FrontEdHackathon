import {Chart} from 'react-google-charts';
import axios from 'axios';
import React from 'react';

export default function Graph() {

const [data, setData] = React.useState([['Time', 'Low', 'Medium', 'High', 'Critical']]);

// axios get in interval
React.useEffect(() => {
    const interval = setInterval(() => {
        axios.get('http://localhost:3000/api/alarms', {
            headers: {
                Authorization: `Bearer ${localStorage.getItem('token')}`
            }
        }).then(res => {
            const data = [['Time', 'Low', 'Medium', 'High', 'Critical']];
            res.data.forEach((alarm) => {
                data.push([alarm.time, alarm.low, alarm.medium, alarm.high, alarm.critical]);
            });
            if(data.length > 5){
                data.shift();
            }
            setData(data);
        })
        .catch(err => {
            console.log(err)
        })
    }, 1000);
    return () => clearInterval(interval);
}, []);

const options = {
    curveType: 'function',
    legend: { position: 'bottom' },
};
    return (
        <Chart
            width={'500px'}
            height={'300px'}
            chartType="AreaChart"
            loader={<div>Loading Chart</div>}
            data={data}
            options={options}
        />
    );
}