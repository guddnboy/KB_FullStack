import axios from 'axios';

export const downloadFile = async (fileUrl) => {
  try {
    const link = document.createElement('a'); // a 노드 생성
    link.href = fileUrl; // href에 다운로드 파일 URL 설정
    document.body.appendChild(link); // DOM에 추가
    link.click(); // click 이벤트 강제 발생
    document.body.removeChild(link); // DOM에서 제거
  } catch (error) {
    console.error('파일 다운로드 실패:', error);
  }
};
