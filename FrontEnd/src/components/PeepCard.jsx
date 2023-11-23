import PropTypes from 'prop-types';
import { ChatBubbleOutline, Repeat, FavoriteBorderOutlined, PublishOutlined } from '@mui/icons-material';
import './PeepCard.css'

export const PeepCard = ({ peeps }) => {
    const formatDate = (dateString) => {
        const options = {
            year: 'numeric',
            month: 'short',
            day: 'numeric',
            hour: 'numeric',
            minute: 'numeric',
            hour12: true,
        };
        return new Date(dateString).toLocaleString('en-GB', options);
    };

    return (
        <>
            {peeps.map((peep) => (
                <div key={peep.id} className='peep-card mt-5 pb-5 border-bottom'>
                    <div className="d-flex mx-4 my-3">
                        <img src="https://imgs.search.brave.com/bHpTjt49BE6IN6GPjmIm4FaNZXFj4xFH3ey8KXtPew0/rs:fit:860:0:0/g:ce/aHR0cHM6Ly93d3cu/dzNzY2hvb2xzLmNv/bS9ob3d0by9pbWdf/YXZhdGFyLnBuZw" alt="User profile image" className='user-image' />
                        <div>
                            <div className="d-flex align-items-center">
                                <p className='peep-auth me-2'>{peep.name}</p>
                                <p className='username me-2'>@{peep.username}</p>
                                <p className='peep-date'>• {formatDate(peep.createdAt)}</p>
                            </div>
                            <p>{peep.content}</p>
                        </div>
                    </div>
                    <div className='d-flex align-items-center justify-content-between mx-4'>
                        <ChatBubbleOutline className='peep-card-icon' />
                        <Repeat className='peep-card-icon' />
                        <FavoriteBorderOutlined className='peep-card-icon' />
                        <PublishOutlined className='peep-card-icon' />
                    </div>
                </div>
            ))}
        </>
    )
}

PeepCard.propTypes = {
    peeps: PropTypes.arrayOf(
        PropTypes.shape({
            id: PropTypes.string.isRequired,
            name: PropTypes.string.isRequired,
            username: PropTypes.string.isRequired,
            content: PropTypes.string.isRequired,
            createdAt: PropTypes.string.isRequired,
        }).isRequired,
    ).isRequired,
}