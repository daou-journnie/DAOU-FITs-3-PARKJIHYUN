
// 전체 선택자 : *
// 타입 선택자 : 원하는 태그 명을 이영해서 선택
// 아이디 선택자 : 원하는 아이디를 선택 - id는 unique
// 클래스 선택자 : 원하는 클래스 가진 element - not unique
// 자식 선택자 / 후손 선택자 : >, " "(공백)으로 선택
// 선택자는 합성해서 사용할 수 있음
// 동위 선택자 :
// + : 바로 다음에 나오는 형제 하나
// ~ : 바로 다음에 나오는 형제 하나 포함 나머지 모두

function myFunc() {
    console.log("졸려죽어요")
    $('*').css("color", "red");
    $('h1, input').css("color", "blue");
    $('#pusan').remove();
    $('.myClass').css("background", "yellow");
    $("ol > li").css("background", "pink")
    // $("div ol").remove();
    $("#a ~").css("color", "blue")
    $("#a +").css("color", "violet")
    alert($("#a +").text()) // bbb 나옴
}
