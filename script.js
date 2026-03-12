const toast = document.getElementById('toast');
const followBtn = document.getElementById('follow-btn');
const followersCount = document.getElementById('followers-count');
const postGrid = document.getElementById('post-grid');

const actionMessages = {
  'switch-account': 'Account switcher opened.',
  notifications: 'No new notifications.',
  menu: 'Menu opened.',
  posts: 'Viewing all posts.',
  followers: 'Followers list opened.',
  following: 'Following list opened.',
  link: 'Opening website...',
  message: 'Message composer opened.',
  share: 'Profile link copied to clipboard.',
  'person-add': 'Discover people opened.',
  'new-highlight': 'Create a new highlight.',
  'highlight-travel': 'Travel highlight opened.',
  'highlight-fits': 'Fits highlight opened.',
  'highlight-food': 'Food highlight opened.'
};

for (let i = 0; i < 12; i += 1) {
  const post = document.createElement('button');
  post.className = 'post';
  post.setAttribute('aria-label', `Open post ${i + 1}`);
  post.dataset.action = `post-${i + 1}`;
  post.addEventListener('click', () => showToast(`Opened post #${i + 1}`));
  postGrid.appendChild(post);
}

let isFollowing = false;
let followerTotal = 1245;

followBtn.addEventListener('click', () => {
  isFollowing = !isFollowing;
  followerTotal += isFollowing ? 1 : -1;

  followBtn.textContent = isFollowing ? 'Following' : 'Follow';
  followBtn.style.background = isFollowing ? '#262626' : '#0095f6';
  followBtn.style.borderColor = isFollowing ? '#3d3d3d' : '#0095f6';
  followersCount.textContent = followerTotal.toLocaleString();

  showToast(isFollowing ? 'You are now following this profile.' : 'You unfollowed this profile.');
});

const tabMap = {
  grid: 'grid-panel',
  reels: 'reels-panel',
  tagged: 'tagged-panel'
};

document.querySelectorAll('.tab').forEach((tabBtn) => {
  tabBtn.addEventListener('click', () => {
    document.querySelectorAll('.tab').forEach((tab) => tab.classList.remove('active'));
    document.querySelectorAll('.panel').forEach((panel) => panel.classList.remove('active'));

    tabBtn.classList.add('active');
    document.getElementById(tabMap[tabBtn.dataset.tab]).classList.add('active');
    showToast(`Switched to ${tabBtn.dataset.tab} tab.`);
  });
});

document.querySelectorAll('[data-action]').forEach((el) => {
  if (el.id === 'follow-btn' || el.classList.contains('tab') || el.classList.contains('post')) return;

  el.addEventListener('click', (event) => {
    if (el.tagName === 'A') {
      event.preventDefault();
    }

    const key = el.dataset.action;
    showToast(actionMessages[key] || `Action triggered: ${key}`);
  });
});

let toastTimer;
function showToast(message) {
  toast.textContent = message;
  toast.classList.add('show');
  clearTimeout(toastTimer);
  toastTimer = setTimeout(() => toast.classList.remove('show'), 1700);
}
