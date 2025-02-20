function myFunc() {
    // 버튼  클릭하면 실행

    // jQuery로 AJAX 실행
    // 어떤 API 호출할지, GET|POST 방식, 넘어가는 파라미터... 등 정보가 있어야 호출됨
    // 이 정보를 JS 객체로 만들어 사용
    // let obj = {
    //     "name" : "aaa",
    //     "age" : 20,
    //     address : "seoul",
    //     10 : 500, // key는 무조건 문자열로 처리됨. 10은 문자열임
    // }
    $.ajax({
        async : true, // 비동기/동기 방식 선택 default:비동기
        url : "http://www.kobis.or.kr/kobisopenapi/webservice/rest/boxoffice/searchDailyBoxOfficeList.json",
        type : "GET",
        dataType : "json", // default : json
        data : {
            key : "8b495d04fe9586cbfe48f00659a8ce72",
            targetDt : "20250218"
        },
        success: function(response) {
            // response라는 매개변수로 서버가 보내준 json 문자열이 객체로 매핑됨
            alert("호출 성공!");
            console.log("요청 성공:", response);
            let dailyBoxOfficeList = response.boxOfficeResult.dailyBoxOfficeList;
            console.log(dailyBoxOfficeList)

            dailyBoxOfficeList.forEach((movie) =>  {
                console.log(movie.movieNm)
                //
                //
                // // let mName = movie.
                // // let li = $("<li></li>").text(mName) // <li>newName</li>
                $("ol").append(`<li>${movie.movieNm}</li>`)

            })





        },
        error: function(xhr, status, error) {
            alert("호출 실패!!")
            console.error("요청 실패:", status, error);
        }



    })

}