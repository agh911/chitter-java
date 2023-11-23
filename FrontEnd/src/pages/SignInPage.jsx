import PropTypes from "prop-types";
import { useParams, useNavigate } from "react-router-dom";
import { SignInForm } from "../components/authentication/SignInForm.jsx";
import "./SigningPages.css";

export const SignInPage = ({ handleSignIn }) => {

    return (
        <div className="container-fluid vh-100">
            <div className="row full-height d-flex align-content-center">
                <div className="col-7 image-container ps-0">
                    <img
                        src="https://i.pinimg.com/564x/6a/a5/af/6aa5afbc56e530e0fd7676ff3fdf81b4.jpg"
                        alt="Sign Up page side image"
                    />
                </div>
                <div className="col d-flex flex-column align-items-center justify-content-center">
                    <h1>Welcome to Chitter!</h1>
                    <p>Connect with friends and the world around you on Chitter.</p>
                    <SignInForm handleSignIn={handleSignIn} />
                    <p>Don't have an account?</p>
                    <a href="/signUp">
                        <button className='sign-up-button'>
                            Sign Up
                        </button>
                    </a>
                </div>
            </div>
        </div>
    );
};

SignInPage.propTypes = {
    handleSignIn: PropTypes.func.isRequired,
}