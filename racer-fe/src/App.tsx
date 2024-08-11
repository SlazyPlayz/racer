import './App.css';
import Navigation from './navigation/Navigation';
import { BrowserRouter, Routes, Route } from 'react-router-dom';
import Home from './home/Home';
import Profile from "./profile/Profile";
import NoPage from "./no-page/NoPage";

function App() {
	return (
		<>
			<BrowserRouter>
				<Routes>
					<Route path="/" element={<Navigation />}>
						<Route index element={<Home />} />
						<Route path="profile" element={<Profile />} />
						<Route path="*" element={<NoPage />} />
					</Route>
				</Routes>
			</BrowserRouter>
		</>
	);
}

export default App;
