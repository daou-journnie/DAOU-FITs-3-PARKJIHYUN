function getMovieInfo() {
    const formattedDate = $("#searchDate").val().replace(/-/g, '');
    console.log(formattedDate); // 예: 20250220

    $.ajax({
        async : true,
        url : "http://www.kobis.or.kr/kobisopenapi/webservice/rest/boxoffice/searchDailyBoxOfficeList.json",
        type : "GET",
        dataType : "json",
        data : {
            key : "8b495d04fe9586cbfe48f00659a8ce72",
            targetDt : formattedDate
        },
        success: function(response) {
            alert("호출 성공!");
            console.log("요청 성공:", response);
            let dailyBoxOfficeList = response.boxOfficeResult.dailyBoxOfficeList;
            console.log(dailyBoxOfficeList);

            // 테이블 초기화
            $("tbody").empty();

            dailyBoxOfficeList.forEach((movie) => {
                // 새로운 행 생성
                let $tr = $("<tr></tr>");

                // 순위, 영화 제목, 누적 관객수, 개봉일 등의 셀 추가
                $tr.append(`<td>${movie.rank}</td>`);
                // 포스터 셀은 임시 텍스트로 시작
                let $posterCell = $("<td>포스터 로딩중...</td>");
                $tr.append($posterCell);
                $tr.append(`<td><a href="https://search.naver.com/search.naver?sm=tab_hty.top&where=nexearch&ssc=tab.nx.all&query=영화+${movie.movieNm}">${movie.movieNm}</a></td>`);
                $tr.append(`<td>${movie.audiAcc}</td>`);
                $tr.append(`<td>${movie.openDt}</td>`);
                // delete 버튼 (해당 행만 삭제하도록 구현)
                $tr.append(`<td><input type="button" value="delete" onclick="deleteRow(this)"></td>`);

                // 행을 테이블 바디에 추가
                $("tbody").append($tr);

                // Kakao API로 영화 포스터 가져오기
                $.ajax({
                    // url: "https://dapi.kakao.com/v2/search/web",
                    url: "https://dapi.kakao.com/v2/search/image",
                    type: "GET",
                    headers: {
                        Authorization: "KakaoAK 59f5a1e92325ee6cc36a2e4a31bbd03c"
                    },
                    data: {
                        query: movie.movieNm + " 포스터"
                    },

                    success: function(kakaoResponse) {
                        // console.log("kakao url" + kakaoResponse.documents[0]);

                        if (kakaoResponse.documents && kakaoResponse.documents.length > 0) {
                            let imageUrl = kakaoResponse.documents[0].thumbnail_url;
                            // 포스터 셀 업데이트
                            $posterCell.html(`<img src="${imageUrl}" alt="${movie.movieNm} 포스터" style="max-width:100px;">`);
                        } else {
                            $posterCell.text("포스터 없음");
                        }
                    },
                    error: function(err) {
                        console.error("Kakao API 호출 실패:", err);
                        $posterCell.text("포스터 에러");
                    }
                });
            });
        },
        error: function(xhr, status, error) {
            alert("호출 실패!!");
            console.error("요청 실패:", status, error);
        }
    });
}

// delete 버튼 클릭 시 해당 행 삭제 함수
function deleteRow(btn) {
    $(btn).closest("tr").remove();
}
