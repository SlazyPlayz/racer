import React from 'react';

function Home() {
	return (
		<main>
			{/* 1x1 */}
			<section className='last-race'>
				<img
					src='https://thumbs.dreamstime.com/z/race-circuit-guide-track-scheme-isolated-white-background-racing-displays-main-technical-elements-editable-strokes-266994709.jpg'
					alt='Race Track'
				/>
			</section>
			{/* 1x1 */}
			<section className='last-race-leaderboard'></section>
			{/* 2x1 */}
			<section className='upcoming races'></section>
		</main>
	);
}

export default Home;
