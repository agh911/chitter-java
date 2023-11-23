import axios from 'axios';
import { useEffect, useState } from 'react';
import { Routes, Route } from 'react-router-dom';
import { ChitterHome } from './pages/ChitterHome.jsx';
import { SignInPage } from './pages/SignInPage.jsx';
import { SignUpPage } from './pages/SignUpPage.jsx';

import { checkSignIn } from './components/authentication/authenticationHelpers.js';
import { getPeeps, addPeep } from './asyncFunctions/chitterAPICalls.js';

function App() {
  const [signedIn, setSignedIn] = useState(false);
  const [email, setEmail] = useState(null);
  const [user, setUser] = useState(null);
  const [peeps, setPeeps] = useState([]);

  const handleSignIn = async ({ email, password }) => {
    const signedIn = await checkSignIn({ email, password });
    if (signedIn) {
      localStorage.setItem('email', email);
      setEmail(email);
    }
  }

  useEffect(() => {
    const storedEmail = localStorage.getItem('email');
    if (storedEmail) {
      setEmail(storedEmail);
    }
  }, []);

  useEffect(() => {
    if (email == null) {
      return;
    }
    async function fetchUserData() {
      const userQuery = {
        'email': email
      }
      const user = await axios.post(`${import.meta.env.VITE_CHITTERURL}/getUser/signedInUser`, userQuery).then((res) => res.data);
      setUser(user);
      setSignedIn(true);
    }
    fetchUserData();
  }, [email]);

  const fetchPeeps = async () => {
    const { peeps: fetchedPeeps } = await getPeeps();
    setPeeps(fetchedPeeps);
  }

  useEffect(() => {
    fetchPeeps();
  }, [])

  const handleAddPeep = async (peepData) => {
    const [peep] = await addPeep(peepData);
    setPeeps([peep, ...peeps]);
  }

  const signedOut = () => {
    localStorage.removeItem('email');
    setSignedIn(false);
  }

  return (
    <Routes>
      <Route path='/' element={<ChitterHome signedIn={signedIn} signedOut={signedOut} user={user} peeps={peeps} handleAddPeep={handleAddPeep} />} />
      <Route path='/signIn' element={<SignInPage handleSignIn={handleSignIn} />} />
      <Route path='/signUp' element={<SignUpPage />} />
    </Routes>
  )
}

export default App;