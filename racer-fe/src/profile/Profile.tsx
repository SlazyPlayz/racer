import './profile.css';

function Profile() {
	return (
		<main>
			<div className='user-container'>
				<div className='user-info'>
					<img
						src='https://www.pngall.com/wp-content/uploads/5/Profile-PNG-File.png'
						alt='Profile'
						className='profile-img'
					/>
					<div className='user-data'>
						<h2 className='full-name'>Petar Ivanov</h2>
						<p className='username'>@petar.ivanov</p>
						<div className='races-container'>
							<div className='races-data'>
								<p className='race-info'>Best Placement</p>
								<p className='race-data'>4th</p>
							</div>
							<div className='races-data'>
								<p className='race-info'>Total Races</p>
								<p className='race-data'>27</p>
							</div>
						</div>
					</div>
				</div>
			</div>
			<p className='about'>
				Lorem ipsum dolor, sit amet consectetur adipisicing elit. Vel at
				tenetur, dolorem minus commodi repellendus nulla blanditiis
				libero porro ipsam.
			</p>
		</main>
	);
}

export default Profile;
