import { useState } from "react";
import LoginForm from "@/components/forms/LoginForm";
import VerifyForm from "@/components/forms/VerifyForm";

export default function Login() {
    const [showVerify, setShowVerify] = useState(false);
    const [email, setEmail] = useState("");
    const [password, setPassword] = useState("");
    const [code, setCode] = useState("");

    const handleShowVerify = () => {
        setShowVerify(!showVerify);
    }

    const handleIncomingData = (data) => {
        console.log(data);
        setEmail(email);
        setPassword(password);
        setCode(code);
    }

    return (
        <>
            <LoginForm handleShowVerify={handleShowVerify} handleIncomingData={handleIncomingData}/>
            <VerifyForm showVerify={showVerify}/>
        </>
    )
}