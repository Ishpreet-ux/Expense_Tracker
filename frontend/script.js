const list = document.getElementById('tx-list');
const form = document.getElementById('tx-form');

async function fetchTransactions() {
    console.log("Fetching transactions from server...");
    try {
        const res = await fetch('/api/transactions');
        const transactions = await res.json();
        console.log("Received data:", transactions);
        render(transactions);
        updateSummary(transactions);
    } catch (err) {
        console.error("Fetch error:", err);
    }
}

function render(data) {
    if (!list) return console.error("Table body 'tx-list' not found!");
    
    list.innerHTML = data.map(t => `
        <tr>
            <td>${t.date}</td>
            <td>${t.title}</td>
            <td>${t.category}</td>
            <td style="color: ${t.type === 'income' ? '#27ae60' : '#c0392b'}">
                ${t.type === 'income' ? '+' : '-'}₹${Math.abs(t.amount)}
            </td>
        </tr>
    `).join('');
}

function updateSummary(data) {
    const income = data.filter(t => t.type === 'income').reduce((s, t) => s + t.amount, 0);
    const expense = data.filter(t => t.type === 'expense').reduce((s, t) => s + t.amount, 0);
    
    document.getElementById('total-income').innerText = `₹${income}`;
    document.getElementById('total-expense').innerText = `₹${expense}`;
    document.getElementById('balance').innerText = `₹${income - expense}`;
}

form.addEventListener('submit', async (e) => {
    e.preventDefault();
    console.log("Submit button clicked!");

    const newTx = {
        title: document.getElementById('title').value,
        amount: parseFloat(document.getElementById('amount').value),
        category: document.getElementById('category').value,
        type: document.getElementById('type').value,
        date: document.getElementById('date').value
    };

    console.log("Sending new transaction:", newTx);

    const res = await fetch('/api/transactions', {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(newTx)
    });

    if (res.ok) {
        console.log("Server saved successfully!");
        form.reset();
        await fetchTransactions(); // Refresh the list
    } else {
        console.error("Server failed to save.");
    }
});

fetchTransactions();