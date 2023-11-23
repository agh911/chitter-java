import axios from 'axios';
import { checkSignIn } from '../src/components/authentication/authenticationHelpers.js';

vi.mock('axios');

describe('authenticationHelpers', () => {
    test('checkSignIn should return true when API call is successful', async () => {
        const mockSignInData = {
            email: 'test@example.com',
            password: 'password123',
        };

        axios.post.mockResolvedValueOnce({ status: 200 });

        const signInStatus = await checkSignIn(mockSignInData);

        expect(signInStatus).toBe(true);
    });
});
