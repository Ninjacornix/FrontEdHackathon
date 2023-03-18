export default function Users(props) {
    return (
        <div className="flex flex-col items-center w-full h-full">
            {props.users.map((user, index) => {
                return (
                    <div key={index} className="mt-8 mb-8 bg-slate-700 rounded">
                        <h1 className="text-2xl font-bold text-center">{user.name}</h1>
                    </div>
                )
            })}
        </div>
    )
}