import { SignUpForm } from "../components/SignUpForm.jsx";
import "./SigningPages.css";

export const SignUpPage = () => {
    return (
        <div className="container-fluid vh-100">
            <div className="row full-height d-flex align-content-center">
                <div className="col-7 image-container ps-0">
                    <img
                        src="https://i.pinimg.com/564x/6a/a5/af/6aa5afbc56e530e0fd7676ff3fdf81b4.jpg"
                        alt="Sign In page side image"
                    />
                </div>
                <div className="col d-flex flex-column align-items-center justify-content-center">
                    <h1>Join the Chitter!</h1>
                    <p>Connect with friends and the world around you on Chitter.</p>
                    <SignUpForm />
                    <p>Already have an account?</p>
                    <a href="/signIn">
                        <button className='sign-in-button'>Sign In</button>
                    </a>
                </div>
            </div>
        </div>
    );
};
