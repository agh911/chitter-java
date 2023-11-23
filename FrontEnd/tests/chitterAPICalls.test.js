import axios from 'axios';
import { getPeeps, addPeep } from '../src/asyncFunctions/chitterAPICalls.js';

vi.mock('axios');

describe('chitterAPICalls', () => {
    test('getPeeps should return peeps when API call is successful', async () => {
        const mockPeeps = [
            {
                _id: '1',
                name: 'John Doe',
                username: 'johndoe',
                content: 'This is a test peep.',
                createdAt: '2023-08-20T12:00:00.000Z',
            },
        ];

        axios.get.mockResolvedValueOnce({ data: mockPeeps, status: 200 });

        const result = await getPeeps();

        expect(result.peeps).toEqual(mockPeeps);
        expect(result.status).toEqual(200);
    });

    test('addPeep should add a peep when API call is successful', async () => {
        const mockPeep = {
            _id: '2',
            name: 'Jane Smith',
            username: 'janesmith',
            content: 'This is another test peep.',
            createdAt: '2023-08-21T10:00:00.000Z',
        };

        axios.post.mockResolvedValueOnce({ data: mockPeep, status: 201 });

        const result = await addPeep(mockPeep);

        expect(result.peep).toEqual(mockPeep);
        expect(result.status).toEqual(201);
    });

    test('addPeep should handle error response', async () => {
        const mockError = {
            response: {
                status: 500,
                data: {
                    message: 'Internal Server Error',
                },
            },
        };

        axios.post.mockRejectedValueOnce(mockError);

        const result = await addPeep({});

        expect(result.status).toEqual(500);
        expect(result.error.type).toEqual('post');
        expect(result.error.message).toEqual(mockError.response.data.message);
    });
});
