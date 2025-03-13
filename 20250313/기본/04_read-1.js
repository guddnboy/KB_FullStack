const fs = require('fs');

const content = fs.readFileSync(`${__dirname}/example.txt`, 'utf-8');

console.log(content);
