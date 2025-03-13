// ESM 방식으로 변경

import fs from 'node:fs/promises'; // 반환형이 Promise

async function getDirectories() {
  try {
    const result = await fs.readdir('./');
    console.log(result);
  } catch (error) {
    console.error(error);
  }
}

getDirectories();
