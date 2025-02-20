// DOM이 완전히 준비되면 실행합니다.
$(document).ready(function() {

    // 1. 텍스트 변경: 버튼 클릭 시 <p> 요소의 텍스트를 변경합니다.
    $('#btnChangeText').click(function() {
        $('#text').text('텍스트가 변경되었습니다!');
    });

    // 2. CSS 스타일 조작 & 이벤트: 마우스 오버/아웃 시 배경색 변경
    $('#box').hover(
        function() {
            // 마우스 오버 시 배경색 변경
            $(this).css('background-color', 'lightblue');
            $(this).text('Mouse Over!');
        },
        function() {
            // 마우스 아웃 시 원래 상태로 복원
            $(this).css('background-color', 'lightgray');
            $(this).text('Hover me!');
        }
    );

    // 3. 애니메이션 효과: 버튼 클릭 시 박스가 fadeOut 후 fadeIn
    $('#btnFade').click(function() {
        $('#fadeBox').fadeOut('slow', function() {
            // fadeOut 완료 후 다시 fadeIn
            $(this).fadeIn('slow');
        });
    });

    // 4. 이벤트 핸들링: 버튼 클릭 시 알림창 표시
    $('#btnAlert').click(function() {
        alert('버튼이 클릭되었습니다!');
    });
});
