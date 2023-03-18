import { useState } from "react";
import RegisterForm from "@/components/forms/RegisterForm"
import VerifyForm from "@/components/forms/VerifyForm"
import { useRouter } from "next/router";

export default function Register() {
    const [showVerify, setShowVerify] = useState(false);
    const router = useRouter();

    const handleShowVerify = () => {
        setShowVerify(true);
        router.push("/login");
    }

    if(localStorage.getItem("token") == null) {
        return (
            <>
                <RegisterForm handleShowVerify={handleShowVerify}/>
            </>
        )
    } else {
        router.push("/dashboard");
    }
}