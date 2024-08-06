import React from "react";

function Navigation() {
    return <header>
        <nav>
            <ul>
                <li><a href="/">Home</a></li>
                <li><a href="/races">All Races</a></li>
                <li><a href="/races/search">Search Race</a></li>
                <li><a href="/leaderboard">Leaderboard</a></li>
                <li><a href="/profile">Profile</a></li>
            </ul>
        </nav>
    </header>
} 

export default Navigation;