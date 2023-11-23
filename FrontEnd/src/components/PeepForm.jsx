import React, { useState } from 'react';
import { ImageOutlined, GifBoxOutlined, PollOutlined, SentimentSatisfiedAltOutlined, CalendarTodayOutlined, LocationOnOutlined } from '@mui/icons-material';
import './PeepForm.css';
import axios from 'axios';

export const PeepForm = ({ user }) => {
    const [peepText, setPeepText] = useState('');

    const handlePeepChange = (event) => {
        setPeepText(event.target.value);
    }


    const handlePeepSubmit = async (event) => {
        event.preventDefault();
        try {
            let peepData = {};
            if (user) {
                peepData = {
                    'name': user.name,
                    'username': user.username,
                    'content': peepText,
                };
            }
            console.log('Peep submitted:', peepData);
            const response = await axios.post(`${import.meta.env.VITE_CHITTERURL}/peeps`, peepData);
            console.log('Peep added successfully:', response.data.message);
            setPeepText('');
            window.location.reload(false);
        } catch (error) {
            console.error('Failed:', error.message);
            if (error.response) {
                console.log('Server responded:', error.response.data);
            }
        }
    }

    return (
        <div className="peep-form mb-2 border-bottom">
            <form className='pt-2' onSubmit={handlePeepSubmit}>
                <div className='d-flex'>
                    <img src="https://imgs.search.brave.com/bHpTjt49BE6IN6GPjmIm4FaNZXFj4xFH3ey8KXtPew0/rs:fit:860:0:0/g:ce/aHR0cHM6Ly93d3cu/dzNzY2hvb2xzLmNv/bS9ob3d0by9pbWdf/YXZhdGFyLnBuZw" alt="User profile image" className='user-image' />
                    <textarea
                        value={peepText}
                        onChange={handlePeepChange}
                        placeholder="What's happening?"
                        rows={4}
                    />
                </div>
                <div className="d-flex justify-content-between align-items-center border-top">
                    <div className="peep-icons mt-3">
                        <ImageOutlined />
                        <GifBoxOutlined />
                        <PollOutlined />
                        <SentimentSatisfiedAltOutlined />
                        <CalendarTodayOutlined className="peep-icon" />
                        <LocationOnOutlined />
                    </div>
                    <input
                        type="submit"
                        value="Peep"
                        disabled={peepText.length === 0}
                        className="peep-submit mt-3"
                    />
                </div>
            </form>
        </div>
    )
}
