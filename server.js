const express = require('express');
const fs = require('fs');
const path = require('path');
const app = express();
const PORT = 3000;
const DATA_FILE = path.join(__dirname, 'transactions.json');

app.use(express.json());
app.use(express.static(path.join(__dirname, 'frontend')));

// Ensure the data file exists before the API is used
if (!fs.existsSync(DATA_FILE)) {
    fs.writeFileSync(DATA_FILE, JSON.stringify([], null, 2));
}

// Helper to handle data
const readData = () => JSON.parse(fs.readFileSync(DATA_FILE, 'utf8') || '[]');
const writeData = (data) => fs.writeFileSync(DATA_FILE, JSON.stringify(data, null, 2));

// API Routes
app.get('/api/transactions', (req, res) => {
    res.json(readData());
});

app.post('/api/transactions', (req, res) => {
    const transactions = readData();
    const newTx = { id: Date.now(), ...req.body };
    transactions.push(newTx);
    writeData(transactions);
    res.status(201).json(newTx);
});

app.listen(PORT, () => console.log(`Server at http://localhost:${PORT}`));