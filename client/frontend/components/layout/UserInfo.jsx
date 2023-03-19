import axios from "axios"
import React from "react"

export default function UserInfo() {

    const [user, setUser] = React.useState({})

    React.useEffect(() => {
        axios.get('http://localhost:3000/api/users', {
            headers: {
                Authorization: `Bearer ${localStorage.getItem('token')}`
            }
        }).then(res => {
            setUser(res.data)
        })
        .catch(err => {
            console.log(err)
        })
    }, [])

    return (
        <div class="flex font-sans bg-gray-50 rounded">
            <div class="flex-none w-48 relative">
                <img src="https://cdn.pixabay.com/photo/2015/10/05/22/37/blank-profile-picture-973460__340.png" alt="" class="absolute inset-0 w-full h-full object-cover" loading="lazy" />
            </div>
            <form class="flex-auto p-6">
                <div class="flex flex-wrap">
                    <h1 class="flex-auto text-lg font-semibold text-slate-900">
                        {user.FirstName}
                    </h1>
                    <div class="w-full flex-none text-sm font-medium text-slate-700 mt-2">
                        {user.LastName}
                    </div>
                    <div class="w-full flex-none text-sm font-medium text-slate-700 mt-2">
                        {user.mail}
                    </div>
                    <div class="w-full flex-none text-sm font-medium text-slate-700 mt-2">
                        {user.phone}
                    </div>
                </div>
            </form>
        </div>
    )
}