// script 태그는 제외한다.

// 자바스크립트 코드와 jsp 문법이 혼합되어 있을 경우: views 폴더 하위에 파일 확장자는 jsp로 저장
// 순수 자바스크립트 코드만 사용되는 경우: resource 폴더 하위에 파일 확장자는 js로 저장

$(document).ready(function () {
  // 1차 카테고리 오버
  // $("1차 카테고리 태그를 참조하는 선택자").on();
  $("div#category_menu li a").on("mouseover", function (e) {
    e.preventDefault(); // a 태그의 링크 기능을 취소
    // console.log("1차 카테고리 오버");

    let sel_first_category = $(this);

    // let cg_code = "선택한 1차 카테고리 코드";
    let cg_code = $(this).data("cg_code"); // data-cg_code -> $(this).data("cg_code")

    // console.log("1차 카테고리 코드: ", cg_code);
    // return; // 하단 코드를 동작시키지 않게 하기 위함

    // let url = '2차 카테고리 정보를 가져오는 주소';
    let url = '/category/secondCategory/' + cg_code;
    $.getJSON(url, function (category) {

      // console.log(category);
      let str = '<ul class="nav justify-content-center" id="second_category">';
      for (let i = 0; i < category.length; i++) {
        str += '<li class="nav-item">';
        // 바로 아래 부분이 2차 카테고리를 보여주는 부분
        // 
        str += '<a class="nav-link active" href="#" data-cg_code="'+ category[i].cg_code + '" + data-cg_name="' + category[i].cg_name + '">'+ category[i].cg_name + '</a>'; // str += '2차 카테고리'
        str += '</li>';
      }
      str += '</ul>';

      // console.log(str);
      sel_first_category.parent().parent().next().remove();
      sel_first_category.parent().parent().after(str);
    });
  });

  // 2차 카테고리 선택
  // $("정적 태그 참조 선택자").on("이벤트명", "동적 태그 참조 선택자", function() {});
  $("div#category_menu").on("click", "ul#second_category li a", function(e) {
    // console.log("2차 카테고리 작업");

    let cg_code = $(this).data("cg_code");
    let cg_name = $(this).data("cg_name");
    // 한글이나 특수문자를 서버에 보낼 때 오류가 나는 경우 인코딩 과정에 의해 처리할 수 있다.
    // [참고] https://travelpark.tistory.com/30 

    // 1) 현재 파일의 확장자가 jsp일 경우, 아래처럼 사용하는 경우, ${cg_code} 변수로 인식하는 것이 아니라 JSP의 EL 문법으로 서버에서 동작된다(주의).
    // location.href = `/user/product/pro_list?cg_code=${cg_code}&cg_name=${cg_name}`; // 문제 발생

    // 권장
    // location.href = '/user/product/pro_list?cg_code=' + cg_code + '&cg_name=' + cg_name;

    // 2) 현재 파일의 확장자가 js일 경우
    // location.href = `/user/product/pro_list/${변수}`: 주소의 일부분이 파라미터 값으로 사용
    location.href = `/user/product/pro_list?cg_code=${cg_code}&cg_name=${cg_name}`; // 템플릿 리터럴로 활용되는 백틱과 ${}

  });

});