import React, {JSX, useState} from 'react';
import './home.css';

function Home() {

    // const [upcomingRaces, setUpcomingRaces] = useState<UpcomingRace[]>([]);

    return (
        <main>
			<LastRace />
			<LastRaceLeaderboard />
            <UpcomingRaces/>
        </main>
    );
}

interface UpcomingRace {
    title: string;
    location: string;
    organizer: string;
}

function LastRace(): JSX.Element {
	return (
		<section className='last-race'>
			<img
				src='https://c.stocksy.com/a/ej9A00/z9/2420706.jpg'
				alt='Race Track'
				className='last-race-img'
			/>
		</section>
	);
}

function LastRaceLeaderboard(): JSX.Element {
	return (
		<section className='last-race-leaderboard'>
			<article>This is a test</article>
			<article>This is a test</article>
			<article>This is a test</article>
            <article>This is a test</article>
            <article>This is a test</article>
        </section>
    );
}

function UpcomingRaces(): JSX.Element {

	const Title = (): JSX.Element => {
		return <h3>Upcoming Races</h3>
	}

	const Race = ({title, location, organizer}: UpcomingRace): JSX.Element => {
		return <article>
			<h6>{title} <span>({location})</span></h6>
			<p>{organizer}</p>
		</article>
	}

    return (
        <section className='upcoming-races'>
			<Title />
			<Race title={'Milano Qualifications'} location={'Milan'} organizer={'Milano Race Association'} />
            <article>This is another test</article>
            <article>This is another test</article>
            <article>This is another test</article>
            <article>This is another test</article>
        </section>
	);
}

export default Home;
