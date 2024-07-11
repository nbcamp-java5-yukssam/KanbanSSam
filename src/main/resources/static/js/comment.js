// 카드 추가 API 요청
function addComment() {
  // todo : CardId 가져올수 있게 후 작업 필요
  const cardId = 1;
  const comment = $('#comment').val();

  const data = {
    'comment': comment
  };

  $.ajax({
    type: 'POST',
    url: `/card/${cardId}/comment`,
    contentType: 'application/json',
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