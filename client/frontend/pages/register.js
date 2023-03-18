import { useState } from "react";
import RegisterForm from "@/components/forms/RegisterForm"
import VerifyForm from "@/components/forms/VerifyForm"

export default function Register() {
    const [showVerify, setShowVerify] = useState(false);

    const handleShowVerify = () => {
        setShowVerify(!showVerify);
    }

    return (
        <>
            <RegisterForm handleShowVerify={handleShowVerify}/>
            <VerifyForm showVerify={showVerify}/>
        </>
    )
}