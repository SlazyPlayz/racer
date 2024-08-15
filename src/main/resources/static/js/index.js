document.addEventListener('DOMContentLoaded', async () => {
    await fetch('/races/upcoming')
        .then(response => response.json())
        .then(data => {
            const upcomingRaces = document.getElementById('upcoming-races');
            const image = document.getElementById('home-image');

            image.src = data[0].imageUrl;

            for (let race of data) {
                const article = document.createElement('article');

                const h6 = document.createElement('h6');
                h6.textContent = `${race.name}`;

                const span = document.createElement('span');
                span.textContent = `(${race.track})`;

                const p = document.createElement('p');
                p.textContent = `${race.organizer}`;

                h6.appendChild(span);
                article.appendChild(h6);
                article.appendChild(p);

                upcomingRaces.appendChild(article);
            }
        });
});