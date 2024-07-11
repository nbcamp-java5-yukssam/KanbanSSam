// 카드 추가 팝업 열기
function openAddColumnPopup() {
    $('#column-container').addClass('active');
}

// 카드 추가 팝업 닫기
function closeAddColumnPopup() {
    $('#column-container').removeClass('active');
}

// 카드 추가 API 요청
function addColumn() {
    // todo : boardId 가져올수 있게 후 작업 필요
    const boardId = 1;
    const name = $('#name').val();

    const data = {
        'name': name
    };

    $.ajax({
        type: 'POST',
        url: `/board/${boardId}/column`,
        contentType: 'application/json',
        data: JSON.stringify(data),
        success: function (response) {
            console.log(response.title)
            $('#column-container').removeClass('active');
            alert('컬럼 추가 성공.');
            window.location.reload();
        },error: err => {
            alert(err.responseJSON.message);
        }
    })
}