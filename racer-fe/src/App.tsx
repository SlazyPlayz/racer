import './App.css';
import Navigation from './navigation/Navigation';
import { BrowserRouter, Routes, Route } from 'react-router-dom';
import Home from './home/Home';
import Profile from './profile/Profile';
import NoPage from './no-page/NoPage';
import Register from './user/register/Register';
import axios from 'axios';
import { createContext, useEffect, useState } from 'react';

export const CsrfContext = createContext<Object | null>(null);

function App() {
	const [csrfToken, setCsrfToken] = useState<Object | null>(null);

	useEffect(() => {
		const getToken = async () => await GetCsrfToken();
		setCsrfToken(getToken);
	}, []);

	return (
		<CsrfContext.Provider value={csrfToken}>
			<BrowserRouter>
				<Routes>
					<Route path='/' element={<Navigation />}>
						<Route index element={<Home />} />
						<Route path='profile' element={<Profile />} />
						<Route path='register' element={<Register />} />
						<Route path='*' element={<NoPage />} />
					</Route>
				</Routes>
			</BrowserRouter>
		</CsrfContext.Provider>
	);
}

async function GetCsrfToken() {
	const response = await axios.get('http://localhost:8080/csrf-token', {
		withCredentials: true,
	});
	return response.data.token;
}

export default App;
