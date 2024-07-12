function getCardIdFromURL() {
  const regex = /\/cards\/(\d+)\/comments\/view/;
  const match = window.location.pathname.match(regex);

  if (match && match[1]) {
    return match[1];
  } else {
    return null;
  }
}

// 카드 추가 API 요청
function addComment() {
  // todo : CardId 가져올수 있게 후 작업 필요
  const cardId = getCardIdFromURL();
  const comment = $('#comment').val();
  const token = localStorage.getItem('Authorization');
  console.log(token)

  const data = {
    'comment': comment
  };

  $.ajax({
    type: 'POST',
    url: `/cards/${cardId}/comments`,
    contentType: 'application/json',
    headers: {
      'Authorization':token ,// 토큰을 Authorization 헤더에 포함
      'Content-Type': 'application/json'
    },
    data: JSON.stringify(data),
    success: function (response) {
      console.log(response.title)
      alert('댓글 추가 성공.');
      window.location.reload();
    },error: err => {
      alert(err.responseJSON.message);
    }
  })
}