// 카드 추가 팝업 열기
function createBtn() {
    $('#board-popup').addClass('active');
}

function exitBtn() {
    $('#board-popup').removeClass('active');
}

function createBoard() {
    const userId = 1;
    const name = $('#name').val();
    const introduction = $('#introduction').val();

    const data = {
        'name': name,
        'introduction': introduction
    };

    $.ajax({
        type: 'POST',
        url: `/api/user/{userId}/board`,
        contentType: 'application/json',
        data: JSON.stringify(data),
        success: function (response) {
            console.log(response.title)
            $('#card-container').removeClass('active');
            alert('보드 생성 성공.');
            window.location.reload();
        },error: err => {
            alert(err.responseJSON.message);
        }
    })
}