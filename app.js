const online = document.getElementById('online');
const messages = document.getElementById('messages');
const nameInput = document.getElementById('name');
const messageInput = document.getElementById('message');
const sendBtn = document.getElementById('send');

const wsProtocol = location.protocol === 'https:' ? 'wss:' : 'ws:';
const socket = new WebSocket(`${wsProtocol}//${location.host}/ws`);

function addMessage(text, className='system') {
  const el = document.createElement('div');
  el.className = className;
  el.textContent = text;
  messages.appendChild(el);
  messages.scrollTop = messages.scrollHeight;
}

socket.onopen = () => {
  addMessage('Live channel connected. You can message me in real time.', 'system');
};

socket.onmessage = (event) => {
  const data = JSON.parse(event.data);
  if (data.type === 'presence') {
    online.textContent = `${data.online} online`;
  }
  if (data.type === 'chat') {
    const el = document.createElement('div');
    el.className = 'msg';
    const who = document.createElement('b');
    who.textContent = data.name + ': ';
    el.appendChild(who);
    el.appendChild(document.createTextNode(data.message));
    messages.appendChild(el);
    messages.scrollTop = messages.scrollHeight;
  }
};

socket.onclose = () => {
  online.textContent = 'Offline';
  addMessage('Live channel disconnected. Refresh to reconnect.', 'system');
};

function send() {
  const message = messageInput.value.trim();
  const name = nameInput.value.trim() || 'Visitor';
  if (!message || socket.readyState !== WebSocket.OPEN) return;
  socket.send(JSON.stringify({name, message}));
  messageInput.value = '';
  messageInput.focus();
}

sendBtn.addEventListener('click', send);
messageInput.addEventListener('keydown', e => {
  if (e.key === 'Enter') send();
});

document.querySelector('.menu').addEventListener('click', () => {
  const nav = document.querySelector('.nav nav');
  nav.style.display = nav.style.display === 'flex' ? '' : 'flex';
  nav.style.position = 'absolute';
  nav.style.top = '76px';
  nav.style.left = '0';
  nav.style.right = '0';
  nav.style.padding = '20px';
  nav.style.background = '#0a1725';
  nav.style.flexDirection = 'column';
});
