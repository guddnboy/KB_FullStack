import api from 'axios';

const BASE_URL = '/api/member';
const headers = { 'Content-Type': 'multipart/form-data' }; // 파일 업로드용 헤더

export default {
  // 아이디 중복 체크 API
  // username 중복 체크 - true: 중복(사용불가), false: 사용가능
  async checkUsername(username) {
    const { data } = await api.get(`${BASE_URL}/checkusername/${username}`);
    console.log('AUTH GET CHECKUSERNAME', data);
    return data;
  },

  // 회원 가입 API
  async create(member) {
    // 아바타 파일 업로드 –> multipart 인코딩 필요
    // -> FormData 객체로 multipart 인코딩
    const formData = new FormData();
    formData.append('username', member.username);
    formData.append('email', member.email);
    formData.append('password', member.password);

    // 아바타 파일이 있는 경우에만 추가
    if (member.avatar) {
      formData.append('avatar', member.avatar);
    }

    const { data } = await api.post(BASE_URL, formData, headers);
    console.log('AUTH POST:', data);
    return data;
  },
};
