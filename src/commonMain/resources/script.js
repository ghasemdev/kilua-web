const toPageAButton = document.getElementById('toPageA');
const toPageBButton = document.getElementById('toPageB');
const pageA = document.getElementById('pageA');
const pageB = document.getElementById('pageB');

let currentPage = 'A';

function navigateTo(page) {
    if (currentPage === page) return;

    if (page === 'A') {
        pageB.classList.remove('active');
        pageB.classList.add('exit-right');
        setTimeout(() => {
            pageB.classList.remove('exit-right');
        }, 500);
        pageA.classList.add('active');
    } else if (page === 'B') {
        pageA.classList.remove('active');
        pageA.classList.add('exit-left');
        setTimeout(() => {
            pageA.classList.remove('exit-left');
        }, 500);
        pageB.classList.add('active');
    }

    currentPage = page;
}

toPageAButton.addEventListener('click', () => navigateTo('A'));
toPageBButton.addEventListener('click', () => navigateTo('B'));
