// selector
// 전체 선택자 : *
// 타입 선택자 : 원하는 태그 명을 이영해서 선택
// 아이디 선택자 : 원하는 아이디를 선택 - id는 unique
// 클래스 선택자 : 원하는 클래스 가진 element - not unique
// 자식 선택자 / 후손 선택자 : >, " "(공백)으로 선택
// 선택자는 합성해서 사용할 수 있음
// 동위 선택자 :
// + : 바로 다음에 나오는 형제 하나
// ~ : 바로 다음에 나오는 형제 하나 포함 나머지 모두
// 속성 선택자  : []


// methods
// each() : 반복 처리 메서드(for loop 대용)
// css()
// remove()
// text()
// value()

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



function showFunc() {
    let selected = $('select > option:selected').text();
    console.log(selected)
    // 입력 상자이기 때문에 text() 대신 val() 사용
    // 인자 없는 val은 현재 입력 상자 안 값 알아오기
    // 인자를 넣어서 val 사용하면 인자값으로 입력 상자 채움
    $('input').val(selected);
}

function checkFunc() {
    // 1. checkbox 찾기
    // 속성을 이용해서 찾을 때는 [] 이용
    // each() : 선택한 element 반복처리
    $("[type=checkbox]:checked + span").each(function (idx, item){
        // idx는 0, 1, 2, ... for문의 첨자 역할
        // item : 선택된 Document object - jQuery 객체 아님
        // jQuery 객체로 바꿔서 사용해야함
        console.log($(item).text() + " 선택됨")
    })

}

function newFunc() {
    let newName = $("#newLi").val()
    let li = $("<li></li>").text(newName) // <li>newName</li>
    // 원하는 위치에 넣는 4가지 방법
    // 1. append() : 마지막 자식으로
    // $("ol").append(li)
    // 2. prepend() : 첫번째자식으로
    // 3. after()
    // $("ol > li:first").after(li);
    // 4. before
    // $("ol > li:last").before(li);

    // image 생성
    // <img src="img/cha.jpg" width=200>
    // width 등의 속성을 붙여야함. - .attr()
    let myImg = $("<img />").attr("src","img/cha.png").attr("width","200") // <img>
    $("ol").append(myImg)

    // 이벤트 처리
    // <h1> 찾아서 이벤트 등록
    // $("h1").on("click", function () {
    //     alert($("h1").text()) // 이렇게 하면 모든 h1이 나옴

    $("h1").on("click", function (){
        // this의 원래 의미는 현재 사용되는 객체에 대한 reference
        // javaScript Event 처리코드 내에서 this 의미는 다름
        // 이벤트 소스에 대한 문서 객체를 지칭할 때 this 사용!!!
        // 그니까 this 사용하려면 $를 써서 doc obj -> jQuery obj로 바꿔줘야함
        alert($(this).text())
    })


}






























