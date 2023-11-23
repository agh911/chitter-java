import { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import '../SignInForm.css';

import { checkSignIn } from './authenticationHelpers.js';

export const SignInForm = ({ handleSignIn }) => {
    const [signIn, setSignIn] = useState({
        email: '',
        password: ''
    });

    const navigate = useNavigate();

    const handleChange = (e) => {
        const { name, value } = e.target;
        setSignIn((signIn) => ({
            ...signIn,
            [name]: value
        }));
    }

    const signInSubmitHandler = async (e) => {
        e.preventDefault();

        const signInStatus = await checkSignIn(signIn);

        if (signInStatus) {
            handleSignIn(signIn);
            navigate('/');
        }
    }

    return (
        <div className="signin-form">
            <h2>Sign In</h2>
            <form onSubmit={signInSubmitHandler}>
                <label htmlFor="email">Email:</label>
                <input
                    type="email"
                    id="email"
                    name="email"
                    value={signIn.email}
                    onChange={handleChange}
                    placeholder="johndoe@mail.com"
                    required
                />
                <label htmlFor="password">Password:</label>
                <input
                    type="password"
                    id="password"
                    name="password"
                    value={signIn.password}
                    onChange={handleChange}
                    placeholder="Your password"
                    required
                />
                <button className="font-bold" type="submit">Sign In</button>
            </form>
        </div>
    )
}
