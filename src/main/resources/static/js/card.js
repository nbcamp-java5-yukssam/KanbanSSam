// 카드 추가 팝업 열기
function openAddCardPopup() {
    $('#card-container').addClass('active');
}

// 카드 추가 팝업 닫기
function closeAddCardPopup() {
    $('#card-container').removeClass('active');
}

// 카드 추가 API 요청
function addCard() {
    // todo : columnId 가져올수 있게 후 작업 필요
    const columnId = 1;
    const title = $('#title').val();
    const responsiblePerson = $('#responsiblePerson').val();
    const content = $('#content').val();
    const deadline = $('#deadline').val();

    const data = {
        'title': title,
        'responsiblePerson': responsiblePerson,
        'content': content,
        'deadline': deadline
    };

    $.ajax({
        type: 'POST',
        url: `/column/${columnId}/cards`,
        contentType: 'application/json',
        data: JSON.stringify(data),
        success: function (response) {
            console.log(response.title)
            $('#card-container').removeClass('active');
            alert('카드 추가 성공.');
            window.location.reload();
        },error: err => {
            alert(err.responseJSON.message);
        }
    })
}