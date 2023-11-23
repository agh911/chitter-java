import axios from 'axios';
import { useState } from 'react';
import './SignUpForm.css';

export const SignUpForm = () => {
    const [signUp, setSignUp] = useState({
        name: '',
        username: '',
        email: '',
        password: ''
    })

    const handleChange = (e) => {
        const { name, value } = e.target;
        setSignUp((signUp) => ({
            ...signUp,
            [name]: value
        }))
    }

    const signUpSubmitHandler = async (e) => {
        e.preventDefault();
        const { name, username, email, password } = signUp;
        const signUpData = { name, username, email, password };

        try {
            console.log(`${import.meta.env.VITE_CHITTERURL}/getUser/signUp`);
            const response = await axios.post(`${import.meta.env.VITE_CHITTERURL}/getUser/signUp`, signUpData);
            console.log('Sign-up successful:', response.data.message);
            handleSignUp();
        } catch (error) {
            console.error('Sign-up failed:', error.message);
            if (error.response) {
                console.log('Server responded:', error.response.data);
            }
        }
    }

    return (
        <div className="signup-form">
            <h2>Sign Up</h2>
            <form onSubmit={signUpSubmitHandler}>
                <label htmlFor="name">Name:</label>
                <input
                    type="text"
                    id="name"
                    name="name"
                    value={signUp.name}
                    onChange={handleChange}
                    placeholder="John Doe"
                    required
                />
                <label htmlFor="username">Username:</label>
                <input
                    type="text"
                    id="username"
                    name="username"
                    value={signUp.username}
                    onChange={handleChange}
                    placeholder="johndoe"
                    required
                />
                <label htmlFor="email">Email:</label>
                <input
                    type="email"
                    id="email"
                    name="email"
                    value={signUp.email}
                    onChange={handleChange}
                    placeholder="johndoe@mail.com"
                    required
                />
                <label htmlFor="password">Password:</label>
                <input
                    type="password"
                    id="password"
                    name="password"
                    value={signUp.password}
                    onChange={handleChange}
                    placeholder="Your secure password"
                    required
                />
                <button className="font-bold" type="submit">Sign Up</button>
            </form>
        </div>
    )
}