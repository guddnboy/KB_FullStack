const fs = require('fs');

const data = fs.readFileSync(`${__dirname}/example.txt`, 'utf-8');

fs.writeFileSync(`${__dirname}/example-1.txt`, data);
