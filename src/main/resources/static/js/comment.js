function getCardIdFromURL() {
  const regex = /\/cards\/(\d+)\/comments\/view/;
  const match = window.location.pathname.match(regex);

  if (match && match[1]) {
    return match[1];
  } else {
    return null;
  }
}

// 토큰 유무 확인
function isNotEmpty(val) {
  if (val !== 'undefined') {
    return true;
  }
  return false;
}
// 토큰 유무 확인
function isEmpty(val) {
  return !isNotEmpty(val);
}

// 쿠키에서 값을 읽는 함수
function getCookie(name) {
  const value = `; ${document.cookie}`;
  const parts = value.split(`; ${name}=`);
  if (parts.length === 2) return parts.pop().split(';').shift();
}

// 카드 추가 API 요청
function addComment() {
  const cardId = getCardIdFromURL();
  const comment = $('#comment').val();
  const token = decodeURIComponent(getCookie('Authorization'));
  if(isEmpty(token)){
    alert('로그인 후 이용해주세요');
    window.location.href = 'http://localhost:8080/users/view/login-page';
    return;
  }



  const data = {
    'comment': comment
  };

  $.ajax({
    type: 'POST',
    url: `/cards/${cardId}/comments`,
    contentType: 'application/json',
    headers: {
      'Authorization': token,// 토큰을 Authorization 헤더에 포함
      'Content-Type': 'application/json'
    },
    data: JSON.stringify(data),
    success: function (response) {
      console.log(response.title)
      alert('댓글 추가 성공.');
      window.location.reload();
    }, error: err => {
      alert(err.responseJSON.message);
    }
  })
}

$(document).ready(function () {
  const cardId = getCardIdFromURL();
  // 페이지 로딩 시 댓글 조회 요청 보내기
  $.ajax({
    url: `/cards/${cardId}/comments`, // 요청을 보낼 URL로 변경
    method: 'GET',
    success: function (response) {
      // 성공적으로 데이터를 받은 경우 화면에 댓글을 표시
      const commentList = $('#comment-list');
      commentList.empty(); // 기존 댓글 목록 비우기
      response.forEach(comment => {
        commentList.append(
            `<div class="comment-item">
            <p><strong>${comment.writerName}</strong>: ${comment.comment}</p>
            <p>${comment.createdAt}</p>
            </div>`
        );
      });
    },
    error: function (error) {
      console.error('Error fetching comments:', error);
    }
  });
});
