const fs = require('fs');

const data = fs.readFileSync(`${__dirname}/example.txt`, 'utf-8');

if (fs.existsSync(`${__dirname}/example-1.txt`)) {
  console.log('example-1.txt 파일이 존재합니다.');
} else {
  fs.writeFileSync(`${__dirname}/example-1.txt`, data);
}
