/**
 * 삭제 버튼 클릭 이벤트 핸들러
 * 사용자 확인 후 POST 요청으로 삭제 처리
 */
document.querySelector('.delete').onclick = function() {
    // 사용자 확인
    if (!confirm('정말 삭제할까요?')) {
        return; // 취소 시 함수 종료
    }

    // 숨겨진 폼을 통해 POST 요청 전송
    document.getElementById('deleteForm').submit();
};
