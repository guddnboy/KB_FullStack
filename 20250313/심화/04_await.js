const fs = require('fs').promises;

const read = async () => {
  try {
    const data = await fs.readdir(__dirname);
    console.log(data);
  } catch (err) {
    console.error(err);
  }
};

read();
