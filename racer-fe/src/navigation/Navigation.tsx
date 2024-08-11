import React from 'react';
import './navigation.css';
import { Link, Outlet } from 'react-router-dom';

function Navigation() {
	return (
		<>
			<header>
				<nav>
					<ul>
						<li>
							<Link to='/'>Home</Link>
						</li>
						<li>
							<Link to='/races'>All Races</Link>
						</li>
						<li>
							<Link to='/search-race'>Search Race</Link>
						</li>
						<li>
							<Link to='/leaderboard'>Leaderboard</Link>
						</li>
						<li>
							<Link to='/profile'>Profile</Link>
						</li>
						<li>
							<Link to='/register'>Register</Link>
						</li>
					</ul>
				</nav>
			</header>

			<Outlet />
		</>
	);
}

export default Navigation;
