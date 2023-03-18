import { useState } from "react";
import LoginForm from "@/components/forms/LoginForm";
import VerifyForm from "@/components/forms/VerifyForm";

export default function Login() {
    const [showVerify, setShowVerify] = useState(false);

    const handleShowVerify = () => {
        setShowVerify(!showVerify);
        console.log(showVerify);
    }

    return (
        <>
            <LoginForm handleShowVerify={handleShowVerify} />
            <VerifyForm showVerify={showVerify}/>
        </>
    )
}